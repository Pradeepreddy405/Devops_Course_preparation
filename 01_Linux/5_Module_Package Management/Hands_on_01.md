
# 1. Identify the OS
cat /etc/os-release

# 2. Identify the package manager
command -v dnf
command -v yum
command -v rpm

# 3. Check whether the RPM package is installed
rpm -q tomcat

# 4. Check package metadata in enabled repositories
sudo dnf repoquery --info tomcat

# 5. Check enabled repositories
sudo dnf repolist

# 6. Search for related package names
sudo dnf search tomcat

# 7. Check repository configuration files
ls -l /etc/yum.repos.d/

# 8. Check whether the service is known to systemd
systemctl list-unit-files 'tomcat*'

# 9. Check whether a Tomcat Java process is running
pgrep -af 'org.apache.catalina.startup.Bootstrap'

# 10. Check listening TCP ports
sudo ss -ltnp
