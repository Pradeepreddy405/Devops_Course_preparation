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
	