## 8 Group Management
	- Linux groups are used to manage permissions for multiple users at the same time.

	- For example, suppose a company has multiple employees working in different teams or departments. Giving permissions to each employee individually can become difficult and time-consuming.

	- Instead, we can create groups for different teams and assign users to the appropriate groups. We can then grant permissions to the group rather than configuring permissions for every user individually.

	- This makes permission management simpler, more scalable, and easier to maintain, especially in large organizations.


	```
		ABC Company
		│
		├── Developers_Group
		│   ├── Rahul
		│   ├── John
		│   └── Priya
		│
		├── Operations_Group
		│   ├── David
		│   └── Mike
		│
		└── DevOps_Group
			├── Alex
			└── Sam
		
		Instead of giving permissions individually to Rahul, John, and Priya, we can create a developers group and assign permissions to that group.

	```
	### 8.1.1 Create a Group
		- Create a group called developers:
			- sudo groupadd developers
	
		- Verify:
			- getent group developers
	
		- Example:
			- developers:x:1002:
			
			- Here:
				- developers → group name
				- 1002 → GID (Group ID)
	
	
	### 8.2 Delete a Group
		- Delete the developers group:
		- sudo groupdel developers
	
		- Verify:
			- getent group developers
			- If the group does not exist, no entry will be returned.
		- Important: Deleting a group does not delete the users who belonged to that group.

	### 8.3 Add a User to a Group
		- Add Rahul to the developers group:
		- sudo usermod -aG developers rahul
		- What does -aG mean?
			
			-a  → append
			-G  → supplementary groups

			- So " sudo usermod -aG developers rahul ", means Adding rahul to the developers supplementary group without removing his existing supplementary groups.

		- Caution : Do not accidentally use:
		- "sudo usermod -G developers rahul ", because -G without -a can replace the user's existing supplementary groups.

		- For normal group addition, use: " sudo usermod -aG developers rahul"
		
	### 8.4 View a User's Groups
		- Check which groups Rahul belongs to:
			- groups rahul (groups username)
		- Example:
		- rahul : rahul developers
		
		- You can also use:
			- id rahul
				- Example:
				- uid=1001(rahul) gid=1001(rahul) groups=1001(rahul),1002(developers)
				- id gives you more detailed information, including:
					- UID
					- Primary GID
					- Supplementary groups
					
	### 8.5 Primary Group vs Supplementary Group
	- This is an important concept for DevOps interviews.
	- A Linux user has a primary group and can have multiple supplementary groups.

	- For example:
		- User: id rahul
		- uid=1001(rahul) gid=1001(rahul) groups=1001(rahul),1002(developers),1003(docker),1004(monitoring)
	
		- Primary Group:
			- rahul

		- Supplementary Groups:
			- developers
			- docker
			- monitoring

		- You can check this with:
			- id rahul

		- Example:
			- uid=1001(rahul)
			- gid=1001(rahul)
			- groups=1001(rahul),1002(developers),1003(docker)
			
	### 8.6 Cheat sheet commands
	
	```
		Task									Command
		============================== 			==========================
		Create group							sudo groupadd developers
		Delete group							sudo groupdel developers
		Add user to group						sudo usermod -aG developers rahul
		View user's groups						groups rahul
		Detailed user/group information			id rahul
		View group information					getent group developers
		View all groups							cat /etc/group
	
	```