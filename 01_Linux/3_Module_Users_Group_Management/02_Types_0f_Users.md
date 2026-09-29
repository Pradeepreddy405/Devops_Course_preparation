## 2 Types of Users ?
	
- Linux is a multi-user operating system, which means multiple users can use the same operating system simultaneously. Every person, application, or service that interacts with the operating system does so through a user account.

	- However, not all users are the same. Linux categorizes users based on their purpose and privileges.
	- The three major types of users are:
		- 1 Root User
		- 2 System Users
		- 3 Normal Users

	- Each type has a different role in maintaining the security and stability of the operating system.
	
		### 2.1 Root User
		- Username : root
		- UID      : 0


		### 2.1.1 What is the Root User?
		- The root user is the superuser or administrator of the Linux operating system.
		- Unlike other users, the root user has unrestricted access to every file, directory, process, device, and configuration on the system.
		- The Linux kernel recognizes any user with UID (User ID) 0 as the root user.
		- Because of this special UID, root bypasses almost all permission checks performed by Linux.
			
		### 2.1.3	Root user characteristics		
		- Has complete administrative privileges.
		- Can access every file regardless of permissions.
		- Can install or remove software.
		- Can create, modify, or delete any user account.
		- Can start or stop any service.
		- Can modify kernel parameters.
		- Can change file ownership and permissions.
		- Can mount or unmount storage devices.
		- Can shut down or reboot the system.
			
		- If something can be done on Linux, the root user can do it.
			
		### 2.1.4 Why is Root Dangerous?		
		- Since root has unrestricted access, even a small mistake can damage the entire operating system.
			
			#### Eamples
				1 rm -rf /			
					This command attempts to delete everything from the root filesystem.
				
				2 chmod -R 777 /
					would make every file writable, creating a serious security risk.Because of this, administrators avoid logging in directly as root for daily tasks.Instead, they temporarily obtain administrative privileges using commands such as "sudo command"
							
							Examples:
							
								- sudo systemctl restart nginx
								- sudo useradd devops
								- sudo chmod 644 index.html
								- sudo kill -9 12345
								- sudo mount /dev/xvdf1 /data
								- sudo -i, sudo su -
				
				Best practice
				3 Use your normal user account for day-to-day work and run administrative commands with sudo only when needed. This minimizes the risk of accidental damage while maintaining security and accountability.
				
			#### Summary 
			The root user is the Linux superuser with UID 0. It has unrestricted access to every resource in the operating system and bypasses almost all permission checks. Because of its unlimited privileges, administrators use it cautiously and generally perform administrative tasks through sudo rather than logging in directly as root.
				
		### 2.2 System Users
		- System users are special accounts created for running system services and background processes.
		- These accounts are not intended for human login, Instead, they provide isolated identities for applications and services.
			- Examples : 
					- apache
					- nginx
					- mysql
					- postgres
					- sshd
					- daemon
					- nobody
					- systemd-network
						
		### 2.2.1 Why Do We Need System Users?
		- Imagine a web server running as the root user.If an attacker exploits a vulnerability in the web server,they immediately gain root access to the entire operating system.If incase attacker can gain access ,that would allow them to do below things
			
			- Delete files
			- Install malware
			- Read passwords
			- Shut down services
			- Access databases
			
			- This is extremely dangerous.Instead, Linux runs each service using its own dedicated system user.
				
				For example:
				
				- Nginx Service Runs as nginx user
				- MySQL Service Runs as mysql user
				- Apache Service Runs as apache user

				- Now suppose an attacker compromises Nginx.The attacker receives only the permissions of the nginx user.Since the nginx user has very limited privileges,the damage is significantly reduced.

				- This follows one of the most important security principles:
					- Least Privilege
					- Every service receives only the permissions it actually needs.
					
		### 2.2.2 characteristics of System User
		- Usually created automatically during software installation.
		- Primarily used by operating system services.
		- Typically cannot log in interactively.
		- Own service-related files and processes.
		- Have limited permissions.
		- Improve isolation between applications.
			
			#### Lets consider real time example
			Consider a Linux server running:
			- 1 Nginx
			- 2 MySQL
			- 3 Jenkins
			- 4 Docker
				
			Each service runs under its own user account:
			- nginx
			- mysql
			- jenkins
			- docker

			If Jenkins is compromised, the attacker does not automatically gain access to MySQL files because each service operates under a different user identity. This separation greatly improves system security.
			
			#### Summary 
			System users are non-human accounts created for operating system services and applications. Each service runs using its own dedicated user account instead of root, ensuring isolation and limiting the impact of security vulnerabilities.
			
			
		### 2.3 Normal Users
		Normal users are regular human users who interact with the operating system.
		
			Examples:rahul, john, developer, ubuntu, ec2-user, student
		Unlike the systemUsers, normal users are intended to log in and perform daily tasks.
		
			#### 2.3.1 What can Normal Users can do ?
				- Log into the operating system.
				- Execute commands.
				- Create files and directories.
				- Install software inside their home directory (where permitted).
				- Access applications.
				- Connect to remote servers.
				- Develop and deploy applications.
				- However, they cannot modify protected system files unless administrative privileges are granted.

				#### 2.3.2 Example
				1 Suppose user rahul creates " report.txt " . Eventually the owner becomes rahul. If Another normal user named john or someother user cannot modify the file unless appropriate permissions are granted.This ensures data isolation between users.
				
				2 On AWS EC2 instances once you login into instance either Ubuntu or Amaozn linux or centos default usernames are 
				- ec2-user
				- Ubuntu
				- ubuntu
				
				These are normal users provided for administrators to log in securely. When administrative work is required sudo is used to temporarily obtain elevated privileges.
				
				#### 2.3.3 Characteristics of Normal User	
				- Intended for human users.
				- Can log in using SSH or a console.
				- Own personal files.
				- Have individual home directories (/home/ec2-user , /home/rahul , /home/John.
				- Limited permissions by default.
				- Can become administrators through sudo if authorized.

	### Summary 
	Linux classifies users into three main categories: Root, System, and Normal users. The root user (UID 0) has unrestricted administrative access to the operating system. System users are dedicated accounts used by services such as Nginx, MySQL, and SSHD, allowing applications to run with only the permissions they require. Normal users are intended for human interaction and perform everyday tasks with limited privileges. When administrative actions are needed, they temporarily elevate their permissions using sudo. This design provides security, isolation, accountability, and controlled access, making Linux suitable for secure multi-user environments and production cloud infrastructure.
