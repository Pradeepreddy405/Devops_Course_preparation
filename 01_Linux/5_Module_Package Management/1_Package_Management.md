# Linux Package Management

A hands-on guide to Linux package management for system administration and Linux operations.

## Topics Covered

- Package management fundamentals
- Installing and removing packages
- Searching for packages
- Verifying installed packages
- Managing software repositories
- Updating packages
- Inspecting package information
- Troubleshooting package management issues

## Package Management Tools

| Tool | Purpose |
|---|---|
| DNF | Package management on modern RHEL-based distributions |
| YUM | Package management on older RHEL-based distributions |
| RPM | Query and manage RPM packages |
| APT | Package management on Debian-based distributions |
| DPKG | Install and inspect Debian packages |

## Essential Commands

### RHEL-Based Linux

```bash
sudo dnf install nginx
dnf search nginx
dnf info nginx
dnf list installed
sudo dnf remove nginx
rpm -qi nginx
rpm -ql nginx
```

### Ubuntu / Debian Linux

```bash
sudo apt update
sudo apt install nginx
apt search nginx
apt show nginx
apt list --installed
sudo apt remove nginx
dpkg -s nginx
dpkg -L nginx
```

## Learning Objectives

1. Understand how Linux package managers work.
2. Install and remove software packages.
3. Inspect package versions and installation details.
4. Search configured repositories.
5. Update package metadata and installed software.
6. Diagnose common package management errors.

## Hands-On Practice

- Identify the Linux distribution.
- Find the package manager available on the system.
- Search for a package.
- Install and verify a package.
- List installed packages.
- Inspect package files.
- Remove a package.
- Inspect repository configuration.
- Check for available updates.
- Troubleshoot package installation failures.

## Important Note

Commands and package names may vary between Linux distributions. Practice in a test environment before modifying production systems.
