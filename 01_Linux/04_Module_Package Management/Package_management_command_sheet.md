###  Update & Upgrade
sudo apt update              # Refresh package list (MANDATORY before install)
sudo apt upgrade            # Upgrade installed packages
sudo apt full-upgrade       # Upgrade + handle dependency changes (more aggressive)

###  Install Packages
sudo apt install <package>
sudo apt install <pkg1> <pkg2>
sudo apt install <package>=<version>   # Install specific version

### Remove Packages
sudo apt remove <package>   # Remove but keep config
sudo apt purge <package>    # Remove completely (including config)
sudo apt autoremove         # Remove unused dependencies

### Search & Info
apt search <package>        # Search package
apt show <package>          # Detailed info
apt list --installed        # List installed packages
apt list --upgradable       # Show upgradable packages

### Fix & Troubleshoot
sudo apt --fix-broken install   # Fix dependency issues
sudo dpkg --configure -a        # Fix interrupted installs
sudo apt clean                  # Clear package cache
sudo apt autoclean              # Remove old cache

### Inspect Packages
dpkg -l                      # List installed packages
dpkg -L <package>            # List files of package
dpkg -S <file>               # Find which package owns a file


## Repository Management
### View repositories
- cat /etc/apt/sources.list
- ls /etc/apt/sources.list.d/

### Add repository
- sudo add-apt-repository ppa:<repo>
### Remove repository
- sudo add-apt-repository --remove ppa:<repo>
