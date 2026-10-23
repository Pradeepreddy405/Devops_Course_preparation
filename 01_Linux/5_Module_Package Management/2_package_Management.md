# Linux Package Basics

## 1. Identify the Linux Distribution

```bash
cat /etc/os-release
```

## 2. Identify Available Package Managers

```bash
command -v dnf
command -v yum
command -v rpm
command -v apt
command -v dpkg
```

## 3. Search for a Package

```bash
# RHEL-based
dnf search nginx

# Ubuntu / Debian
apt search nginx
```

## 4. Install a Package

```bash
# RHEL-based
sudo dnf install nginx

# Ubuntu / Debian
sudo apt update
sudo apt install nginx
```

## 5. Verify the Installation

```bash
nginx -v
```

Inspect package details:

```bash
# RHEL-based
rpm -qi nginx

# Ubuntu / Debian
dpkg -s nginx
```

## 6. List Installed Packages

```bash
# RHEL-based
dnf list installed

# Ubuntu / Debian
apt list --installed
```

## 7. Remove a Package

```bash
# RHEL-based
sudo dnf remove nginx

# Ubuntu / Debian
sudo apt remove nginx
```

## Practice Tasks

1. Identify your Linux distribution.
2. Search for `curl`.
3. Install `curl`.
4. Verify its version.
5. Inspect its package information.
6. List installed packages.
7. Remove a test package.
8. Record the command output.

