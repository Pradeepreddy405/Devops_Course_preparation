package com.pradeep.monitor.controller;

import com.pradeep.monitor.model.SystemHealth;
import com.pradeep.monitor.service.SystemMonitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MonitorController {
    private final SystemMonitorService monitorService;

    public MonitorController(SystemMonitorService monitorService) {
        this.monitorService = monitorService;
    }

    // The browser calls this endpoint every 60 seconds.
    @GetMapping("/api/health")
    public SystemHealth getHealth() {
        return monitorService.getSystemHealth();
    }

    @GetMapping("/api/ping")
    public String ping() {
        return "{\"status\":\"UP\",\"application\":\"linux-health-monitor\"}";
    }
}
