## 6. Creating and Managing Linux Users

- Linux user management is one of the most important administration skills for a DevOps/Cloud Engineer.
In real production environments, engineers create users for developers, administrators, CI/CD systems, monitoring tools, application processes, and automation.

	```
			ABC_Company
				│
				├── Developers
				│   ├── Rahul
				│   ├── John
				│   └── Ankit
				│
				├── DevOps Engineers
				│   ├── Priya
				│   └── David
				│
				└── Applications
					├── nginx
					├── jenkins
					└── prometheus
	```
	- Each Linux user has an identity represented primarily by a UID, and users can belong to one or more groups.
	
	## 6.1.1 Create a User
	- Command : sudo useradd rahul
		- This creates the user rahul.
		- Verify : id rahul
		- Example: uid=1001(rahul) gid=1001(rahul) groups=1001(rahul)
	- You can also check:
		- getent passwd rahul
	
	## 6.1.2 Create a User With a Home Directory
	- Command : sudo useradd -m rahul
	- The -m option creates: /home/rahul
		- Verify: ls -ld /home/rahul
	
	- You can also specify the login shell while creating user :
		- sudo useradd -m -s /bin/bash rahul

	- This creates the user with:
		- Username:		rahul
		- Home    :     /home/rahul
		- Shell   :    	/bin/bash
		
	## 6.1.3 Set a Password
	- After creating the user:
		- sudo passwd rahul
		- Linux will ask you to enter the password.
	- Verify the account status :
		- sudo passwd -S rahul
			- Example:
			- rahul P ...
			- P generally indicates that the account has a usable password.
			
	## 6.1.4 Create a User With a Specific Group
	- sudo groupadd developers ( Adding group with name "developers" )
	- Create Rahul and make developers his primary group:
		- sudo useradd -m -g developers rahul
	
	- Check : id rahul
	
	## 6.1.5  Add an Existing User to a Group
	- Suppose Rahul already exists.
	- Add him to developers:
	- sudo usermod -aG developers rahul
	
	- Important:
	- aG
	- means:
		-a → append
		-G → supplementary group
		

	- Caution   
		- Avoid : sudo usermod -G developers rahul
		- if Rahul already belongs to other supplementary groups and you want to preserve them.
	
		- Why?
			- Because -G replaces the supplementary group list.
			- Prefer : sudo usermod -aG developers rahul.
			
	## 6.1.6 Can We Add Multiple Users to a Group?
	- A common mistake is : sudo usermod -aG deployment Rahul John Doe
	- This does not mean "add Rahul, John and Doe."
	
	- usermod modifies one user account at a time.
	- Use:
			- sudo usermod -aG deployment Rahul
			- sudo usermod -aG deployment John
			- sudo usermod -aG deployment Doe

	- For multiple users, automation is better to use automation script

		```
			for user in Rahul John Doe
			do
				sudo usermod -aG deployment "$user"
			done
	
		```
	- This is the kind of thing you should understand for DevOps automation.
	
	## 6.1.7 Change a User's Login Shell
	- Change Rahul's shell:
		- Command : sudo usermod -s /bin/bash rahul

		- Check:
		- getent passwd rahul

		- You may see:
		- rahul:x:1001:1001::/home/rahul:/bin/bash
		- The last field is the login shell.

	## 6.1.8 Lock a User Account
	- To lock the password:
	- Command : sudo usermod -L rahul
	- Check:
		- sudo passwd -S rahul

	- You can also use:
		- sudo passwd -l rahul

	- Example : Production scenario
		- Suppose an employee leaves the company. Instead of immediately deleting the account, an administrator might first disable it. 
		
			- sudo usermod -L rahul
		
		- This preserves the account and its ownership information while preventing normal password authentication.

	## 6.1.9 Unlock a User
	- Unlock:
		- sudo usermod -U rahul (or) sudo passwd -u rahul
		
	## 6.1.10. Change a User's Home Directory
	- You can modify the home directory using:
	- sudo usermod -d /home/rahul-new rahul
	If you also want to move the existing home directory contents:
	sudo usermod -d /home/rahul-new -m rahul

	Here:
		-d → specify home directory
		-m → move existing contents

	## 6.1.11 Change Username
	- You can change the login name:
		- sudo usermod -l rahul_new rahul
	- However, notice that this changes the username, not automatically every associated path.

	For production systems, username changes should be planned carefully because files, scripts, SSH configurations, cron jobs, application configurations, and ownership references may depend on the old account.

	## 6.1.12 Delete a User
	- Basic Command :
		- sudo userdel rahul
	- This removes the user account, but the user's home directory may remain.
	- Check:
		- ls /home/

	## 6.1.13. Delete User and Home Directory
	- Use : sudo userdel -r rahul
	- The -r option removes the user's home directory and associated mail spool where applicable.


	- Caution :  Production warning 
		- Don't blindly run : sudo userdel -r username

		- On a production server.
			- First investigate by using username id and check the process
				- id username

			- Check processes:
				- ps -u username

			- Find files owned by the user:
				- sudo find / -user username 2>/dev/null
	
	- You need to understand what the account is being used for before deleting it.
	