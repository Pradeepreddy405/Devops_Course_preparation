### 6. Creating and Managing Linux Users

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
	
	#### 6.1 Create a User
	- Basic command : sudo useradd rahul
		- This creates the user rahul.
		- Verify : id rahul
		- Example: uid=1001(rahul) gid=1001(rahul) groups=1001(rahul)
	- You can also check:
		- getent passwd rahul
	
	### 6.2 Create a User With a Home Directory
	- Use : sudo useradd -m rahul
	- The -m option creates: /home/rahul
		- Verify: ls -ld /home/rahul
	
	- You can also specify the login shell while creating user :
		- sudo useradd -m -s /bin/bash rahul

	- This creates the user with:
		- Username:		rahul
		- Home    :     /home/rahul
		- Shell   :    	/bin/bash
	