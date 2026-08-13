## Linux Process Management
- Linux process management refers to creating, monitoring, controlling, and terminating processes in a Linux operating system. A process is an instance of a running program, identified by a unique Process ID (PID).

	- Key Concepts
		- PID (Process ID)			: Unique identifier for each process.
		- PPID (Parent Process ID)	: ID of the process that created the current process.
		- Foreground process		: Runs interactively and uses the terminal.
		- Background process		: Runs without occupying the terminal.
		- Daemon					: A background service process that starts at boot or on demand (e.g., web server)

- Lets go with an example
- Before understanding a process, you need to understand a program.
- Imagine you have a Linux server and you have installed Nginx,Java,Python,Docker,SSH,PostgreSQL. All of these are softwares, but software sitting on the disk cannot serve users right .Something has to execute that software.That is where the concept of a process comes in.

- These are executable programs. When Linux executes a program, the operating system creates a process.

	### BASIC WORKFLOW
	
	```
			Software / Program
				↓
			Execute
				↓
			Process
				↓
			CPU + Memory + Resources
				↓
			Application / Service
				↓
			User Request

	```
## What is a program?
- A program is a file containing instructions that tell the computer what to do.
- For example " nginx " is software that provides a web server.
- The actual executable might be located at " /usr/sbin/nginx "
- You can think of it as:
	
	```
		Program
			│
			│ contains instructions
			▼
		Executable file
		
	```
- But simply having the file on disk does not mean Nginx is running.Once you start running this "systemctl start nginx"

	- Step 1 : Find the executable
	- Step 2 : Load it into memory
	- Step 3 : Create a process
	- Step 4 : Assign the process a PID
	- Step 5 : Allocate resources
	- Step 6 : Schedule it on the CPU
	- Step 7 : Start executing its instructions

	```
			Program on Disk
				│
				│ execute
				▼
			Linux Kernel
				│
				├── creates process
				├── assigns PID
				├── allocates memory
				├── assigns resources
				└── schedules CPU time
						│
						▼
				Running Process
		
		
	```
## 1.4 What Is a Process in Linux?
- A process is an instance of a program that is currently being executed by the operating system.
- For example:

	```
		/usr/sbin/nginx
			│
			│ execute
			▼
		Linux Kernel
			│
			▼
		Process
			│
			├── PID
			├── Memory
			├── CPU state
			├── Open files
			├── Environment
			└── Security credentials
				
							
	```
- So a process is much more than just the program.Linux maintains information about every process.