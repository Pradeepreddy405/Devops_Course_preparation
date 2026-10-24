package com.pradeep.monitor.model;

public record DiskMetric(
        String name,
        String mount,
        String fileSystem,
        long totalBytes,
        long usedBytes,
        long freeBytes,
        double usedPercent
) {}
