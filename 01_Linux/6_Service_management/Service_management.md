# Linux Service Management 

A practical, hands-on guide to Linux service management for **Linux Administrators, Cloud Engineers, DevOps Engineers, and Advanced Linux Administrators**.

This repository covers service fundamentals, systemd, service lifecycle management, logs, troubleshooting, custom unit files, security, automation, and production operations.

## 📚 Table of Contents

- [1. Introduction](#1-introduction)
- [2. Core Concepts](#2-core-concepts)
- [3. Understanding systemd](#3-understanding-systemd)
- [4. Essential systemctl Commands](#4-essential-systemctl-commands)
- [5. Service States](#5-service-states)
- [6. Installing and Managing Nginx](#6-installing-and-managing-nginx)
- [7. Service Logs with journalctl](#7-service-logs-with-journalctl)
- [8. Troubleshooting Service Failures](#8-troubleshooting-service-failures)
- [9. Understanding systemd Unit Files](#9-understanding-systemd-unit-files)
- [10. Creating a Custom systemd Service](#10-creating-a-custom-systemd-service)
- [11. daemon-reload vs reload vs restart](#11-daemon-reload-vs-reload-vs-restart)
- [12. Service Dependencies and Targets](#12-service-dependencies-and-targets)
- [13. Advanced systemd Administration](#13-advanced-systemd-administration)
- [14. Security and Hardening](#14-security-and-hardening)
- [15. Cloud and DevOps Integration](#15-cloud-and-devops-integration)
- [16. Practical Labs](#16-practical-labs)
- [17. Troubleshooting Cheat Sheet](#17-troubleshooting-cheat-sheet)
- [18. Interview Questions](#18-interview-questions)
- [19. Learning Roadmap](#19-learning-roadmap)

---

## 1. Introduction

Linux service management is the process of starting, stopping, restarting, monitoring, configuring, and troubleshooting services running on a Linux system.

Services commonly run in the background and provide functionality to users, applications, and other systems.

### Examples of Linux Services

| Service | Purpose | Common Port |
|---|---|---|
| Nginx | Web server and reverse proxy | 80, 443 |
| Apache HTTP Server | Web server | 80, 443 |
| SSH daemon (`sshd`) | Remote server access | 22 |
| MySQL | Database server | 3306 |
| PostgreSQL | Database server | 5432 |
| Docker | Container management | No fixed service port |
| Prometheus | Monitoring and metrics | 9090 |

Ports depend on configuration and are not necessarily exposed externally.

## 2. Core Concepts

### Service vs Process vs Daemon

| Concept | Description |
|---|---|
| Program | Executable file stored on disk |
| Process | Running instance of a program |
| Daemon | Background process providing a service |
| Service | Managed functionality, commonly controlled by systemd |
| Unit | A resource managed by systemd, such as a service, timer, or mount |

### Important Linux Directories

| Directory | Purpose |
|---|---|
| `/usr/lib/systemd/system/` | Vendor-supplied systemd unit files on many distributions |
| `/etc/systemd/system/` | Administrator-created units and overrides |
| `/run/systemd/system/` | Runtime unit configuration |
| `/etc/` | System configuration files |
| `/var/log/` | Application and system logs, where configured |
| `/run/` | Runtime files and sockets |

Paths vary by Linux distribution. Use `systemctl cat SERVICE` to inspect a unit's actual configuration.

## 3. Understanding systemd

`systemd` is the init system and service manager used by most modern Linux distributions.

It typically runs as PID 1 and manages service startup, dependencies, system targets, and unit lifecycle operations.

### Architecture

```text
Administrator / Automation
          |
          v
      systemctl
          |
          v
       systemd
        (PID 1)
          |
          +---- nginx.service
          |
          +---- sshd.service
          |
          +---- docker.service
          |
          +---- mysqld.service
```

### Check the init system

```bash
ps -p 1 -o pid,comm,args
systemctl --version
```

## 4. Essential systemctl Commands

### Start, Stop, and Restart

```bash
sudo systemctl start nginx
sudo systemctl stop nginx
sudo systemctl restart nginx
sudo systemctl reload nginx
```

### Check Service Status

```bash
systemctl status nginx
systemctl is-active nginx
systemctl is-enabled nginx
```

### Enable and Disable Services

```bash
sudo systemctl enable nginx
sudo systemctl disable nginx
sudo systemctl enable --now nginx
sudo systemctl disable --now nginx
```

### Important Differences

| Command | Purpose |
|---|---|
| `start` | Starts the service now |
| `stop` | Stops the service |
| `restart` | Stops and starts the service |
| `reload` | Requests the service to reload its configuration |
| `enable` | Configures automatic activation at boot |
| `disable` | Removes normal enablement relationships |
| `daemon-reload` | Reloads systemd unit definitions |

**Remember:** A running service is not necessarily enabled at boot. An enabled service is not necessarily running now.

## 5. Service States

### Runtime States

- `active`: The unit is active.
- `inactive`: The unit is not active.
- `failed`: The unit has failed.
- `activating`: The unit is starting.
- `deactivating`: The unit is stopping.

### Enablement States

- `enabled`: Configured for automatic activation through installation relationships.
- `disabled`: Not enabled through those relationships.
- `static`: Has no normal enablement instructions.
- `masked`: Blocked from normal systemd activation.

Check the state:

```bash
systemctl is-active nginx
systemctl is-enabled nginx
systemctl status nginx
```

## 6. Installing and Managing Nginx

### Step 1: Identify the Linux Distribution

```bash
cat /etc/os-release
```

### Step 2: Install Nginx

**RHEL / Rocky Linux / AlmaLinux / Amazon Linux:**

```bash
sudo dnf install nginx -y
```

On older systems:

```bash
sudo yum install nginx -y
```

**Ubuntu / Debian:**

```bash
sudo apt update
sudo apt install nginx -y
```

### Step 3: Start and Enable Nginx

```bash
sudo systemctl enable --now nginx
```

### Step 4: Verify the Service

```bash
systemctl status nginx
systemctl is-active nginx
systemctl is-enabled nginx
```

### Step 5: Verify the Process and Port

```bash
pgrep -a nginx
sudo ss -lntp | grep -E ':(80|443)\b'
```

### Step 6: Test the Web Server

```bash
curl -I http://localhost
```

A successful TCP connection or HTTP response does not automatically prove the application is healthy. Verify the expected HTTP status and application behavior.

## 7. Service Logs with journalctl

`journalctl` retrieves logs from the systemd journal.

### Common Commands

```bash
# All available logs for a service
sudo journalctl -u nginx

# Logs from the current boot
sudo journalctl -u nginx -b

# Last 50 log entries
sudo journalctl -u nginx -n 50

# Follow logs in real time
sudo journalctl -u nginx -f

# Logs from the last 30 minutes
sudo journalctl -u nginx --since "30 minutes ago"

# Error-level messages
sudo journalctl -u nginx -p err

# General journal disk usage
journalctl --disk-usage
```

Logs are often the best starting point when a service fails to start.

## 8. Troubleshooting Service Failures

### Standard Troubleshooting Workflow

```text
Service Failure
      |
      v
Check systemctl status
      |
      v
Inspect journalctl logs
      |
      v
Validate application configuration
      |
      v
Check permissions and dependencies
      |
      v
Check process and listening port
      |
      v
Inspect firewall and security controls
      |
      v
Fix the root cause
      |
      v
Restart or start the service
      |
      v
Verify application health
```

### Common Problems

| Problem | Possible Cause | Investigation |
|---|---|---|
| Unit not found | Incorrect name or missing unit | `systemctl list-unit-files` |
| Permission denied | Ownership, permissions, SELinux, or AppArmor | Inspect logs and security denials |
| Address already in use | Another process occupies the port | `sudo ss -lntp` |
| Service exits immediately | Application error or incorrect configuration | `journalctl` and application logs |
| Service repeatedly fails | Crash loop or start-rate limit | `systemctl status` and `systemctl show` |
| Service active but unreachable | Binding, firewall, routing, proxy, or application issue | `ss`, `curl`, and network diagnostics |

### Example: Nginx Port Conflict

```bash
sudo systemctl status nginx
sudo journalctl -u nginx -b -n 100 --no-pager
sudo ss -lntp | grep ':80'
sudo nginx -t
```

Identify the process using port 80 before deciding how to resolve the conflict. Do not terminate processes blindly.

## 9. Understanding systemd Unit Files

A systemd unit file defines how systemd manages a resource.

Common unit types include:

| Extension | Purpose |
|---|---|
| `.service` | Services and processes |
| `.timer` | Scheduled activation |
| `.socket` | Socket-based activation |
| `.mount` | Filesystem mount points |
| `.target` | Groups of units and system states |
| `.path` | Path-based activation |

Inspect a service unit:

```bash
systemctl cat nginx
systemctl show nginx
```

## 10. Creating a Custom systemd Service

Example: run a Python application as a managed service.

Create the unit:

```bash
sudo vi /etc/systemd/system/demoapp.service
```

Add the following configuration:

```ini
[Unit]
Description=Demo Python Application
After=network.target

[Service]
Type=simple
User=demoapp
Group=demoapp
WorkingDirectory=/opt/demo
ExecStart=/usr/bin/python3 /opt/demo/app.py
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```

This example assumes that the `demoapp` user, application directory, Python executable, script, and application dependencies already exist.

### Validate and Start the Service

```bash
sudo systemd-analyze verify /etc/systemd/system/demoapp.service
sudo systemctl daemon-reload
sudo systemctl enable --now demoapp
```

### Verify the Service

```bash
systemctl status demoapp
sudo journalctl -u demoapp -n 50 --no-pager
```

### Important Directives

| Directive | Purpose |
|---|---|
| `Description` | Human-readable description |
| `After` | Defines startup ordering |
| `Type` | Defines service startup behavior |
| `User` | Specifies the execution user |
| `Group` | Specifies the execution group |
| `WorkingDirectory` | Sets the working directory |
| `ExecStart` | Defines the application command |
| `Restart` | Controls restart behavior |
| `RestartSec` | Sets the delay before restart |
| `WantedBy` | Defines an installation relationship |

## 11. daemon-reload vs reload vs restart

| Command | Meaning |
|---|---|
| `systemctl daemon-reload` | Reloads systemd unit definitions |
| `systemctl reload nginx` | Requests Nginx to reload its configuration |
| `systemctl restart nginx` | Stops and starts Nginx |
| `systemctl reload-or-restart nginx` | Reloads if supported, otherwise restarts |
| `nginx -t` | Validates Nginx configuration |

Example:

```bash
sudo nginx -t && sudo systemctl reload nginx
```

If the configuration test fails, the reload is not executed.

After changing a systemd unit file, run `daemon-reload`. If the running application must pick up the changes, perform the appropriate reload or restart separately.

## 12. Service Dependencies and Targets

### Dependency Directives

| Directive | Purpose |
|---|---|
| `Wants=` | Establishes a weaker dependency |
| `Requires=` | Establishes a stronger dependency |
| `After=` | Defines startup ordering |
| `Before=` | Defines reverse startup ordering |
| `PartOf=` | Propagates certain stop and restart operations |

Inspect dependencies:

```bash
systemctl list-dependencies nginx
systemctl list-dependencies --reverse nginx
```

`After=network.target` does not itself start the network target or guarantee external connectivity.

### Common Targets

| Target | Purpose |
|---|---|
| `multi-user.target` | Normal multi-user server environment |
| `graphical.target` | Graphical environment |
| `rescue.target` | Rescue mode |
| `emergency.target` | Minimal emergency environment |
| `network-online.target` | Network-online synchronization target |

Check the default target:

```bash
systemctl get-default
systemctl list-units --type=target
```

## 13. Advanced systemd Administration

### Mask and Unmask

```bash
sudo systemctl mask nginx
sudo systemctl unmask nginx
```

Masking blocks normal systemd activation. It is stronger than disabling a service and should be used carefully.

### Inspect Restart Counters and Exit Status

```bash
systemctl show demoapp \
  -p NRestarts \
  -p Result \
  -p ExecMainStatus
```

### Reset a Failed Unit

After fixing the underlying issue:

```bash
sudo systemctl reset-failed demoapp
sudo systemctl start demoapp
```

### Use Drop-in Overrides

```bash
sudo systemctl edit nginx
```

Example:

```ini
[Service]
Environment="APP_ENV=production"
```

Then:

```bash
sudo systemctl daemon-reload
```

Restart the service if required for the changed setting to take effect.

Inspect the resulting configuration:

```bash
systemctl cat nginx
systemctl show nginx -p FragmentPath -p DropInPaths
```

## 14. Security and Hardening

### Run Services with Least Privilege

Avoid running custom applications as root when a dedicated service account is sufficient.

Example directives:

```ini
[Service]
User=demoapp
Group=demoapp
NoNewPrivileges=yes
PrivateTmp=yes
```

Additional hardening options include:

- `ProtectSystem=strict`
- `ProtectHome=yes`
- `ReadWritePaths=`

Test hardening changes because applications may require access to particular files, directories, or system resources.

### SELinux Troubleshooting

```bash
getenforce
sudo ausearch -m AVC -ts recent
```

Investigate security denials instead of disabling SELinux as a shortcut.

### Application Health Checks

```bash
systemctl is-active --quiet nginx &&
curl -fsS http://localhost/health
```

This example assumes the application implements the `/health` endpoint.

## 15. Cloud and DevOps Integration

Linux service management is essential for cloud infrastructure, automation, and application delivery.

### Cloud Engineering

- Bootstrap services using cloud-init.
- Configure automatic service startup on virtual machines.
- Verify service availability after instance reboot.
- Troubleshoot security groups, firewalls, and network bindings.

### CI/CD

- Deploy application artifacts.
- Validate configuration before applying changes.
- Reload or restart services as required.
- Perform post-deployment health checks.
- Roll back unsuccessful deployments.

### Ansible Automation

Example playbook tasks for a RHEL-family server:

```yaml
- name: Install Nginx
  ansible.builtin.dnf:
    name: nginx
    state: present

- name: Ensure Nginx is running and enabled
  ansible.builtin.systemd_service:
    name: nginx
    state: started
    enabled: true
```

Use Ansible handlers to reload or restart services only when relevant configuration changes.

### Monitoring