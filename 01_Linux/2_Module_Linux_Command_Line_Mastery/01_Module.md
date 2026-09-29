#  Linux Commands for DevOps & Cloud

Linux is one of the core operating systems used in **Cloud, DevOps, SRE, System Administration, Containers, and Kubernetes environments**.

The goal of this guide is not to memorize hundreds of commands. The goal is to understand the commands required to **operate, troubleshoot, automate, secure, and manage Linux servers in real-world environments**.

---

# 1. Basic Linux Commands

| Command    | Description                                    | Example              |
| ---------- | ---------------------------------------------- | -------------------- |
| `pwd`      | Displays the current working directory         | `pwd`                |
| `ls`       | Lists files and directories                    | `ls -la`             |
| `cd`       | Changes the current directory                  | `cd /var/log`        |
| `mkdir`    | Creates a directory                            | `mkdir devops`       |
| `rmdir`    | Removes an empty directory                     | `rmdir devops`       |
| `touch`    | Creates an empty file or updates its timestamp | `touch file.txt`     |
| `cp`       | Copies files or directories                    | `cp file.txt /tmp/`  |
| `mv`       | Moves or renames files/directories             | `mv old.txt new.txt` |
| `rm`       | Removes files or directories                   | `rm file.txt`        |
| `clear`    | Clears the terminal screen                     | `clear`              |
| `history`  | Displays previously executed commands          | `history`            |
| `man`      | Displays the manual/documentation of a command | `man ls`             |
| `whoami`   | Displays the current user                      | `whoami`             |
| `hostname` | Displays the system hostname                   | `hostname`           |

---

# 2. File Viewing Commands

| Command   | Description                              | Example                   |
| --------- | ---------------------------------------- | ------------------------- |
| `cat`     | Displays file contents                   | `cat file.txt`            |
| `less`    | Views large files page by page           | `less application.log`    |
| `more`    | Displays file contents page by page      | `more file.txt`           |
| `head`    | Displays the beginning of a file         | `head file.txt`           |
| `tail`    | Displays the end of a file               | `tail file.txt`           |
| `tail -f` | Continuously monitors new log entries    | `tail -f application.log` |
| `nl`      | Displays file contents with line numbers | `nl file.txt`             |

### DevOps Example

Monitor an application log in real time:

```bash
tail -f /var/log/application.log
```

---

# 3. Finding Files and Directories

| Command   | Description                                 | Example                       |
| --------- | ------------------------------------------- | ----------------------------- |
| `find`    | Searches for files/directories              | `find /var/log -name "*.log"` |
| `locate`  | Quickly searches for files using a database | `locate nginx.conf`           |
| `which`   | Shows the location of an executable         | `which python`                |
| `whereis` | Locates binary, source and manual files     | `whereis nginx`               |
| `file`    | Identifies the type of a file               | `file script.sh`              |
| `stat`    | Displays detailed file information          | `stat file.txt`               |

---

# 4. Disk Usage and Storage

| Command  | Description                        | Example                 |
| -------- | ---------------------------------- | ----------------------- |
| `df`     | Displays filesystem disk usage     | `df -h`                 |
| `du`     | Displays directory/file disk usage | `du -sh /var/log`       |
| `lsblk`  | Lists block devices                | `lsblk`                 |
| `blkid`  | Displays block device attributes   | `blkid`                 |
| `mount`  | Mounts a filesystem                | `mount /dev/sdb1 /data` |
| `umount` | Unmounts a filesystem              | `umount /data`          |
| `fdisk`  | Manages disk partitions            | `fdisk -l`              |
| `parted` | Creates/manages disk partitions    | `parted -l`             |
| `mkfs`   | Creates a filesystem               | `mkfs.ext4 /dev/sdb1`   |

### Important Difference

```text
df  → How much space is available?
du  → Which files/directories are consuming space?
```

Example:

```bash
df -h
du -sh /var/*
```

---

# 5. Linux Users and Groups

| Command    | Description                              | Example                    |
| ---------- | ---------------------------------------- | -------------------------- |
| `id`       | Displays user and group information      | `id john`                  |
| `whoami`   | Displays the current user                | `whoami`                   |
| `who`      | Shows currently logged-in users          | `who`                      |
| `w`        | Shows logged-in users and their activity | `w`                        |
| `last`     | Shows login history                      | `last`                     |
| `useradd`  | Creates a user                           | `useradd john`             |
| `usermod`  | Modifies a user                          | `usermod -aG docker john`  |
| `userdel`  | Deletes a user                           | `userdel john`             |
| `passwd`   | Changes a user's password                | `passwd john`              |
| `groupadd` | Creates a group                          | `groupadd devops`          |
| `groupmod` | Modifies a group                         | `groupmod -n cloud devops` |
| `groupdel` | Deletes a group                          | `groupdel devops`          |
| `groups`   | Displays groups of a user                | `groups john`              |

---

# 6. File Permissions

Linux permissions are based on:

```text
User     Group     Others
 ↓        ↓          ↓
rwx      rwx        rwx
```

Where:

```text
r = read
w = write
x = execute
```

| Command | Description                  | Example                      |
| ------- | ---------------------------- | ---------------------------- |
| `ls -l` | Displays file permissions    | `ls -l file.txt`             |
| `chmod` | Changes file permissions     | `chmod 755 script.sh`        |
| `chown` | Changes file ownership       | `chown john:devops file.txt` |
| `chgrp` | Changes group ownership      | `chgrp devops file.txt`      |
| `umask` | Controls default permissions | `umask`                      |

### Common Permissions

```bash
chmod 755 script.sh
```

```text
7 → User    → rwx
5 → Group   → r-x
5 → Others  → r-x
```

```bash
chmod 644 file.txt
```

```text
6 → User    → rw-
4 → Group   → r--
4 → Others  → r--
```

---

# 7. Process Management

| Command   | Description                           | Example               |
| --------- | ------------------------------------- | --------------------- |
| `ps`      | Displays running processes            | `ps aux`              |
| `top`     | Provides real-time process monitoring | `top`                 |
| `htop`    | Interactive process monitoring        | `htop`                |
| `pgrep`   | Finds process IDs by name             | `pgrep nginx`         |
| `pkill`   | Terminates processes by name          | `pkill nginx`         |
| `kill`    | Sends a signal to a process           | `kill 1234`           |
| `killall` | Terminates processes by name          | `killall nginx`       |
| `jobs`    | Displays shell jobs                   | `jobs`                |
| `bg`      | Sends a job to the background         | `bg %1`               |
| `fg`      | Brings a background job to foreground | `fg %1`               |
| `nohup`   | Runs a command immune to hangups      | `nohup ./script.sh &` |
| `nice`    | Starts a process with a priority      | `nice -n 10 command`  |
| `renice`  | Changes process priority              | `renice 10 -p 1234`   |

### Find a process

```bash
ps aux | grep nginx
```

### Find the PID

```bash
pgrep nginx
```

---

# 8. System Monitoring

| Command  | Description                                 | Example    |
| -------- | ------------------------------------------- | ---------- |
| `uptime` | Shows system uptime and load average        | `uptime`   |
| `free`   | Displays memory usage                       | `free -h`  |
| `vmstat` | Displays memory, process and CPU statistics | `vmstat`   |
| `iostat` | Displays CPU and disk I/O statistics        | `iostat`   |
| `sar`    | Collects and reports system activity        | `sar`      |
| `lscpu`  | Displays CPU information                    | `lscpu`    |
| `lsmem`  | Displays memory information                 | `lsmem`    |
| `uname`  | Displays system/kernel information          | `uname -a` |
| `dmesg`  | Displays kernel messages                    | `dmesg`    |

### Common troubleshooting commands

```bash
uptime
free -h
df -h
top
```

---

# 9. Networking Commands

Networking is critical for **AWS, Docker, Kubernetes, CI/CD, and production troubleshooting**.

| Command      | Description                                       | Example                         |
| ------------ | ------------------------------------------------- | ------------------------------- |
| `ip`         | Displays/configures network interfaces and routes | `ip addr`                       |
| `ss`         | Displays network sockets and connections          | `ss -tulpn`                     |
| `ping`       | Tests network connectivity                        | `ping google.com`               |
| `traceroute` | Shows the network path to a destination           | `traceroute google.com`         |
| `tracepath`  | Traces network path and MTU                       | `tracepath google.com`          |
| `curl`       | Transfers data and tests HTTP endpoints           | `curl http://localhost:8080`    |
| `wget`       | Downloads files from the network                  | `wget https://example.com/file` |
| `dig`        | Performs DNS queries                              | `dig google.com`                |
| `nslookup`   | Queries DNS information                           | `nslookup google.com`           |
| `host`       | Performs DNS lookup                               | `host google.com`               |
| `nc`         | Tests network connections/ports                   | `nc -zv server 22`              |
| `telnet`     | Tests TCP connectivity                            | `telnet server 8080`            |

### Check listening ports

```bash
ss -tulpn
```

### Test an application endpoint

```bash
curl -I http://localhost:8080
```

### Test a port

```bash
nc -zv 192.168.1.10 8080
```

---

# 10. DNS Commands

| Command    | Description                   | Example                |
| ---------- | ----------------------------- | ---------------------- |
| `dig`      | Performs detailed DNS queries | `dig example.com`      |
| `nslookup` | Queries DNS records           | `nslookup example.com` |
| `host`     | Performs simple DNS lookups   | `host example.com`     |

Useful DNS record types:

```text
A
AAAA
CNAME
MX
NS
TXT
```

Example:

```bash
dig example.com A
```

---

# 11. Text Processing Commands

These commands are extremely important for **Linux automation and DevOps scripting**.

| Command | Description                            | Example                     |
| ------- | -------------------------------------- | --------------------------- |
| `grep`  | Searches text using patterns           | `grep "ERROR" app.log`      |
| `awk`   | Processes and extracts structured text | `awk '{print $1}' file`     |
| `sed`   | Searches and modifies text             | `sed 's/old/new/g' file`    |
| `cut`   | Extracts sections from lines           | `cut -d: -f1 /etc/passwd`   |
| `sort`  | Sorts lines                            | `sort names.txt`            |
| `uniq`  | Removes/counts duplicate lines         | `uniq -c names.txt`         |
| `wc`    | Counts lines, words and characters     | `wc -l file.txt`            |
| `tr`    | Translates/deletes characters          | `tr 'a-z' 'A-Z'`            |
| `tee`   | Writes output to file and terminal     | `command \| tee output.txt` |
| `xargs` | Builds command arguments from input    | `cat files.txt \| xargs rm` |

### Example

Count errors in a log:

```bash
grep "ERROR" application.log | wc -l
```

Find the most common IP addresses:

```bash
awk '{print $1}' access.log | sort | uniq -c | sort -nr
```

---

# 12. Pipes and Redirection

### Pipe `|`

Passes output of one command to another command.

```bash
ps aux | grep nginx
```

### Output Redirection `>`

Writes output to a file and overwrites it.

```bash
ls > files.txt
```

### Append `>>`

Adds output to the end of a file.

```bash
echo "Hello" >> file.txt
```

### Input Redirection `<`

Uses a file as command input.

```bash
sort < names.txt
```

### Error Redirection

```bash
command 2> error.log
```

### Standard Output + Error

```bash
command > output.log 2>&1
```

---

# 13. Services and systemd

| Command      | Description                       | Example                  |
| ------------ | --------------------------------- | ------------------------ |
| `systemctl`  | Manages systemd services          | `systemctl status nginx` |
| `service`    | Legacy service management command | `service nginx status`   |
| `journalctl` | Reads systemd logs                | `journalctl -u nginx`    |

### Common systemctl commands

```bash
systemctl status nginx
systemctl start nginx
systemctl stop nginx
systemctl restart nginx
systemctl reload nginx
systemctl enable nginx
systemctl disable nginx
```

### Monitor service logs

```bash
journalctl -u nginx -f
```

---

# 14. Logs

| Command      | Description              | Example              |
| ------------ | ------------------------ | -------------------- |
| `journalctl` | Views systemd logs       | `journalctl`         |
| `dmesg`      | Views kernel messages    | `dmesg`              |
| `tail`       | Views latest log entries | `tail -f app.log`    |
| `grep`       | Searches logs            | `grep ERROR app.log` |
| `less`       | Reads large logs         | `less app.log`       |

### Real-world troubleshooting

```bash
systemctl status nginx
journalctl -u nginx
tail -f /var/log/nginx/error.log
```

---

# 15. SSH and Remote Access

| Command       | Description                            | Example                          |
| ------------- | -------------------------------------- | -------------------------------- |
| `ssh`         | Connects to a remote server            | `ssh user@server`                |
| `scp`         | Copies files over SSH                  | `scp file.txt user@server:/tmp/` |
| `sftp`        | Transfers files interactively over SSH | `sftp user@server`               |
| `ssh-keygen`  | Generates SSH keys                     | `ssh-keygen`                     |
| `ssh-copy-id` | Copies public key to a remote server   | `ssh-copy-id user@server`        |

### Connect to a server

```bash
ssh ubuntu@192.168.1.100
```

### Copy a file

```bash
scp app.tar.gz ubuntu@server:/tmp/
```

---

# 16. Package Management

## RHEL / CentOS / Amazon Linux

```bash
dnf
yum
rpm
```

| Command       | Description                  |
| ------------- | ---------------------------- |
| `dnf install` | Installs packages            |
| `dnf remove`  | Removes packages             |
| `dnf update`  | Updates packages             |
| `dnf search`  | Searches for packages        |
| `rpm -qa`     | Lists installed RPM packages |

Example:

```bash
dnf install nginx
```

## Ubuntu / Debian

```bash
apt
dpkg
```

Example:

```bash
apt update
apt install nginx
apt remove nginx
dpkg -l
```

---

# 17. Compression and Archives

| Command  | Description               | Example                     |
| -------- | ------------------------- | --------------------------- |
| `tar`    | Creates/extracts archives | `tar -cvf backup.tar data/` |
| `gzip`   | Compresses files          | `gzip file.log`             |
| `gunzip` | Decompresses gzip files   | `gunzip file.log.gz`        |
| `zip`    | Creates ZIP archives      | `zip backup.zip file.txt`   |
| `unzip`  | Extracts ZIP archives     | `unzip backup.zip`          |

### Common DevOps command

```bash
tar -czvf backup.tar.gz /var/www/
```

Extract:

```bash
tar -xzvf backup.tar.gz
```

---

# 18. Environment Variables

| Command    | Description                           | Example               |
| ---------- | ------------------------------------- | --------------------- |
| `env`      | Displays environment variables        | `env`                 |
| `printenv` | Displays environment variables        | `printenv PATH`       |
| `export`   | Creates/exports environment variables | `export APP_ENV=prod` |
| `echo`     | Displays variable values              | `echo $PATH`          |
| `source`   | Loads a shell configuration/script    | `source ~/.bashrc`    |

Example:

```bash
export APP_ENV=production
echo $APP_ENV
```

---

# 19. Bash Scripting

Linux commands become much more powerful when combined with Bash scripting.

Important concepts:

```text
Variables
Conditions
Loops
Functions
Arguments
Exit Codes
Input/Output
Command Substitution
```

Important variables:

```bash
$0
$1
$2
$@
$?
$USER
$HOME
$PATH
```

Example:

```bash
#!/bin/bash

if systemctl is-active --quiet nginx
then
    echo "Nginx is running"
else
    echo "Nginx is down"
fi
```

---

# 20. Time and Scheduling

| Command       | Description                  | Example       |
| ------------- | ---------------------------- | ------------- |
| `date`        | Displays date/time           | `date`        |
| `timedatectl` | Manages system time settings | `timedatectl` |
| `crontab`     | Schedules recurring tasks    | `crontab -e`  |
| `at`          | Schedules one-time tasks     | `at 10:00`    |

Example cron:

```text
0 2 * * * /opt/scripts/backup.sh
```

This runs the backup script every day at 2:00 AM.

---

# 21. Important DevOps Troubleshooting Commands

When an application is not working, use a systematic approach.

### Check the service

```bash
systemctl status nginx
```

### Check processes

```bash
ps aux | grep nginx
```

### Check ports

```bash
ss -tulpn
```

### Check CPU

```bash
top
```

### Check memory

```bash
free -h
```

### Check disk

```bash
df -h
```

### Find large directories

```bash
du -sh /var/*
```

### Check logs

```bash
journalctl -u nginx
```

### Test the application

```bash
curl http://localhost:8080
```

### Check DNS

```bash
dig example.com
```

---

# 22. Linux Commands → DevOps Skills

```text
Linux
 │
 ├── Files & Directories
 │
 ├── Users & Permissions
 │
 ├── Processes
 │
 ├── Networking
 │
 ├── Storage
 │
 ├── Logs
 │
 ├── systemd
 │
 ├── SSH
 │
 ├── Text Processing
 │
 └── Bash Scripting
        │
        ▼
     Automation
        │
        ▼
      DevOps
        │
        ├── Git
        ├── CI/CD
        ├── Docker
        ├── Kubernetes
        ├── Terraform
        ├── AWS
        └── Monitoring
```

# 🎯 Linux Mastery Goal

Do not measure your Linux knowledge by the number of commands you can memorize.

Measure it by whether you can troubleshoot a real server.

You should eventually be able to answer questions such as:

* Why is the application down?
* Why is the server slow?
* Why is CPU usage high?
* Why is memory exhausted?
* Why is disk space full?
* Why can't users connect to port `8080`?
* Why is DNS resolution failing?
* Why can't a service start?
* Why is a user getting `Permission denied`?
* Why is an application unable to read/write a file?
* Which process is consuming resources?
* Which process is listening on a port?
* Where are the relevant logs?
* How can this task be automated?

**That is the level of Linux knowledge required for serious Cloud & DevOps work.**
