# Linux Repository Management

## What Is a Package Repository?

A package repository is a location that stores software packages and package metadata. Linux package managers use repositories to find, download, install, and update software.

## RHEL-Based Distributions

List enabled repositories:

```bash
dnf repolist
```

List all repositories:

```bash
dnf repolist all
```

Inspect repository configuration:

```bash
ls -l /etc/yum.repos.d/
cat /etc/yum.repos.d/*.repo
```

Refresh metadata:

```bash
sudo dnf makecache
```

## Ubuntu / Debian Distributions

Inspect the main repository configuration:

```bash
cat /etc/apt/sources.list
```

Inspect additional repository files:

```bash
ls -l /etc/apt/sources.list.d/
```

Refresh package indexes:

```bash
sudo apt update
```

## Troubleshooting Repositories

Check DNS resolution:

```bash
getent hosts example.com
```

Check HTTPS connectivity:

```bash
curl -I https://example.com
```

Review repository URLs, network connectivity, DNS settings, and package manager error messages.

## Practice Tasks

1. List configured repositories.
2. Identify enabled repositories.
3. Inspect repository configuration files.
4. Refresh repository metadata.
5. Investigate a repository access error in a test environment.
6. Document the cause and solution.

## Best Practices

- Use trusted repositories.
- Verify package signatures.
- Review repository changes carefully.
- Avoid disabling signature verification to bypass errors.

