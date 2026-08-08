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
	#### 5.1 /etc/passwd - User information
	- It stores basic information about every user on the system.
	- It does not store passwords anymore.
	- To view file use command
		
		cat /etc/passwd
		rahul:x:1000:1000:Rahul Kumar:/home/rahul:/bin/bash
		
		Each field is separated by a colon (:).
		username : password : UID : GID : comment : home : shell
		
		
	#### 5.2 /etc/shadow - User passwords
	
	#### 5.3 /etc/group - Group information
	
	#### 5.4 /etc/gshadow - Secure group passwords & admins
	
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