
### 4 Groups
-Imagine you work in a software company.

There are 100 Developers, 30 Testers, 15 Database Administrators, 10 DevOps Engineers and the company has thousands of files. Now suppose every developer needs access to the source code.Just image without groups, Linux would have to give permissions individually.

	1 John   → Access
	2 Rahul  → Access
	3 Ankit  → Access
	4 Priya  → Access
	5 Kiran  → Access
	
	...upto 100			

If a new developer joins,you must manually give permissions and if someone leaves the organization or company you must manually remove permissions. This quickly becomes impossible to manage and very difficult to manage.

To avoid this Linux introduces Groups. Instead of managing permissions for every user, manage permissions for the entire team.

		Developers Group
		John
		Rahul
		Ankit
		Priya
		Kiran
		
Now give permissions only to the Developers group. Every member automatically gets those permissions.