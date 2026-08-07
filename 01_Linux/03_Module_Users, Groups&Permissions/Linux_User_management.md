# Linux User management

- Linux uses users to identify who is accessing the system. Every file, process, and resource belongs to a user and a group. This prevents unauthorized access, protects critical system files, keeps users isolated from one another, allows administrators to track actions, and enables controlled sharing of resources. Without user management, Linux would not be a secure multi-user operating system.
		
## 1 Why Linux Needs Users ?

- Linux is designed as a multi-user operating system, meaning multiple users can access and use the same system simultaneously. To maintain security, stability, and efficient resource management, Linux identifies every user and controls what they are allowed to do.

	### 1.1 An easy way to understand this concept is to imagine Linux as an apartment building
	
	- Every resident represents a user.
	- Every family represents a group of users who share common permissions.
	- The security guard represents the authentication system, which verifies the identity of anyone attempting to access the building.
	- Every room represents a file or directory, and each room has an owner who determines who can access or modify it.

	### 1.2 Without a user management system, the operating system would have no way to distinguish between different people using the computer. As a result:
	- Any user could delete or modify important system files.
	- Any user could stop or interfere with critical applications and services.
	- Any user could gain unauthorized access to sensitive information, such as databases and configuration files.

	### 1.3 To prevent these problems, Linux separates users and enforces access controls. This provides several important benefits:
	- Security 				:	Ensures that only authorized users can access or modify system resources.
	- Isolation 			:	Keeps each user's files, processes, and activities separate from those of other users.
	- Accountability		:	Records actions performed by each user, making it possible to identify who performed a particular task.
	- Resource Control		: 	Allows the operating system to manage and limit access to CPU, memory, storage, and other system resources for different users.

	Because of these features, Linux provides a secure, reliable, and efficient environment for multiple users to work on the same system without interfering with one another.
	
---

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