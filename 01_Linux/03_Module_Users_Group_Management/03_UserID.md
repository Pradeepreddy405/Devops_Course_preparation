
### 3 User IDs (UID)

- In Linux, every user is assigned a User ID (UID), which is a unique numerical identifier.Although humans recognize users by names such as rahul, john, or root, the Linux kernel does not use usernames internally. Instead, it identifies every user by their UID.
	
	For example:
		Username    UID
		root        0
		john        1001
		rahul       1002
Usernames are designed for humans, while UIDs are designed for the Linux kernel.
When you log into a Linux system, your username is first converted into its corresponding UID. From that point onward, the kernel uses the UID—not the username—to determine ownership, permissions, and access rights.