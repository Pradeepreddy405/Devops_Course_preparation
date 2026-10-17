# Linux for Cloud & DevOps Engineers

![Linux](https://img.shields.io/badge/Linux-Operating%20System-FCC624?style=for-the-badge&logo=linux&logoColor=black)
![Cloud](https://img.shields.io/badge/Cloud-AWS%20%7C%20Azure%20%7C%20GCP-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)
![DevOps](https://img.shields.io/badge/DevOps-Automation-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)
![Shell](https://img.shields.io/badge/Shell-Bash-4EAA25?style=for-the-badge&logo=gnubash&logoColor=white)

A practical, beginner-friendly guide to understanding Linux from scratch, with a focus on **Cloud Engineering, DevOps, SRE, System Administration, and Kubernetes**.

## Table of Contents

1. [What Is Linux OS?](#1-what-is-linux-os)
2. [Linux vs GNU/Linux](#2-linux-vs-gnulinux)
3. [How Many Linux Distributions Are There?](#3-how-many-linux-distributions-are-there)
4. [Popular Linux Distributions](#4-popular-linux-distributions)
5. [Why Linux Is Widely Used in Cloud and DevOps](#5-why-linux-is-widely-used-in-cloud-and-devops)
6. [Linux Architecture](#6-linux-architecture)
7. [How Linux Architecture Works](#7-how-linux-architecture-works)
8. [Linux Boot Process](#8-linux-boot-process)
9. [Linux Filesystem Hierarchy](#9-linux-filesystem-hierarchy)
10. [Users, Groups, and Permissions](#10-users-groups-and-permissions)
11. [Processes and Services](#11-processes-and-services)
12. [Linux Networking](#12-linux-networking)
13. [Package Management](#13-package-management)
14. [Linux Storage and Disk Management](#14-linux-storage-and-disk-management)
15. [Linux Security Fundamentals](#15-linux-security-fundamentals)
16. [Linux Monitoring and Troubleshooting](#16-linux-monitoring-and-troubleshooting)
17. [Linux Shell Scripting and Automation](#17-linux-shell-scripting-and-automation)
18. [Linux in Real-World Cloud and DevOps](#18-linux-in-real-world-cloud-and-devops)
19. [Essential Linux Commands](#19-essential-linux-commands)
20. [Hands-On Labs](#20-hands-on-labs)
21. [Linux Learning Roadmap](#21-linux-learning-roadmap)
22. [Conclusion](#22-conclusion)

---

## 1. What Is Linux OS?

Linux is an open-source, Unix-like operating system kernel originally created by Linus Torvalds in 1991.

The Linux kernel manages communication between software and hardware, including CPU, memory, storage, devices, and networking.

A complete Linux operating system typically combines the Linux kernel with system utilities, libraries, package management, and applications.

### What does an operating system do?

An operating system provides an environment in which applications can execute and access computer resources safely.

Its responsibilities include:

- **Process management:** Creates, schedules, and terminates processes.
- **Memory management:** Allocates memory and manages virtual memory.
- **File management:** Provides filesystems and file access.
- **Device management:** Communicates with hardware through device drivers.
- **Security:** Enforces users, permissions, and isolation.
- **Networking:** Provides networking interfaces and protocol implementations.
- **Resource management:** Coordinates CPU, memory, disk, and other resources.

### Where is Linux used?

- Cloud servers and virtual machines
- Web servers and application servers
- Docker containers and Kubernetes nodes
- CI/CD runners and build agents
- Embedded systems and IoT devices
- Supercomputers and high-performance computing
- Android devices, which use the Linux kernel

---

## 2. Linux vs GNU/Linux

These terms are related but are not exactly the same.

| Term | Meaning |
|---|---|
| Linux kernel | Core software responsible for managing hardware and system resources |
| GNU utilities | Common command-line tools, compilers, libraries, and other system components |
| Linux distribution | An integrated operating system built around the Linux kernel with utilities, packages, and configuration |

For example, Ubuntu generally combines the Linux kernel with GNU utilities and many other open-source components.

When people say "Linux OS," they usually mean a complete Linux distribution rather than the kernel alone.

---

## 3. How Many Linux Distributions Are There?

There is **no single fixed number of Linux distributions**.

Hundreds of distributions and derivatives have existed, and new distributions, forks, and specialized variants continue to appear. The number depends on whether inactive projects, minor derivatives, and specialized systems are counted.

A Linux distribution is a packaged operating system built around the Linux kernel, with its own selection of software, package repositories, defaults, and support model.

### Major distribution families

| Family | Examples | Typical package format |
|---|---|---|
| Debian | Debian, Ubuntu | `.deb` |
| Red Hat | RHEL, Fedora, Rocky Linux, AlmaLinux | `.rpm` |
| SUSE | SLES, openSUSE | `.rpm` |
| Arch | Arch Linux, Manjaro | Pacman packages |
| Alpine | Alpine Linux | APK packages |
| Independent | Gentoo, NixOS | Distribution-specific |

**Important:** These distributions do not all use the same package manager or configuration conventions. Learn the family of the system you administer.

---

## 4. Popular Linux Distributions

### Ubuntu

- Beginner-friendly and widely documented.
- Common in cloud virtual machines, development environments, and containers.
- Uses APT and DEB packages.

### Red Hat Enterprise Linux (RHEL)

- Commercial enterprise Linux distribution.
- Used in organizations requiring vendor support and defined lifecycle policies.
- Uses DNF and RPM packages.

### Amazon Linux

- AWS-oriented Linux distribution.
- Commonly used for Amazon EC2 workloads and AWS-based infrastructure.
- Integrates with AWS environments and their operational tooling.

### Debian

- Known for stability and a large software repository.
- A foundation for Ubuntu.
- Commonly used on servers.

### Rocky Linux and AlmaLinux

- Enterprise-oriented distributions compatible with the RHEL ecosystem.
- Used for server workloads where that ecosystem is desirable.

### Alpine Linux

- Minimal Linux distribution with a small base footprint.
- Common in container images.
- Uses `apk` and commonly uses musl libc and BusyBox.

### Which distributions should a DevOps engineer learn first?

Start with **Ubuntu and a RHEL-family distribution**.

The fundamentals transfer between distributions, while package management, service configuration, security defaults, and some filesystem conventions can differ.

---

## 5. Why Linux Is Widely Used in Cloud and DevOps

First, an important correction: **Linux is not the only operating system used in Cloud and DevOps.**

Windows Server is used for Windows applications, Microsoft infrastructure, and other workloads. However, Linux is a major platform for cloud-native infrastructure, web services, automation, and containerized applications.

### 5.1 Open source and flexibility

Linux can be inspected, configured, automated, and customized. Organizations can select distributions and support models according to their requirements.

### 5.2 Automation

Linux supports powerful automation through shells, scripts, SSH, and configuration management tools.

Examples:

- Bash scripts
- Python automation
- Ansible playbooks
- Terraform provisioning workflows
- Jenkins and GitHub Actions runners

### 5.3 Cloud compatibility

Linux runs on virtual machines and many other compute environments across AWS, Microsoft Azure, and Google Cloud.

It supports common cloud workloads such as:

- Web and application servers
- Databases and caches
- Reverse proxies and load balancers
- Container hosts
- Monitoring agents
- Background workers

### 5.4 Containers and Kubernetes

Containers commonly use Linux kernel features such as:

- **Namespaces:** Isolate views of processes, networking, mounts, and other resources.
- **cgroups:** Account for and limit resource usage.
- **Capabilities:** Divide privileged operations into more granular permissions.
- **Seccomp:** Restrict permitted system calls.

These features help container runtimes isolate workloads. Containers are not complete virtual machines: they share the host kernel.

### 5.5 Performance and resource efficiency

Linux can run efficiently on a wide range of hardware and workload sizes. Actual performance depends on workload, configuration, kernel, and hardware—not simply on choosing Linux.

### 5.6 Cost and ecosystem

Many Linux distributions can be used without a per-instance operating system license fee, although enterprise support, cloud infrastructure, and other services can cost money.

### 5.7 Production troubleshooting

Linux provides extensive observability and troubleshooting tools for:

- CPU and memory utilization
- Disk capacity and I/O
- Network connectivity
- Processes and services
- System logs
- Security and permissions

These are essential skills for DevOps and Site Reliability Engineering (SRE).

---

## 6. Linux Architecture

Linux architecture describes how applications, system libraries, the kernel, and hardware interact.

A useful conceptual model has these layers:

```text
+----------------------------------------+
|            User Applications           |
| Web Apps | Nginx | Docker | Databases   |
+----------------------------------------+
|       Shells, Utilities & Libraries     |
| Bash | Core Utilities | glibc | Tools   |
+----------------------------------------+
|     System Calls / Kernel Interface     |
+----------------------------------------+
|              Linux Kernel               |
|                                        |
| Process Management                     |
| Memory Management                      |
| Filesystems / VFS                      |
| Networking                             |
| Device Drivers                         |
| Security                               |
+----------------------------------------+
|                Hardware                |
| CPU | RAM | Disk | NIC | Devices        |
+----------------------------------------+
```

This is a simplified conceptual diagram. System calls are an interface through which user-space programs request kernel services; they are not a separate physical component.

### 6.1 Hardware

The physical or virtual resources available to the operating system.

Examples:

- CPU
- RAM
- SSD or HDD
- Network interface
- Virtual devices provided by a hypervisor

### 6.2 Linux kernel

The core of the operating system.

Its major responsibilities include:

- Process scheduling
- Virtual memory
- Filesystem operations
- Networking
- Device drivers
- Security and access control
- Interrupt handling

### 6.3 System call interface

Applications cannot directly perform arbitrary privileged hardware operations. They request supported services through system calls.

Examples include:

- `open()` — open a file
- `read()` — read data
- `write()` — write data
- `fork()` — create a process
- `execve()` — execute a program
- `socket()` — create a networking endpoint

These are Linux/Unix system interfaces; the exact behavior and availability depend on the call and environment.

### 6.4 System libraries

Libraries provide reusable functionality and convenient interfaces to system services.

For example, the GNU C Library (glibc) supplies many standard C library functions and wrappers for system calls on common Linux systems.

Not every Linux distribution uses glibc. Alpine Linux commonly uses musl libc.

### 6.5 Shell

A shell provides an interface for running commands and scripts.

Examples:

- Bash
- Zsh
- Dash

A shell interprets commands and can launch programs, connect pipelines, redirect input/output, and automate tasks.

### 6.6 User-space applications

Applications run outside the kernel's privileged execution context.

Examples:

- Nginx
- Apache HTTP Server
- Python
- Docker CLI
- Monitoring agents
- Database services

Applications request operating system services through libraries and system calls.

---

## 7. How Linux Architecture Works

Consider what happens when a user requests a webpage served by Nginx on a Linux server.

```text
User / Browser
      |
      | HTTP/HTTPS request
      v
Network Interface
      |
      v
Linux Network Stack
      |
      v
Nginx Process
      |
      | Request file or application data
      v
System Call Interface
      |
      v
Linux Kernel
      |
      | Filesystem, memory, networking
      v
Storage / RAM / Network
      |
      v
Response sent to the browser
```

### Step-by-step explanation

1. A client sends a network request to the server.
2. The network interface receives packets, and the Linux networking stack processes them.
3. The kernel makes the relevant data available to the Nginx process through networking interfaces.
4. Nginx processes the request in user space.
5. If required, Nginx requests file or other resources through system calls and libraries.
6. The kernel handles the relevant filesystem, memory, and networking operations.
7. Nginx sends the response, and the networking stack transmits it to the client.

The precise sequence depends on the application, caching, configuration, and whether an upstream application server is involved.

### Example: What happens when you run `ls`?

```bash
ls -l /var/log
```

1. Your shell parses the command.
2. The shell locates and starts the `ls` executable.
3. `ls` requests directory information through operating system interfaces.
4. The kernel checks permissions and obtains the directory information.
5. The program formats and prints the results to the terminal.

### Example: What happens when you run `docker ps`?

1. The shell starts the Docker CLI.
2. The CLI communicates with the Docker daemon through its configured API endpoint, commonly a Unix socket on a local Linux host.
3. The daemon retrieves container information from the container runtime and its state.
4. The CLI displays the results.

The Docker CLI is not itself the Linux kernel, and `docker ps` does not create a container.

---

## 8. Linux Boot Process

Understanding boot helps troubleshoot servers that fail to start correctly.

```text
Power On
   |
   v
BIOS / UEFI
   |
   v
Bootloader (commonly GRUB)
   |
   v
Linux Kernel + initramfs
   |
   v
PID 1 (commonly systemd)
   |
   v
Services and Targets
   |
   v
System Ready
```

### Boot stages

1. **BIOS/UEFI:** Initializes the platform and selects a boot device.
2. **Bootloader:** Loads the kernel and initial RAM filesystem.
3. **Kernel:** Initializes hardware support, memory management, and other kernel subsystems.
4. **initramfs:** Provides an early userspace environment needed to prepare the real root filesystem when required.
5. **PID 1:** Starts the main userspace initialization process, commonly systemd.
6. **Services:** Required services and targets are started.
7. **Login or workload readiness:** The system becomes available for administration and applications.

### Useful commands

```bash
uptime
systemctl get-default
systemctl --failed
journalctl -b
journalctl -p err -b
```

These commands help inspect uptime, the default systemd target, failed units, and boot logs.

---

## 9. Linux Filesystem Hierarchy

Linux organizes files and directories under a single root directory: `/`.

| Directory | Purpose |
|---|---|
| `/` | Root of the filesystem hierarchy |
| `/etc` | System-wide configuration |
| `/var` | Variable data, logs, caches, and service state |
| `/home` | Regular users' home directories |
| `/root` | Root user's home directory |
| `/tmp` | Temporary files |
| `/usr` | User-space programs, libraries, and shared data |
| `/opt` | Optional or third-party software |
| `/bin` | Essential command binaries; often merged into `/usr/bin` |
| `/sbin` | System administration binaries; often merged into `/usr/sbin` |
| `/dev` | Device nodes |
| `/proc` | Virtual filesystem exposing process and kernel information |
| `/sys` | Kernel device and subsystem information |
| `/run` | Runtime state, often cleared during boot |
| `/boot` | Bootloader files and kernel-related files |
| `/mnt` | Common temporary mount point |
| `/media` | Common mount point for removable media |

### DevOps examples

- Application configuration: `/etc/nginx/`
- Common system logs: `/var/log/`
- Web content: `/var/www/html/`
- Temporary files: `/tmp/`
- Process information: `/proc/`
- Runtime sockets and PID files: often under `/run/`

Actual paths vary by distribution and application configuration.

### Hands-on commands

```bash
ls -la /
ls -la /etc
ls -la /var/log
df -h
findmnt
```

---

## 10. Users, Groups, and Permissions

Linux uses identity and access-control mechanisms to regulate access to files and resources.

### Common users

- `root`: The superuser with extensive administrative privileges.
- Regular users: Human or application users with assigned permissions.
- Service accounts: Identities used to run services and workloads.

### File permission example

```bash
-rwxr-xr--
```

This represents:

- Owner: read, write, execute
- Group: read, execute
- Others: read

### Common commands

```bash
whoami
id
who
groups
ls -l
chmod 750 script.sh
chown user:group script.sh
sudo command
```

### Numeric permissions

| Permission | Value |
|---|---:|
| Read (`r`) | 4 |
| Write (`w`) | 2 |
| Execute (`x`) | 1 |

For example, `750` means owner `rwx`, group `r-x`, and others no permissions.

### Real-world DevOps use cases

- Granting Nginx access to website files.
- Ensuring a deployment user can update an application.
- Preventing unauthorized access to secrets and configuration.
- Running applications without unnecessary root privileges.
- Debugging `Permission denied` errors.

---

## 11. Processes and Services

A **process** is a running instance of a program. A **service** is a program or capability managed to provide ongoing functionality; on systemd-based systems, services are commonly represented by service units.

### Process management

```bash
ps aux
top
pgrep nginx
pstree -p
kill -TERM <PID>
```

Use `kill -TERM` to request graceful termination. Do not terminate processes blindly, especially on production systems.

### Service management with systemd

```bash
systemctl status nginx
systemctl start nginx
systemctl stop nginx
systemctl restart nginx
systemctl reload nginx
systemctl enable nginx
systemctl is-active nginx
systemctl --failed
```

- `start`: Starts a service now.
- `enable`: Configures it to start automatically at boot.
- `restart`: Stops and starts it again.
- `reload`: Requests configuration reload when supported.

A service can be enabled but currently stopped, or active but not enabled at boot.

### Logs

```bash
journalctl -u nginx
journalctl -u nginx -f
journalctl -b
```

These are essential commands for diagnosing service failures and startup problems.

---

## 12. Linux Networking

Cloud and DevOps engineers must understand how applications communicate over networks.

### Core concepts

- IP addresses and subnets
- Routing tables
- DNS resolution
- TCP and UDP
- Listening ports
- Firewalls
- Network interfaces
- SSH
- HTTP and HTTPS
- Load balancing and reverse proxies

### Useful commands

```bash
ip addr
ip route
ip link
ss -tulpn
ping 8.8.8.8
curl -I https://example.com
getent hosts example.com
dig example.com
traceroute example.com
```

Some commands, such as `dig` and `traceroute`, may require installing additional packages.

### Troubleshooting checklist

When an application is unreachable, check:

1. Is the service running?
2. Is the expected port listening?
3. Is the application bound to the correct IP address?
4. Does local access work?
5. Can the client reach the server?
6. Are Linux firewall rules blocking traffic?
7. Are cloud security groups, network ACLs, routes, or load balancers configured correctly?
8. Does DNS resolve to the expected address?
9. Is TLS configured correctly?

**Important:** A port listening locally does not guarantee external reachability. Cloud networking and host firewall rules must also allow the traffic.

---

## 13. Package Management

Package managers install, update, and remove software while managing dependencies.

| Distribution | Package manager | Examples |
|---|---|---|
| Ubuntu / Debian | APT | `apt install nginx` |
| RHEL-family | DNF | `dnf install nginx` |
| Alpine | APK | `apk add nginx` |
| Arch Linux | Pacman | `pacman -S nginx` |

### Ubuntu/Debian example

```bash
sudo apt update
sudo apt install nginx
sudo systemctl enable --now nginx
sudo systemctl status nginx
```

### RHEL-family example

```bash
sudo dnf install nginx
sudo systemctl enable --now nginx
sudo systemctl status nginx
```

Package availability and repository configuration vary by distribution and version.

### Why this matters in DevOps

- Installing application dependencies.
- Creating repeatable server builds.
- Patching vulnerabilities.
- Maintaining consistent environments.
- Building Docker images with predictable dependencies.

---

## 14. Linux Storage and Disk Management

Storage administration is essential for databases, logs, application data, and cloud instances.

### Important concepts

- Disks and partitions
- Filesystems such as ext4 and XFS
- Mount points
- Persistent mounts
- Inodes
- Disk capacity and utilization
- Logical Volume Manager (LVM)
- Swap
- Cloud block storage

### Useful commands

```bash
lsblk
blkid
df -h
df -i
du -sh /var/log
findmnt
free -h
```

### Real-world troubleshooting

If a server cannot write logs or applications fail, investigate:

```bash
df -h
df -i
du -xhd1 /var 2>/dev/null
```

A filesystem can run out of available space or inodes. These are different failure modes.

On cloud platforms, also verify the size of the attached volume and whether the partition and filesystem have been expanded after increasing the volume size.

**Safety:** Disk formatting, partition changes, and filesystem resizing can cause data loss if performed incorrectly. Confirm the target device and backup critical data before making changes.

---

## 15. Linux Security Fundamentals

Linux security is not just about file permissions. It includes authentication, authorization, updates, network exposure, service configuration, and auditing.

### Essential practices

- Use SSH keys and protect private keys.
- Restrict root access and unnecessary privileges.
- Apply security patches.
- Disable unused services.
- Configure host firewalls.
- Enforce least privilege.
- Protect credentials and secrets.
- Review logs and authentication attempts.
- Use SELinux or AppArmor where appropriate.
- Avoid exposing administrative ports publicly.

### Useful commands

```bash
sudo -l
ss -tulpn
systemctl --failed
last
id
ls -l
```

Additional security tools and log locations vary by distribution.

### SSH best practices

- Prefer key-based authentication where appropriate.
- Protect private keys with suitable permissions and passphrases.
- Restrict access using firewall rules and account policies.
- Test a new SSH configuration before closing your existing session.
- Avoid disabling password authentication until alternative access has been tested.

---

## 16. Linux Monitoring and Troubleshooting

Monitoring provides visibility into system health and helps identify failures before they cause major outages.

### CPU and processes

```bash
uptime
top
ps aux --sort=-%cpu | head
```

### Memory

```bash
free -h
vmstat 1
```

### Disk

```bash
df -h
df -i
iostat -xz 1
```

`iostat` may require the `sysstat` package.

### Network

```bash
ip addr
ip route
ss -s
ss -tulpn
```

### Logs

```bash
journalctl -b
journalctl -p warning
dmesg -T
```

### A practical troubleshooting workflow

1. Identify the actual symptom and affected service.
2. Check the service status.
3. Inspect recent logs.
4. Check CPU, memory, disk, and network usage.
5. Verify permissions and configuration.
6. Test connectivity and dependencies.
7. Identify the root cause before making changes.
8. Apply the smallest safe fix.
9. Verify recovery and monitor for recurrence.
10. Document the root cause and prevention steps.

Useful monitoring tools include Prometheus, Grafana, Datadog, and New Relic.

---

## 17. Linux Shell Scripting and Automation

Shell scripting allows engineers to automate repetitive administrative tasks.

### Example: Check whether a service is active

```bash
#!/usr/bin/env bash

SERVICE="nginx"

if systemctl is-active --quiet "$SERVICE"; then
    echo "$SERVICE is running"
else
    echo "$SERVICE is not running"
    exit 1
fi
```

Save as `check_service.sh` and run:

```bash
chmod +x check_service.sh
./check_service.sh
```

### Important shell scripting concepts

- Variables
- Conditions
- Loops
- Functions
- Exit codes
- Input/output redirection
- Pipes
- Text processing
- Error handling
- Logging
- Scheduling jobs with cron

### Automation in DevOps

Linux scripting skills are useful for:

- Health checks
- Log rotation and cleanup
- Automated backups
- Deployment scripts
- User and permission management
- Scheduled maintenance
- CI/CD pipeline steps

Learn Bash first for common Linux administration tasks, then Python for more complex automation and integrations.

---

## 18. Linux in Real-World Cloud and DevOps

### Example 1: Hosting a web application on AWS

1. Launch a Linux EC2 instance.
2. Configure SSH access and security groups.
3. Install Nginx or another web server.
4. Deploy the application.
5. Configure service management and logs.
6. Add monitoring and alerting.
7. Apply security patches and backups.

### Example 2: Docker and Kubernetes

1. Linux provides the kernel environment.
2. The container runtime creates and manages containers.
3. Kernel namespaces and cgroups support isolation and resource management.
4. Kubernetes schedules workloads onto nodes and manages their desired state.
5. Engineers troubleshoot container logs, networking, permissions, storage, and resource limits.

### Example 3: CI/CD

1. A developer pushes code to Git.
2. A CI/CD runner executes the pipeline.
3. The runner uses Linux commands and tools to build and test the application.
4. The pipeline creates an artifact or container image.
5. The image is published to a registry.
6. A deployment workflow updates the target environment.

### Example 4: Production incident

An application becomes unavailable because the root filesystem is full.

A Linux engineer investigates with `df -h`, checks logs and large directories, identifies the cause, safely restores available space, verifies the service, and introduces monitoring or retention policies to prevent recurrence.

Linux knowledge makes it possible to diagnose the operating system beneath the application and infrastructure tooling.

---

## 19. Essential Linux Commands

| Category | Commands |
|---|---|
| Navigation | `pwd`, `ls`, `cd` |
| Files and directories | `touch`, `mkdir`, `cp`, `mv`, `rm` |
| Viewing files | `cat`, `less`, `head`, `tail` |
| Searching | `find`, `grep`, `locate` |
| Text processing | `sort`, `uniq`, `cut`, `awk`, `sed` |
| Users and permissions | `id`, `whoami`, `chmod`, `chown`, `sudo` |
| Processes | `ps`, `top`, `pgrep`, `kill` |
| Services | `systemctl`, `journalctl` |
| Networking | `ip`, `ss`, `curl`, `ssh`, `dig` |
| Storage | `lsblk`, `df`, `du`, `findmnt` |
| System information | `uname`, `hostnamectl`, `uptime` |
| Packages | `apt`, `dnf`, `rpm`, `apk` |
| Scheduling | `crontab`, `systemctl list-timers` |
| Archives | `tar`, `gzip`, `zip`, `unzip` |
| Automation | `bash`, `python3` |

Do not memorize commands in isolation. Learn what they do, when to use them, how to interpret their output, and what risks they introduce.

---

## 20. Hands-On Labs

Practice these labs on a disposable virtual machine or cloud instance. Avoid running destructive commands on production systems.

### Lab 1: Linux system information

```bash
cat /etc/os-release
uname -r
hostnamectl
uptime
lscpu
free -h
```

**Goal:** Identify the distribution, kernel, CPU, memory, and uptime.

### Lab 2: Filesystem exploration

```bash
pwd
ls -la /
find /etc -maxdepth 1 -type f
df -h
findmnt
```

**Goal:** Understand the filesystem hierarchy and mounted filesystems.

### Lab 3: Users and permissions

```bash
whoami
id
touch demo.txt
ls -l demo.txt
chmod 640 demo.txt
ls -l demo.txt
```

**Goal:** Understand ownership and permission changes.

### Lab 4: Process and service management

```bash
ps aux | head
systemctl status ssh
systemctl --failed
journalctl -b -p err
```

The SSH service name may differ by distribution; some use `sshd`.

**Goal:** Inspect running processes, service state, and system errors.

### Lab 5: Networking

```bash
ip addr
ip route
ss -tulpn
curl -I https://example.com
getent hosts example.com
```

**Goal:** Understand interfaces, routes, listening ports, DNS, and HTTP responses.

### Lab 6: Install and verify Nginx

On Ubuntu/Debian:

```bash
sudo apt update
sudo apt install nginx
sudo systemctl enable --now nginx
systemctl is-active nginx
curl -I http://localhost
```

**Goal:** Install a package, manage its service, and verify the local HTTP response.

### Lab 7: Disk troubleshooting

```bash
df -h
df -i
du -xhd1 /var 2>/dev/null
```

**Goal:** Identify filesystem capacity and inode usage.

### Lab 8: Schedule a task with cron

Create a simple test job:

```bash
mkdir -p "$HOME/cron-lab"
crontab -e
```

Add the following line, replacing `YOUR_USERNAME` with the appropriate Linux username:

```cron
* * * * * /usr/bin/date >> /home/YOUR_USERNAME/cron-lab/timestamps.log 2>&1
```

Verify after a minute or two:

```bash
crontab -l
cat "$HOME/cron-lab/timestamps.log"
```

The example assumes the user's home directory is `/home/YOUR_USERNAME`. Check the actual home directory with `echo "$HOME"` before configuring the job. Cron must be installed and its service running.

**Goal:** Learn scheduled automation, cron syntax, and basic verification.

### Lab 9: Shell scripting

Write a script that checks whether a service is running, reports the result, and returns an appropriate exit code.

**Goal:** Learn variables, conditionals, exit codes, and automation.

### Lab 10: Troubleshoot a web service

Investigate a deliberately stopped service in a disposable environment:

```bash
systemctl status nginx
journalctl -u nginx -n 50 --no-pager
ss -tulpn
curl -I http://localhost
```

Restart it only after understanding the cause.

**Goal:** Develop a structured troubleshooting approach.

---

## 21. Linux Learning Roadmap

Follow this sequence to build skills relevant to Cloud and DevOps roles.

- [ ] **Module 1 — Fundamentals:** Linux distributions, kernel, shell, terminal, filesystem hierarchy.
- [ ] **Module 2 — Command Line:** Navigation, file operations, searching, pipes, redirection, text processing.
- [ ] **Module 3 — Users and Permissions:** Users, groups, ownership, sudo, access controls.
- [ ] **Module 4 — Processes and Services:** Process states, signals, systemd, service management, logs.
- [ ] **Module 5 — Networking:** TCP/IP, DNS, ports, routing, SSH, HTTP/HTTPS, firewalls.
- [ ] **Module 6 — Storage:** Partitions, filesystems, mounting, LVM, disk and inode troubleshooting.
- [ ] **Module 7 — Package Management:** APT, DNF, repositories, upgrades, dependencies.
- [ ] **Module 8 — Security:** SSH hardening, patching, least privilege, SELinux/AppArmor fundamentals.
- [ ] **Module 9 — Shell Scripting:** Variables, conditions, loops, functions, exit codes, cron.
- [ ] **Module 10 — Monitoring:** CPU, memory, disk, network, logs, metrics, alerts.
- [ ] **Module 11 — Cloud Linux:** EC2 access, security groups, storage volumes, cloud-init, cloud troubleshooting.
- [ ] **Module 12 — Containers:** Docker, namespaces, cgroups, images, volumes, container networking.
- [ ] **Module 13 — Kubernetes:** Linux worker nodes, container runtime, pods, services, resource limits, troubleshooting.
- [ ] **Module 14 — CI/CD and IaC:** Linux build agents, Git, Jenkins/GitHub Actions, Ansible, Terraform.
- [ ] **Module 15 — Production Operations:** Incident response, root-cause analysis, backups, recovery, patching, automation.

### Suggested practice routine

1. Learn a concept.
2. Run the relevant commands in a Linux VM.
3. Break a disposable environment safely.
4. Diagnose the problem using logs and system tools.
5. Fix it and verify the result.
6. Document the steps and push your lab notes to GitHub.

The objective is not merely to memorize Linux commands. It is to **understand system behavior, troubleshoot failures, automate repetitive work, and operate production infrastructure safely**.

---

## 22. Conclusion

Linux is a foundational skill for Cloud Engineering, DevOps, SRE, and container platforms. Understanding the kernel, processes, permissions, networking, filesystems, services, security, and automation helps engineers solve problems that higher-level tools cannot always explain.

Start with the fundamentals, practice daily, and gradually move toward cloud infrastructure, CI/CD, containers, Kubernetes, monitoring, and production troubleshooting.



---

**Topics:** Linux, Linux Administration, Cloud Computing, DevOps, AWS, SRE, Bash Scripting, Docker, Kubernetes, System Administration, Automation, Linux Troubleshooting

