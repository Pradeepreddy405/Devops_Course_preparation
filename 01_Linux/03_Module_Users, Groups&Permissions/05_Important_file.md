### 5 Important files 

- Whenever you create a user, assign a password, create a group, or give sudo access, Linux stores that information in a few important files.

	```
		File			Purpose
		====			==============
		/etc/passwd		User information
		/etc/shadow		User passwords
		/etc/group		Group information
		/etc/gshadow	Secure group passwords & admins
	
	```
	
	Lets us Imagine a company with 1,000 employees. For every employee, the HR department maintains important information such as the employee's name, employee ID, department, home address, role, and login password. Linux follows a similar concept for managing users and access. 
	
	- Instead of employees, Linux manages **users**. 
	- Instead of departments, Linux uses **groups** to organize users.
	- Instead of employee IDs, Linux uses **UIDs (User IDs)** to uniquely identify each user on the system.

	#### 5.1 /etc/passwd - User information
	- Location: /etc/passwd
	- It stores basic information about every user on the system.
	- It does not store passwords anymore.
	- To view file use command
		
		cat /etc/passwd
		rahul:x:1000:1000:Rahul Kumar:/home/rahul:/bin/bash
		
		Each field is separated by a colon (:).
		username : password : UID : GID : comment : home : shell
		
		Field 1 — Username
		Field 2 — Password Placeholder
		Field 3 — UID (User ID)
		Field 4 — GID (Primary Group ID)
		Field 5 — Comment (GECOS)
		Field 6 — Home Directory
		Field 7 — Login Shell
		
		
	#### 5.2 /etc/shadow - User passwords
	- Location : /etc/shadow
	- This file stores encrypted password information and password aging settings.
		- sudo cat /etc/shadow
		- rahul:$6$asdflkasd...:19950:0:99999:7:::
	
	#### 5.3 /etc/group - Group information
	- Location : /etc/group
	- This file stores group information.
		- sudo cat /etc/group
		- developers:x:1002:john,rahul
		
		- Field 1 — Group Name
		- Field 2 — Password Placeholder
		- Field 3 — Group ID (GID)
		- Field 4 — Members
		
	#### 5.4 /etc/gshadow - Secure group passwords & admins
	- Location : /etc/gshadow
	- This is the secure version of /etc/group.
		- sudo cat /etc/gshadow
		- developers:!:rahul:john
	
	#### 5.5 Common UID Ranges
	
	```
		- UID 		Meaning
		=====		====================================================
		- 0			Root user
		- 1–999		System users/services (distribution-dependent)
		- 1000+		Regular users (on most modern Linux distributions)
		================================================================
	```
		-