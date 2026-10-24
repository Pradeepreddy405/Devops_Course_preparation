package com.pradeep.monitor.model;

import java.util.List;

public record SystemHealth(
        String hostname,
        String operatingSystem,
        int logicalCpuCores,
        double cpuPercent,
        long memoryTotalBytes,
        long memoryAvailableBytes,
        long memoryUsedBytes,
        double memoryPercent,
        long uptimeSeconds,
        double loadAverage1m,
        double loadAverage5m,
        double loadAverage15m,
        String collectedAt,
        String overallStatus,
        List<DiskMetric> disks
) {}
