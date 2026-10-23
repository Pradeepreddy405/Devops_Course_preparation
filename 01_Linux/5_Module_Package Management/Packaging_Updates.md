# Linux Package Updates

## 1. Check for Available Updates

### RHEL-Based Systems

```bash
sudo dnf check-update
```

A return code indicating available updates does not necessarily mean the command failed.

### Ubuntu / Debian Systems

```bash
sudo apt update
apt list --upgradable
```

## 2. Update Packages

```bash
# RHEL-based
sudo dnf upgrade

# Ubuntu / Debian
sudo apt upgrade
```

Review the proposed changes before confirming.

## 3. Inspect Package History

### RHEL-Based Systems

```bash
sudo dnf history
```

### Ubuntu / Debian Systems

```bash
less /var/log/apt/history.log
less /var/log/dpkg.log
```

## 4. Verify Package Versions

```bash
# RHEL-based
rpm -qi nginx

# Ubuntu / Debian
dpkg -s nginx
```

## Practice Tasks

1. Check for available package updates.
2. Refresh repository metadata.
3. Review packages awaiting upgrades.
4. Update packages in a test environment.
5. Inspect package management logs.
6. Verify installed package versions.

## Best Practices

- Review proposed package changes.
- Test updates before production deployment.
- Maintain appropriate backups.
- Schedule updates according to maintenance requirements.
- Investigate failed upgrades before retrying.

