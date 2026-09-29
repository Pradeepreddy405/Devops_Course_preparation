# Linux Fundamentals & Architecture

Welcome to the **Linux Fundamentals & Architecture** section of the Cloud & DevOps learning path.

Linux is the foundation of modern **Cloud, DevOps, Containers, Kubernetes, CI/CD, and Infrastructure Engineering**. Before working with tools such as Docker, Kubernetes, Terraform, Jenkins, or AWS, you should understand how Linux systems actually work.

---

##  What You Will Learn

By completing this section, you will understand:

* What Linux is
* Linux distributions
* Linux architecture
* Kernel and user space
* Shell and command-line interface
* Filesystem hierarchy
* Processes and services
* Users, groups, and permissions
* Package management
* Networking fundamentals
* Storage fundamentals
* System monitoring
* Logs
* Environment variables
* Basic Bash scripting

---

# 1. What is Linux?

Linux is an **open-source, Unix-like operating system kernel**.

In everyday usage, the term "Linux" commonly refers to a complete operating system built around the Linux kernel, together with system utilities, libraries, package managers, and applications.

Examples of Linux distributions:

* Ubuntu
* Debian
* Red Hat Enterprise Linux (RHEL)
* Rocky Linux
* AlmaLinux
* Fedora
* Amazon Linux
* SUSE Linux Enterprise

### Why Linux is important in DevOps

Linux is widely used for:

* Cloud servers
* Web servers
* Application servers
* Containers
* Kubernetes nodes
* CI/CD systems
* Databases
* Infrastructure automation
* Monitoring systems

---

# 2. Linux Architecture

Linux can be understood as a layered architecture.

```text
┌──────────────────────────────────────────────┐
│              USER APPLICATIONS               │
│                                              │
│ Nginx │ Git │ Docker │ Python │ Java │ etc. │
├──────────────────────────────────────────────┤
│                    SHELL                     │
│                                              │
│       Bash │ Zsh │ sh │ Other Shells        │
├──────────────────────────────────────────────┤
│              SYSTEM LIBRARIES               │
│                                              │
│             glibc and others                │
├──────────────────────────────────────────────┤
│                LINUX KERNEL                 │
│                                              │
│ Process Management                           │
│ Memory Management                            │
│ File Systems                                 │
│ Networking                                   │
│ Device Drivers                               │
│ Security                                     │
├──────────────────────────────────────────────┤
│                   HARDWARE                   │
│                                              │
│ CPU │ RAM │ Disk │ Network │ Devices        │
└──────────────────────────────────────────────┘
```

---

# 3. Hardware

Hardware is the physical layer of the system.

Major components include:

* CPU
* RAM
* HDD / SSD
* Network Interface Card
* USB devices
* Other hardware devices

The Linux kernel manages access to these resources.

---

# 4. Linux Kernel

The **Linux kernel is the core component of the operating system**.

It acts as an interface between applications and hardware.

### Major responsibilities

#### Process Management

The kernel manages:

* Process creation
* CPU scheduling
* Process termination
* Signals
* Process states

#### Memory Management

The kernel manages:

* RAM
* Virtual memory
* Paging
* Swap
* Memory allocation

#### File System Management

The kernel provides mechanisms for:

* Files
* Directories
* File permissions
* Mounting filesystems
* Storage access

#### Networking

The kernel handles:

* Network interfaces
* TCP/IP
* Routing
* Sockets
* Network communication

#### Device Management

The kernel communicates with hardware through **device drivers**.

#### Security

The kernel provides mechanisms for:

* Users
* Groups
* Permissions
* Capabilities
* Access control

---

# 5. User Space vs Kernel Space

Linux separates applications from the kernel.

```text
                 USER SPACE
┌─────────────────────────────────┐
│                                 │
│ Bash │ Nginx │ Python │ Docker │
│                                 │
└───────────────┬─────────────────┘
                │
            System Calls
                │
┌───────────────▼─────────────────┐
│            KERNEL SPACE         │
│                                 │
│ Processes                       │
│ Memory                          │
│ Filesystems                     │
│ Networking                      │
│ Device Drivers                  │
│ Security                        │
└───────────────┬─────────────────┘
                │
┌───────────────▼─────────────────┐
│             HARDWARE            │
│                                 │
│ CPU │ RAM │ Disk │ Network      │
└─────────────────────────────────┘
```

### User Space

Applications normally execute in user space.

Examples:

```text
bash
nginx
python
git
docker
kubectl
```

### Kernel Space

The kernel operates with privileged access to system resources.

Applications request kernel services through **system calls**.

---

# 6. Shell

A shell provides a command-line interface for interacting with Linux.

Common shells:

* Bash
* Zsh
* sh
* Fish

Example:

```bash
ls
```

Conceptually:

```text
User
  ↓
Shell
  ↓
Command
  ↓
System Call
  ↓
Kernel
  ↓
Filesystem
```

---

# 7. System Calls

A **system call** is a mechanism through which a user-space program requests a service from the kernel.

Common categories include:

* Process creation
* File operations
* Memory management
* Networking
* Device operations

Conceptually:

```text
Application
     ↓
System Call
     ↓
Linux Kernel
     ↓
Hardware / Resource
```

You don't need to memorize system calls initially, but you should understand their purpose.

---

# 8. Linux Filesystem

Linux uses a hierarchical filesystem.

The top-level directory is:

```text
/
```

Important directories:

```text
/
├── bin
├── boot
├── dev
├── etc
├── home
├── lib
├── media
├── mnt
├── opt
├── proc
├── root
├── run
├── sbin
├── srv
├── sys
├── tmp
├── usr
└── var
```

### Important directories

| Directory | Purpose                              |
| --------- | ------------------------------------ |
| `/`       | Root of the filesystem               |
| `/home`   | Users' home directories              |
| `/root`   | Root user's home directory           |
| `/etc`    | System and application configuration |
| `/var`    | Variable data such as logs           |
| `/tmp`    | Temporary files                      |
| `/usr`    | User-space programs and libraries    |
| `/bin`    | Essential user commands              |
| `/sbin`   | System administration commands       |
| `/dev`    | Device files                         |
| `/proc`   | Process and kernel information       |
| `/sys`    | Kernel/device information            |
| `/boot`   | Boot-related files                   |
| `/opt`    | Optional/add-on software             |

---

# 9. Users and Groups

Linux is a multi-user operating system.

Every user has:

* Username
* UID
* Primary group
* Home directory
* Shell

Example:

```bash
id
```

Example output:

```text
uid=1000(user) gid=1000(user) groups=1000(user)
```

Important files:

```text
/etc/passwd
/etc/shadow
/etc/group
```

---

# 10. File Permissions

Linux permissions control access to files and directories.

Example:

```text
-rwxr-xr--
```

Permissions are divided into:

```text
Owner     Group     Others
 rwx       r-x       r--
```

Where:

```text
r = read
w = write
x = execute
```

Example:

```bash
chmod 755 script.sh
```

Result:

```text
Owner  → rwx
Group  → r-x
Others → r-x
```

---

# 11. Processes

A **process is a running instance of a program**.

Example:

```bash
ps
```

Useful commands:

```bash
ps aux
top
htop
pgrep
kill
```

Important concepts:

* PID
* PPID
* Parent process
* Child process
* Foreground process
* Background process
* Signals
* Process states

Example:

```bash
ps -ef
```

---

# 12. Services

Linux servers commonly run background services.

Examples:

* Nginx
* SSH
* Docker
* Cron
* Kubernetes components

On systems using `systemd`:

```bash
systemctl status nginx
```

Start:

```bash
systemctl start nginx
```

Stop:

```bash
systemctl stop nginx
```

Enable at boot:

```bash
systemctl enable nginx
```

---

# 13. Package Management

Linux distributions provide package managers.

### Debian / Ubuntu

```bash
apt update
apt install nginx
apt remove nginx
```

### RHEL / Rocky / AlmaLinux / Fedora

```bash
dnf install nginx
dnf remove nginx
dnf update
```

Package management is essential for server administration and automation.

---

# 14. Environment Variables

Environment variables provide configuration information to processes.

View variables:

```bash
env
```

View a specific variable:

```bash
echo $PATH
```

Set a variable:

```bash
export APP_ENV=production
```

Example:

```text
APP_ENV=production
DATABASE_HOST=db.example.com
```

Environment variables are heavily used in:

* Applications
* CI/CD pipelines
* Docker
* Kubernetes
* Cloud environments

---

# 15. Linux Networking Basics

Important networking concepts:

* IP address
* MAC address
* DNS
* Port
* TCP
* UDP
* Routing
* Firewall
* Network interface

Useful commands:

```bash
ip addr
ip route
ping
ss
curl
dig
nslookup
traceroute
```

Example:

```bash
ss -tulnp
```

This can help identify listening network ports.

---

# 16. Storage Fundamentals

Important Linux storage concepts:

```text
Disk
 ↓
Partition
 ↓
Filesystem
 ↓
Mount Point
 ↓
Directory
```

Useful commands:

```bash
lsblk
df -h
du -sh
mount
umount
blkid
```

Example:

```bash
df -h
```

shows filesystem space usage.

---

# 17. Logs

Logs are critical for troubleshooting Linux systems.

Common locations:

```text
/var/log/
```

Examples:

```bash
ls /var/log
```

For systemd systems:

```bash
journalctl
```

Follow logs:

```bash
journalctl -f
```

Logs help diagnose:

* Application failures
* Authentication problems
* Service failures
* Network problems
* System issues

---

# 18. Essential Linux Commands

### Navigation

```bash
pwd
ls
cd
```

### Files and directories

```bash
touch
mkdir
cp
mv
rm
find
```

### Viewing files

```bash
cat
less
head
tail
```

### Text processing

```bash
grep
awk
sed
cut
sort
uniq
wc
```

### System information

```bash
uname
hostname
uptime
free
df
lsblk
```

### Processes

```bash
ps
top
htop
kill
pgrep
```

### Networking

```bash
ip
ss
ping
curl
dig
```

### Permissions

```bash
chmod
chown
chgrp
umask
```

---

# 19. Bash Fundamentals

Bash scripting allows us to automate repetitive Linux tasks.

Example:

```bash
#!/bin/bash

echo "Starting application..."

systemctl start nginx

echo "Application started."
```

Important Bash concepts:

* Variables
* Conditions
* Loops
* Functions
* Arguments
* Exit codes
* Redirection
* Pipes
* Command substitution

---

# 20. Redirection and Pipes

Linux commands can be combined using redirection and pipes.

### Output redirection

```bash
ls > files.txt
```

### Append output

```bash
ls >> files.txt
```

### Pipe

```bash
ps aux | grep nginx
```

The output of one command becomes the input of another.

```text
Command 1
   ↓
   |
   ↓
Command 2
```

This is one of the most powerful concepts in Linux administration.

---

# 21. Linux Boot Process

A simplified Linux boot sequence:

```text
Power On
   ↓
BIOS / UEFI
   ↓
Bootloader
   ↓
Linux Kernel
   ↓
initramfs
   ↓
systemd
   ↓
System Services
   ↓
Login
```

Understanding the boot process becomes useful when troubleshooting servers that fail to start correctly.

---

# 22. Linux Architecture — DevOps Perspective

A DevOps engineer should understand Linux from this perspective:

```text
                 DEVOPS
                    │
                    ↓
              Linux Server
                    │
       ┌────────────┼────────────┐
       ↓            ↓            ↓
   Processes      Network      Storage
       │            │            │
       ↓            ↓            ↓
    Memory        Ports       Filesystem
       │            │            │
       └────────────┼────────────┘
                    ↓
                  Kernel
                    ↓
                 Hardware
```

This foundation supports:

```text
Linux
  ↓
Shell Scripting
  ↓
Git
  ↓
CI/CD
  ↓
Docker
  ↓
Kubernetes
  ↓
Terraform
  ↓
AWS / Cloud
  ↓
Production Infrastructure
```

---



---

##  Next Step

Once these fundamentals are comfortable, move to:

**Linux Command Line → Users & Permissions → Processes → Services → Networking → Storage → Logs → Bash Automation → Linux Troubleshooting → Docker → Kubernetes**
