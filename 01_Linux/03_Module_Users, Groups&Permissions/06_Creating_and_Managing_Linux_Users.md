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
	- A Linux user's home directory is their personal working environment. For a normal user such as Rahul, it is typically /home/rahul. It contains personal files and user-specific configuration such as .bashrc, .profile, and .ssh. When creating users with useradd, I can use useradd -m username to explicitly create the user's home directory. Linux can populate the initial configuration from /etc/skel.
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
	
	## 14. check Whether a User Exists
	- Useful command: id rahul
	- If the user doesn't exist, you'll get an error.
	- Another useful command:
		- getent passwd rahul

	- For automation scripts, this is particularly useful.

		```
		Example:
		
		if id rahul >/dev/null 2>&1
		then
			echo "User exists"
		else
			echo "User does not exist"
		fi
		
		```
	This introduces you to Linux administration + shell automation, which is important for DevOps.

	## 6.1.15 Check User's Groups
	- Command: groups rahul	or id rahul
	- Example:
		- uid=1001(rahul) gid=1001(rahul) groups=1001(rahul),1002(developers),1003(deployment)
		- This tells you:
			UID
			Primary GID
			Supplementary groups

	## 6.1.16 Add User to Sudo/Admin Group
	- On Ubuntu/Debian systems: " sudo usermod -aG sudo rahul "
	
	- On RHEL/Amazon Linux systems, the administrative group is commonly: " sudo usermod -aG wheel rahul"
	
	- Then verify : id rahul
	
	- Important: Don't assume the same administrative group exists on every Linux distribution.
		- For example:
			- Ubuntu/Debian → sudo
			- RHEL/CentOS/Amazon Linux → wheel

	## 6.1.17 Switch to Another User
	You can switch users with:
	- Command:  su - rahul
	- The  " - " is important because it creates a login shell and loads Rahul's environment.

	- After you finish working as Rahul, you can return to the previous user with Command"" exit "
	- The exit command closes the current user's shell and returns you to the previous user.

	## 6.1.18 SSH User Management — Very Important for Cloud
	- In AWS environments, you commonly connect to EC2 using SSH.
	- For example:
		- ssh -i key.pem ec2-user@<server-ip>

	- For another administrator:
		- sudo useradd -m devops
		- sudo passwd devops

	- But for production SSH access, SSH keys are generally preferred over password authentication.
	- The user's public key is commonly placed in:
			- 1  /home/devops/.ssh/authorized_keys
			- 2  Permissions matter:
			- 3  chmod 700 /home/devops/.ssh
			- 4  chmod 600 /home/devops/.ssh/authorized_keys
		- Ownership should also be correct:
		- sudo chown -R devops:devops /home/devops/.ssh
		- This connects Linux user management directly to AWS EC2 administration and cloud security.

	## 6.1.19 Service Accounts
	- Not every Linux user represents a human. Applications often run under dedicated accounts.
	- For example:
		- 1 nginx
		- 2 jenkins
		- 3 prometheus
		- 4 postgres

	- You can create a service account such as:
		- sudo useradd -r nginx
		-r creates a system account.

	- A service account helps enforce the principle of least privilege:
	- Run each application with only the permissions it actually needs. For example, instead of running the Nginx application as the root user, you can run it as a dedicated service account such as the nginx user. This reduces the impact of a compromise.