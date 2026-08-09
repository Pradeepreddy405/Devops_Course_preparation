### 9. Switching Users
- Linux provides commands to switch from one user account to another. This is commonly used by DevOps engineers and system administrators when troubleshooting, performing administrative tasks, or testing permissions.

	
### 9.1 Switch to Another User
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

	Verify:
		- whoami

	Output:
		- root

	- For a clean root login environment, sudo -i is generally preferred over sudo su.
	
---



