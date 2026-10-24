package com.pradeep.monitor.service;

import com.pradeep.monitor.model.DiskMetric;
import com.pradeep.monitor.model.SystemHealth;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SystemMonitorService {
    private final SystemInfo systemInfo = new SystemInfo();
    private final CentralProcessor processor = systemInfo.getHardware().getProcessor();
    private final GlobalMemory memory = systemInfo.getHardware().getMemory();
    private final OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

    public SystemHealth getSystemHealth() {
        // OSHI measures CPU across a short sampling interval.
        double cpuPercent = clamp(processor.getSystemCpuLoad(700L) * 100.0);
        long totalMemory = memory.getTotal();
        long availableMemory = memory.getAvailable();
        long usedMemory = Math.max(0, totalMemory - availableMemory);
        double memoryPercent = totalMemory == 0 ? 0 : clamp((double) usedMemory / totalMemory * 100.0);

        FileSystem fileSystem = operatingSystem.getFileSystem();
        List<DiskMetric> disks = new ArrayList<>();
        for (OSFileStore store : fileSystem.getFileStores()) {
            long total = Math.max(0, store.getTotalSpace());
            long free = Math.max(0, store.getUsableSpace());
            long used = Math.max(0, total - free);
            double percent = total == 0 ? 0 : clamp((double) used / total * 100.0);

            // Skip pseudo filesystems with no meaningful capacity.
            if (total > 0) {
                disks.add(new DiskMetric(
                        safe(store.getName()),
                        safe(store.getMount()),
                        safe(store.getType()),
                        total,
                        used,
                        free,
                        roundOne(percent)
                ));
            }
        }

        double[] loads = processor.getSystemLoadAverage(3);
        double load1 = loadAt(loads, 0);
        double load5 = loadAt(loads, 1);
        double load15 = loadAt(loads, 2);

        String status = "HEALTHY";
        if (cpuPercent >= 90 || memoryPercent >= 90 ||
                disks.stream().anyMatch(d -> d.usedPercent() >= 90)) {
            status = "CRITICAL";
        } else if (cpuPercent >= 75 || memoryPercent >= 75 ||
                disks.stream().anyMatch(d -> d.usedPercent() >= 75)) {
            status = "WARNING";
        }

        return new SystemHealth(
                operatingSystem.getNetworkParams().getHostName(),
                operatingSystem.toString(),
                processor.getLogicalProcessorCount(),
                roundOne(cpuPercent),
                totalMemory,
                availableMemory,
                usedMemory,
                roundOne(memoryPercent),
                operatingSystem.getSystemUptime(),
                roundTwo(load1),
                roundTwo(load5),
                roundTwo(load15),
                Instant.now().toString(),
                status,
                disks
        );
    }

    private static double loadAt(double[] values, int index) {
        if (values == null || values.length <= index || values[index] < 0) {
            return 0.0; // Some platforms do not provide load averages.
        }
        return values[index];
    }

    private static double clamp(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) return 0.0;
        return Math.max(0, Math.min(100, value));
    }

    private static double roundOne(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private static double roundTwo(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private static String safe(String value) {
        return value == null || value.isBlank() ? "Unknown" : value;
    }
}
