# Linux User & Group management

 - Linux is a multi-user operating system where multiple users can access the same server securely. To manage access and security,Linux uses users and groups.

### 1 Users
 - A user is an account created for a person, application, or service.
 - Each user has:
	Username
	UID (User ID)
	Home directory
	Default shell

 - Example: cat /etc/passwd
 - This file stores user account information.

 - There are mainly 3 types of users:

 - Root user/SuperUser/Sudo user  	------>	Full administrative access
 - Normal users						------> Regular login users
 - System/service users				------> Used by services like nginx, mysql

	- Example:
	- root
	- ec2-user
	- nginx

### 2 Groups
 - A group is a collection of users.
 - Groups simplify permission management because instead of assigning permissions user by user, we assign them to a group.
 - A user can belong to multiple groups.
 - Check the groups 
	- Command : groups username
	
 #### 2.1 Primary Group
	- Every user has one default group.
	- Example:
    
	```
		id ravi
		Output:
			uid=1001(ravi) gid=1001(ravi) groups=1001(ravi)
		Here:
			gid = primary group
	```
 #### 2.2 Secondary (Supplementary) Groups
	- Extra groups a user belongs to.
	- Example:

	```
		- usermod -aG docker ravi
		- Now ravi can access Docker commands.
	```
### 3 Important commands
 - User Information		: /etc/passwd
 - Group Information	: /etc/group
 - Password Hashes		: /etc/shadow
 
### 4 Group Management Commands
	- 4.1 Create Group	: groupadd <Group_name>
	- Eg: groupadd developers
	
	- 4.2 Check the group after creating it
	- cat /etc/group | grep developers
	
	- 4.3 Delete Group	: groupdel <Group_name>
	- groupdel developers
	
	- 4.4 Rename the group developers to devops
	- Modify Group Name : groupmod -n <New_Group_name> <Old_Group_name>
	- groupmod -n devops developers

### 5 User Management with Groups
	- 5.1 Create User with Group : useradd -g group 
	- useradd -g devops ravi
	-g indicates ---> primary group
	
	- 5.2 Add User to Secondary Group
	- usermod -aG docker ravi
	- a ----> append 
	- G ----> supplementary groups
	- Without -a, existing groups get removed.

	- 5.3. Check User Groups
	- groups ravi
		
		OR
	- id ravi
	
	- 5.4 Remove User from Group
	- gpasswd -d ravi docker
	
	- 5.4 Change User Primary Group
	- usermod -g devops ravi