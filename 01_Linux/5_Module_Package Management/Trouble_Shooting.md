# Linux Package Management Troubleshooting

## 1. Package Not Found

Search for the package and refresh repository metadata.

```bash
# RHEL-based
dnf search PACKAGE_NAME
sudo dnf makecache

# Ubuntu / Debian
sudo apt update
apt search PACKAGE_NAME
```

## 2. Repository Errors

Inspect repository configuration:

```bash
# RHEL-based
dnf repolist
ls -l /etc/yum.repos.d/

# Ubuntu / Debian
ls -l /etc/apt/sources.list.d/
```

Check DNS and connectivity:

```bash
getent hosts example.com
curl -I https://example.com
```

## 3. Dependency Problems

Read the complete error message and inspect the proposed package changes before proceeding.

On Debian-based systems, interrupted package configuration may sometimes be recovered with:

```bash
sudo dpkg --configure -a
sudo apt --fix-broken install
```

Use these commands only after understanding the reported issue.

## 4. Package Manager Lock

Check whether another package management process is active:

```bash
ps aux | grep -E 'apt|dpkg|dnf|yum'
```

Allow an active transaction to finish. Do not delete lock files while a package manager is running.

## 5. Insufficient Disk Space

```bash
df -h
df -i
```

Check available storage and inodes.

## 6. Verify Package Installation

```bash
# RHEL-based
rpm -qi nginx
rpm -ql nginx

# Ubuntu / Debian
dpkg -s nginx
dpkg -L nginx
```

## Troubleshooting Checklist

- Confirm the Linux distribution.
- Verify the package name.
- Read the full error message.
- Check repository configuration.
- Verify network and DNS connectivity.
- Check available disk space.
- Inspect active package management processes.
- Verify the package after installation.

