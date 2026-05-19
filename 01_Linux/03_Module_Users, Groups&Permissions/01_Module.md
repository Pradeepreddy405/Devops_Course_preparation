# Linux User & Group management

 - Linux is a multi-user operating system where multiple users and services can securely access the same system using authentication, permissions, ownership, and groups.

### 1 Users
 - A user is an account created for a person, application, or service.
 - Each user has:
	- Username
	- UID (User ID)
	- Home directory
	- Default shell
	- Password
	- GID (Primary Group ID)

 - Example: cat /etc/passwd ----> Output : ravi:x:1001:1001:Ravi:/home/ravi:/bin/bash
 - This file stores user account information.

 - There are mainly 3 types of users:

 - Root user  	                	------>	Full administrative access
 - Normal users						------> Regular login users or Used by humans for interactive login and regular operations.
 - System/service users				------> Cannot login interactively and Used by services like nginx, mysql. System/service users are typically used by applications and services such as nginx, mysql, and apache. Most of them are configured with non-login shells like /sbin/nologin.

	- Example:
	- root				------> Root user
	- ec2-user			------> Normal User
	- nginx				------> System User / Service User
	
	- 0 → root user
	- 1-999 → system/service users
	- 1000+ → normal users (varies by distro)

### 2 Groups
 - A group is a collection of users.
 - Groups are used to simplify permission management by assigning permissions to multiple users collectively.
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
 - Password Hashes		: /etc/shadow  (/etc/shadow stores encrypted password hashes and password aging information.
Only root can read this file.)
 
### 4 Group Management Commands
 
 #### 4.1 Create Group	: groupadd <Group_name>
	- Eg: groupadd developers
	
 #### 4.2 Check the group after creating it
	- cat /etc/group | grep developers
	
 #### 4.3 Delete Group	: groupdel <Group_name>
	- groupdel developers
	- Note : group cannot be deleted if it's primary group of existing users.
	
 #### 4.4 Rename the group developers to devops
	- Modify Group Name : groupmod -n <New_Group_name> <Old_Group_name>
	- groupmod -n devops developers

### 5 User Management with Groups
	
 #### 5.1 Create User with Group : useradd -g group_name username
	- useradd -g devops ravi
	-g indicates ---> primary group

 #### 5.2 Create user with Create user with home directory
	- useradd -m ravi
	
 #### 5.3 Set password	
	- passwd ravi
	
 #### 5.4 Add User to Secondary Group
	- usermod -aG docker ravi
	- a ----> append 
	- G ----> supplementary groups
	- Without -a, existing groups get removed.
	
 #### 5.5 Adding Users to Primary and Secondary Group
	- Eg: useradd -g devops -G docker,jenkins ravi
	
 #### 5.6 Check User Groups
	- groups ravi
		
		OR
	- id ravi
	
 #### 5.7 Remove User from Group
	- gpasswd -d ravi docker
	
 #### 5.8 Change User Primary Group
	- usermod -g devops ravi
	
 #### 5.9 userdel username
	- userdel -r username    -> remove user with home directory

 #### 5.10 lock and Unlock account
	- passwd -l username     -> lock account
	- passwd -u username     -> unlock account

 #### 5.11 Password aging information
	- chage -l username      -> To get the password aging info	
	
 #### 5.12 Adds user to sudo administrative group.
	- usermod -aG wheel ravi     # RHEL/CentOS
	- usermod -aG sudo ravi      # Ubuntu
	