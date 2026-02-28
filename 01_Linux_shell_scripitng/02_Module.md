

## 1️ What is a Variable?

A variable is a container to store data.

``` bash
name="Mr_Linux"
```

No spaces around = in Bash.

------------------------------------------------------------------------

## 2️ Basic Variable Usage

``` bash
#!/bin/bash

name="Mr_Linux"
echo $name
```

**Output:**

    Mr_Linux

------------------------------------------------------------------------

## 3️ Why Use "\$var" Instead of \$var ?

Always prefer:

``` bash
echo "$name"
```

Because:

	-   Prevents word splitting
	-   Prevents glob expansion
	-   Avoids bugs in production scripts

Example:

``` bash
file="My File.txt"
echo $file      # ❌ breaks
echo "$file"    # ✅ correct
```

------------------------------------------------------------------------

##  4️ Different Types of Variables

### 🔹 User Defined Variables

``` bash
project="DevOps"
```

### 🔹 System Variables

``` bash
echo $HOME
echo $USER
echo $PWD
echo $SHELL
```

Common ones:

  Variable   Meaning
  ---------- ---------------------------
  \$HOME     Home directory
  \$USER     Current user
  \$PWD      Present working directory
  \$PATH     Command search path
  \$?        Last command exit status
  \$\$       Current process ID

------------------------------------------------------------------------

##  5️ Command Substitution

``` bash
today=$(date)
echo "$today"
```

------------------------------------------------------------------------

##  6️ Read Input from User

``` bash
read -p "Enter your name: " name
echo "Hello $name"
```

Silent input (password):

``` bash
read -s password
```

------------------------------------------------------------------------

##  7️ Positional Parameters (Script Arguments) - When you pass arguments to a script, Bash stores them in special variables:

$0  → Script name
$1  → First argument
$2  → Second argument
$3  → Third argument
...
$#  → Number of arguments
$@  → All arguments (separately)
$*  → All arguments (as one string)



``` bash
#!/bin/bash

echo "First argument: $1"
echo "All arguments: $@"
echo "Number of arguments: $#"
```

Run:

``` bash
bash script.sh devops aws linux
```

------------------------------------------------------------------------

##  8 Local vs Global Variables

Global (default) → Accessible everywhere.

Local (inside function):

``` bash
myfunc() {
  local name="DevOps"
  echo "$name"
}
```

------------------------------------------------------------------------

##  9️ Environment Variables

Export makes variable available to child processes.

``` bash
export ENV="production"
```

Check:

``` bash
env | grep ENV
```

------------------------------------------------------------------------

##  Variable Expansion 

### Default Value

``` bash
echo ${name:-"Guest"}
```

### Assign Default

``` bash
echo ${name:="Guest"}
```

### Length of Variable

``` bash
echo ${#name}
```

### Substring

``` bash
name="DevOpsEngineer"
echo ${name:0:6}
```

### Replace String

``` bash
file="app.log"
echo ${file/log/txt}
```

------------------------------------------------------------------------

##  11 Arithmetic with Variables

``` bash
a=10
b=20

sum=$((a + b))
echo "$sum"
```

------------------------------------------------------------------------

## 1️2 Readonly Variables

``` bash
readonly PI=3.14
```

Cannot modify later.

------------------------------------------------------------------------

## 1️3 Unset Variables

``` bash
unset name
```

------------------------------------------------------------------------

##  14 Best Practices (Production Ready )

-   Always quote variables: "\$var"
-   Use meaningful names
-   Use local inside functions
-   Use set -u to prevent unset variable usage
-   Avoid global variables in large scripts

------------------------------------------------------------------------
