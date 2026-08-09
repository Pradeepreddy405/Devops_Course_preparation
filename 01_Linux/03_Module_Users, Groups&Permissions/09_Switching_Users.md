### 9. Switching Users
- Linux provides commands to switch from one user account to another. This is commonly used by DevOps engineers and system administrators when troubleshooting, performing administrative tasks, or testing permissions.

	
### 9.1.1 Switch to Another User
- Switch to the rahul user : su rahul
- This switches the current shell to rahul.
- If Rahul's password is required, Linux will prompt for it.
- A better way to switch to another user is : " su - rahul " or " su - username"
	- The " - " is important because it creates a login shell and loads Rahul's environment, including his:
		- Home directory	
		- Environment variables
		- Shell configuration
		- PATH settings

- For example:

		Current user
			│
			│ su - rahul
			▼
		rahul
			│
			└── /home/rahul
		
- For administration and troubleshooting, " su - rahul " is generally preferable to " su rahul "  when you want the target user's complete login environment.

---

### 9.1.2 Become Root
- There are two common approaches.

	- Method 1: sudo su
	- sudo su : This uses your current user's sudo privileges to start a root shell.
	
	- Verify:
		- whoami

	- Output:
		- root
		
	=======
	
	- Method 2: sudo -i
	- sudo -i : This starts an interactive root login shell.

	- Verify:
		- whoami

	- Output:
		- root

	- For a clean root login environment, sudo -i is generally preferred over sudo su.
	
---

### 9.1.3 Return to the Previous User
- When you have finished working as another user or as root , enter the command " exit "
	- Example:
		- sudo - rahul  		----> switching to user called "rahul"
		- pwd 					----> Present working directory 
		- rahul@server:~$ exit	---->  exit from rahul user
		- logout
		- user@server:~$

		- If you entered a root shell:
		- root@server:~# exit
		- logout
		- user@server:~$

	- Caution: exit does not shut down the server. It simply exits the current shell and returns you to the previous shell/user.
	- You can also press: Ctrl + D to exit the current shell.

### 9.1.4 su vs su - vs sudo -i
	```
		Command			Purpose
		=======			========
		su rahul		Switch to Rahul without starting a full login environment
		su - rahul		Switch to Rahul with a login environment
		sudo su			Start a root shell through sudo
		sudo -i			Start a root login shell through sudo
		exit			Leave the current shell
		
	```
### 9.1.5 Summary
- su is used to switch to another user, while sudo is used to execute commands with elevated privileges. su - username starts a login shell and loads the target user's environment. sudo -i starts a root login shell using the user's sudo privileges. After completing the task, we can use exit to leave the switched shell and return to the previous user.
