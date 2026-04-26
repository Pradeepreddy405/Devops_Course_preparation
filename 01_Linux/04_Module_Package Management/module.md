## Linux Package Management
 - A package in Linux is a structured, installable unit of software that includes not just the executable program, but also configuration files, dependency information, and metadata required for proper installation and lifecycle management.

 - For example, when we install nginx, we don’t just get the binary. We get the executable under /usr/sbin/nginx, configuration files under /etc/nginx/, and a list of dependencies like OpenSSL or PCRE that are required for it to run.

 - From a DevOps perspective, packages are critical because they ensure consistency across environments. Since they include versioning and dependency details, we can reproduce the same setup in development, staging, and production.

 ### Why not go with manual installations ?
 - Manual installation doesn’t handle dependencies, version control, or clean uninstallation. It breaks automation and makes systems inconsistent, which is risky in production environments.Manual installation doesn’t handle dependencies, version control, or clean uninstallation. It breaks automation and makes systems inconsistent, which is risky in production environments.


 - A package = precompiled software + metadata
 - A package manager = tool that installs, updates, removes software safely
 - Debian → .deb
 - RedHat → .rpm



## 1. Types of Linux Package Managers
 - Debian-based (Ubuntu - .deb) 				: apt, apt-get, dpkg
 - RedHat-based (RHEL, CentOS, Fedora - .rpm) 	: yum, dnf, rpm



## LAB 1: Identify Your System
 - Command :  cat /etc/os-release

Check:
	- Ubuntu 		→ use apt
	- CentOS/RHEL 	→ use yum / dnf

## 2. Package Lifecycle (Core Concept)
 - Every package goes through:
 - Search
 - Install
 - Verify
 - Update
 - Remove


## 3. Searching Packages
 - Ubuntu 			: apt search nginx
 - RHEL/CentOS 	: yum search nginx

## LAB 2: Search for a Package
  - apt search curl
  - Observe: Package name, Description
 
 1.Installing Packages
  - Ubuntu 		: sudo apt install nginx -y
  - RHEL/CentOS  : sudo yum install nginx -y
 
## LAB 3: Install and Verify
  - sudo apt install nginx -y
  - nginx -v
  - If command works → installed successfully

  - Under the Hood (Important for DevOps)

When you install:

	Downloads from repository
	Resolves dependencies
	Installs binaries in:
	/usr/bin
	/usr/sbin

	6. Dependency Management (CRITICAL)

Problem: Software depends on other software

Example:
 - sudo apt install docker
	It installs:
		containerd
		runc
		libraries
		
## LAB 4: See Dependencies
 - apt show nginx

Look for:
Depends:

7 Repositories (Where Packages Come From)
A repository is a structured source that provides both software packages AND the metadata required to manage them.

Repos are configured in:

Ubuntu
/etc/apt/sources.list
RHEL
/etc/yum.repos.d/
## LAB 5: Check Repos
cat /etc/apt/sources.list
You’ll see URLs that’s where packages come from

Updating Packages :
	Update package list
	- sudo apt update
	- Upgrade packages
	- sudo apt upgrade -y

## LAB 6: Update System
 - sudo apt update
 - sudo apt upgrade -y
This is mandatory in real world DevOps before installation 

Removing Packages
 - sudo apt remove nginx

Remove completely:
 - sudo apt purge nginx

## LAB 7: Remove Package
 - sudo apt remove nginx -y
 - nginx -v
Should fail → means removed

Cleaning System
- sudo apt autoremove
- sudo apt clean
 Removes unused dependencies

Low-Level Tools (Advanced)
Ubuntu
 - dpkg -l
 - dpkg -i package.deb
RHEL
 - rpm -qa
 - rpm -ivh package.rpm

These don’t resolve dependencies dangerous if misused

## LAB 8: List Installed Packages
 - dpkg -l | grep nginx

Debugging Issues (REAL DevOps Skill)

Problem 1: Package not found
 - Fix : sudo apt update

Problem 2: Dependency issues
 - Fix : sudo apt --fix-broken install

Problem 3: Command not found
Example: ping command not found
 - Fix:
 - apt search ping
 - sudo apt install iputils-ping
 
## LAB 9: Break and Fix
Remove ping:
- sudo apt remove iputils-ping -y

Try:
- ping google.com

Fix it:
- sudo apt install iputils-ping

## 13. Installing from External Repos

Example: Docker
 - sudo apt install ca-certificates curl gnupg
Add repo:
 - sudo mkdir -p /etc/apt/keyrings

## LAB 10: Add Custom Repo (Basic)
 - sudo add-apt-repository universe
 - sudo apt update


## 14. What You Actually Need for DevOps
As a Devops Engineer focus on:
 - apt & yum usage
 - debugging failures
 - repo management
 - dependency issues
 - installing tools (Docker, Kubernetes, Jenkins)
