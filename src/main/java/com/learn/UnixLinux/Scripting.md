# Linux, UNIX, and Shell Scripting Interview Guide for Amdocs

## Introduction to UNIX and Linux

UNIX and Linux operating systems are essential knowledge for developers working in enterprise environments like Amdocs.
As a Java developer, you'll likely interact with Linux servers for deployment, troubleshooting, and automation, so
understanding these systems is crucial for your success.

## 1️⃣ UNIX/Linux Fundamentals

### What is UNIX/Linux?

UNIX is a powerful, multiuser, multitasking operating system developed in the 1970s at Bell Labs. Linux is an
open-source UNIX-like operating system created by Linus Torvalds in 1991. Both follow similar principles and share many
commands.

The key strengths of UNIX/Linux systems include:

- **Stability**: They can run for years without requiring restarts
- **Security**: Strong permission systems and user separation
- **Flexibility**: Highly customizable for different needs
- **Scalability**: Can run on devices from tiny IoT devices to supercomputers

### Shell Basics

The shell is your command-line interface to the operating system. Common shells include:

- **Bash** (Bourne Again SHell): The most common Linux shell
- **sh** (Bourne Shell): The original UNIX shell
- **zsh**: Extended shell with additional features
- **fish**: User-friendly shell with modern features

When you open a terminal, you're interacting with a shell. The default prompt typically looks like:

```
username@hostname:~$
```

Where:

- `username` is your login name
- `hostname` is your computer's name
- `~` represents your home directory
- `$` indicates you're a regular user (# would indicate root/admin privileges)

## 2️⃣ Essential UNIX/Linux Commands

### File System Navigation

Understanding how to navigate the file system is fundamental for any Unix/Linux user:

| Command | Description                | Example                                |
|---------|----------------------------|----------------------------------------|
| `pwd`   | Print Working Directory    | `pwd`                                  |
| `ls`    | List files and directories | `ls -la` (list all files with details) |
| `cd`    | Change Directory           | `cd /home/user/documents`              |
| `cd ..` | Move up one directory      | `cd ..`                                |
| `cd ~`  | Go to home directory       | `cd ~`                                 |

For example, to see where you are and then view files in your current directory:

```bash
pwd
# Output might be: /home/username
ls -l
# Output lists files with permissions, size, date
```

### File Operations

Working with files is a daily task for developers:

| Command | Description                  | Example                         |
|---------|------------------------------|---------------------------------|
| `touch` | Create empty file            | `touch newfile.txt`             |
| `mkdir` | Create directory             | `mkdir myproject`               |
| `rm`    | Remove file                  | `rm oldfile.txt`                |
| `rm -r` | Remove directory recursively | `rm -r olddirectory`            |
| `cp`    | Copy file                    | `cp source.txt destination.txt` |
| `mv`    | Move or rename file          | `mv oldname.txt newname.txt`    |

For example, creating a project directory structure:

```bash
mkdir -p project/src/main/java
# Creates nested directories in one command (-p flag)
touch project/src/main/java/Main.java
# Creates an empty Java file
```

### Viewing and Editing Files

Developers frequently need to examine and modify files:

| Command  | Description               | Example                              |
|----------|---------------------------|--------------------------------------|
| `cat`    | Display file contents     | `cat config.xml`                     |
| `less`   | View file with pagination | `less large_log_file.log`            |
| `head`   | Show first few lines      | `head -10 data.csv` (first 10 lines) |
| `tail`   | Show last few lines       | `tail -f app.log` (follow updates)   |
| `nano`   | Simple text editor        | `nano script.sh`                     |
| `vi/vim` | Powerful text editor      | `vim code.java`                      |

For example, monitoring a growing log file:

```bash
tail -f /var/log/application.log
# Shows new entries as they're added (-f means "follow")
```

### File Permissions

Understanding permissions is crucial for security and troubleshooting:

| Command | Description                 | Example                     |
|---------|-----------------------------|-----------------------------|
| `chmod` | Change file permissions     | `chmod 755 script.sh`       |
| `chown` | Change file owner           | `chown user:group file.txt` |
| `ls -l` | List files with permissions | `ls -l`                     |

Permission notation in Linux uses either:

- Numeric notation: `chmod 755 file.sh` (rwx for owner, rx for others)
- Symbolic notation: `chmod u+x file.sh` (add execute to user)

For example, making a script executable:

```bash
chmod +x deploy.sh
# Makes the file executable for all who have read permission
```

### Process Management

Managing running processes is essential for troubleshooting and optimization:

| Command   | Description                 | Example                                  |
|-----------|-----------------------------|------------------------------------------|
| `ps`      | Show processes              | `ps aux` (show all processes)            |
| `top`     | Dynamic process viewer      | `top`                                    |
| `htop`    | Enhanced top (if installed) | `htop`                                   |
| `kill`    | Terminate process           | `kill 1234` (kill process with PID 1234) |
| `killall` | Kill processes by name      | `killall java` (kill all Java processes) |

For example, finding memory-hungry Java processes:

```bash
ps aux | grep java
# Lists all Java processes with details
```

## 3️⃣ Shell Scripting

Shell scripting allows you to automate tasks that would be tedious to perform manually.

### Script Basics

A simple shell script:

```bash
#!/bin/bash
# This is a comment
echo "Hello, world!"

# Variables
NAME="Alice"
echo "Hello, $NAME"

# Command substitution
CURRENT_DIR=$(pwd)
echo "You are in $CURRENT_DIR"
```

Save this as `hello.sh`, make it executable with `chmod +x hello.sh`, and run it with `./hello.sh`.

### Control Structures

Shell scripts support various control structures:

#### Conditionals (if statements)

```bash
#!/bin/bash
# Check if a file exists
if [ -f "config.txt" ]; then
    echo "Config file exists"
else
    echo "Config file not found"
    exit 1
fi

# Check a number
AGE=25
if [ $AGE -lt 18 ]; then
    echo "Minor"
elif [ $AGE -ge 18 ] && [ $AGE -lt 65 ]; then
    echo "Adult"
else
    echo "Senior"
fi
```

#### Loops

```bash
#!/bin/bash
# For loop
echo "Counting from 1 to 5:"
for i in 1 2 3 4 5; do
    echo $i
done

# While loop
echo "Countdown from 5 to 1:"
count=5
while [ $count -gt 0 ]; do
    echo $count
    count=$((count - 1))
done

# Loop through files
echo "Text files in current directory:"
for file in *.txt; do
    echo $file
done
```

### Functions

```bash
#!/bin/bash
# Define a function
say_hello() {
    echo "Hello, $1!"
}

# Call the function
say_hello "World"
say_hello "Amdocs"

# Function with return value
add_numbers() {
    local sum=$(($1 + $2))
    return $sum
}

add_numbers 5 7
echo "Sum: $?"  # $? contains the return value
```

### Practical Script Example

Here's a more practical script that might be useful in a development environment:

```bash
#!/bin/bash
# backup_project.sh - Backs up a project directory

# Check if source directory is provided
if [ $# -lt 1 ]; then
    echo "Usage: $0 <project_directory>"
    exit 1
fi

# Variables
SRC_DIR="$1"
TIMESTAMP=$(date +%Y%m%d_%H%M%S)
BACKUP_DIR="backups/${SRC_DIR##*/}_$TIMESTAMP"

# Check if source directory exists
if [ ! -d "$SRC_DIR" ]; then
    echo "Error: Directory $SRC_DIR does not exist"
    exit 1
fi

# Create backup directory
mkdir -p "$BACKUP_DIR"

# Copy files
echo "Backing up $SRC_DIR to $BACKUP_DIR..."
cp -r "$SRC_DIR"/* "$BACKUP_DIR"

# Create a log file
echo "Backup created on $(date)" > "$BACKUP_DIR/backup_info.log"
echo "Original location: $SRC_DIR" >> "$BACKUP_DIR/backup_info.log"

echo "Backup completed successfully."
echo "Backup location: $BACKUP_DIR"
```

This script creates a timestamped backup of a project directory, demonstrating practical use of variables, conditionals,
and command substitution.

## 4️⃣ Common Interview Questions on Linux/UNIX and Shell Scripting

### Basic Linux/UNIX Questions

#### Q1: What is the difference between UNIX and Linux?

**Answer**: UNIX is the original operating system developed at Bell Labs in the 1970s. It's proprietary and comes with a
license fee. Linux, on the other hand, is an open-source UNIX-like operating system created by Linus Torvalds in 1991.
Linux is actually just the kernel, while a complete Linux operating system (like Ubuntu or Red Hat) includes the kernel
plus many other tools, many from the GNU project, which is why it's sometimes called GNU/Linux.

#### Q2: Explain the Linux file system hierarchy.

**Answer**: The Linux file system follows the Filesystem Hierarchy Standard (FHS) and includes these key directories:

- `/` - Root directory (everything is under this)
- `/bin` - Essential user commands
- `/boot` - Boot loader files and kernel
- `/dev` - Device files
- `/etc` - System configuration files
- `/home` - User home directories
- `/lib` - Shared libraries
- `/mnt` or `/media` - Mount points for removable media
- `/opt` - Optional application software
- `/proc` - Virtual filesystem for process information
- `/root` - Home directory for the root user
- `/sbin` - System binaries
- `/tmp` - Temporary files
- `/usr` - User utilities and applications
- `/var` - Variable files (logs, etc.)

#### Q3: What are file permissions in Linux? How do you change them?

**Answer**: File permissions in Linux control who can read, write, or execute files. They're represented by a
10-character string like `drwxr-xr--`, where:

- First character: file type (d for directory, - for regular file)
- Characters 2-4: owner permissions (rwx means read, write, execute)
- Characters 5-7: group permissions
- Characters 8-10: others/world permissions

To change permissions, use the `chmod` command:

- Symbolic mode: `chmod u+x file.sh` (adds execute permission for the user)
- Numeric mode: `chmod 755 file.sh` (sets rwx for owner, rx for group and others)

#### Q4: What is a symbolic link? How do you create one?

**Answer**: A symbolic link (or symlink) is a special file that points to another file or directory. It's similar to a
shortcut in Windows. To create a symbolic link, use the `ln -s` command:

```bash
ln -s target_file link_name
```

For example, to create a link named "latest" pointing to a log file:

```bash
ln -s /var/log/app/log-20240310.txt latest.log
```

Now, using `latest.log` will reference the actual log file.

### Intermediate Linux Questions

#### Q5: How would you find all files larger than 100MB in the /var directory?

**Answer**: You can use the `find` command with the `-size` option:

```bash
find /var -type f -size +100M
```

This searches the /var directory for files (not directories) larger than 100MB.

#### Q6: How would you search for a string in multiple files?

**Answer**: You can use the `grep` command:

```bash
grep -r "search_string" /path/to/search
```

The `-r` flag makes the search recursive through directories. For example:

```bash
grep -r "ERROR" /var/log
```

This searches for "ERROR" in all files under /var/log.

#### Q7: How do you check system resource usage?

**Answer**: There are several commands for monitoring system resources:

- `top` or `htop` - Interactive process viewers showing CPU, memory usage
- `free -h` - Memory usage in human-readable format
- `df -h` - Disk space usage
- `vmstat` - Virtual memory statistics
- `iostat` - CPU and I/O statistics

#### Q8: Explain the difference between a hard link and a symbolic link.

**Answer**:

- **Hard link**: Creates an additional directory entry for the same file (same inode). If the original file is deleted,
  the hard link still works. Hard links can't cross file systems and can't link to directories.
- **Symbolic link**: Creates a special file that points to the target by name. If the original file is deleted, the
  symlink breaks. Symlinks can cross file systems and can link to directories.

Commands:

- Hard link: `ln target_file link_name`
- Symbolic link: `ln -s target_file link_name`

### Shell Scripting Questions

#### Q9: Write a shell script to count the number of files in a directory.

**Answer**:

```bash
#!/bin/bash
# Count files in a directory

dir=${1:-.}  # Use first argument or current directory if none provided

file_count=$(ls -l "$dir" | grep -v ^d | wc -l)
# Subtract 1 for the "total" line in ls output
file_count=$((file_count - 1))

echo "Number of files in $dir: $file_count"
```

#### Q10: How would you debug a shell script?

**Answer**: There are several ways to debug shell scripts:

1. Use the `-x` flag when running the script:
   ```bash
   bash -x script.sh
   ```
   This prints each command and its arguments as they're executed.

2. Add `set -x` at the beginning of your script to enable debugging for the entire script.

3. Use `set -x` before and `set +x` after specific sections to debug only those parts.

4. Add echo statements to print variable values and execution flow.

5. Check the exit code of commands using `$?`:
   ```bash
   command
   echo "Exit code: $?"
   ```

#### Q11: Write a shell script to find and delete files older than 30 days.

**Answer**:

```bash
#!/bin/bash
# Delete files older than 30 days

if [ $# -ne 1 ]; then
    echo "Usage: $0 <directory>"
    exit 1
fi

directory="$1"

if [ ! -d "$directory" ]; then
    echo "Error: $directory is not a valid directory"
    exit 1
fi

echo "Finding files older than 30 days in $directory"
find "$directory" -type f -mtime +30 -exec rm -f {} \;
echo "Old files have been removed."
```

This script uses the `find` command with `-mtime +30` to find files modified more than 30 days ago, and
`-exec rm -f {} \;` to remove each matching file.

#### Q12: What is the shebang line in a shell script? Why is it important?

**Answer**: The shebang line is the first line of a shell script that starts with `#!` followed by the path to the
interpreter. For example:

```bash
#!/bin/bash
```

It's important because it tells the system which interpreter to use when executing the script. Without it, the system
might use a different shell than intended, causing compatibility issues. Different shells (bash, sh, zsh, etc.) have
different features and syntax, so specifying the correct interpreter ensures the script runs as expected.

### Advanced Linux and Shell Scripting Questions

#### Q13: How would you find the top 5 processes consuming the most memory?

**Answer**: You can use the `ps` command combined with `sort` and `head`:

```bash
ps aux --sort=-%mem | head -n 6
```

This command:

1. Lists all processes with detailed information
2. Sorts by memory usage in descending order
3. Shows the first 6 lines (header + top 5 processes)

#### Q14: Explain the use of pipes and redirection in Linux.

**Answer**:

- **Pipes** (`|`) connect the output of one command to the input of another. For example:
  ```bash
  cat file.txt | grep "error" | sort
  ```
  This reads file.txt, filters for lines containing "error", and sorts the results.

- **Redirection** changes where input comes from or where output goes:
    - `>` redirects output to a file (overwriting it)
    - `>>` appends output to a file
    - `<` takes input from a file
    - `2>` redirects error output to a file
    - `2>&1` redirects errors to the same place as standard output

Examples:

```bash
# Send output to a file
ls -l > listing.txt

# Append output to a file
echo "New line" >> notes.txt

# Take input from a file
sort < unsorted.txt

# Redirect both output and errors to a file
command > output.txt 2>&1
```

#### Q15: Write a script to monitor disk space and send an email alert if usage exceeds 90%.

**Answer**:

```bash
#!/bin/bash
# Monitor disk space and alert if usage exceeds threshold

THRESHOLD=90
EMAIL="admin@example.com"
HOSTNAME=$(hostname)

# Get disk usage
USAGE=$(df -h | grep '/dev/sda1' | awk '{print $5}' | sed 's/%//')

if [ "$USAGE" -gt "$THRESHOLD" ]; then
    # Prepare email content
    SUBJECT="ALERT: Disk usage above $THRESHOLD% on $HOSTNAME"
    BODY="Disk usage is at $USAGE% on $HOSTNAME.\n\nFull disk information:\n$(df -h)"
    
    # Send email alert
    echo -e "$BODY" | mail -s "$SUBJECT" "$EMAIL"
    
    echo "Alert sent: Disk usage at $USAGE%"
else
    echo "Disk usage normal: $USAGE%"
fi
```

This script checks the disk usage of /dev/sda1, compares it to a threshold, and sends an email alert if the threshold is
exceeded.

## 5️⃣ Practical Tips for Amdocs Interviews

### Preparation Strategies

1. **Practice on a Linux system**: If you don't have Linux installed, use a virtual machine, WSL (Windows Subsystem for
   Linux), or online terminals like JSLinux.

2. **Create a cheat sheet**: List commonly used commands, their options, and examples.

3. **Write sample scripts**: Practice creating simple scripts that demonstrate loops, conditions, functions, and error
   handling.

4. **Understand the Java-Linux connection**: Know how to run Java applications on Linux, check logs, and troubleshoot
   common issues.

5. **Be ready for scenario-based questions**: "How would you solve X problem using Linux commands?"

### During the Interview

1. **Think aloud**: When solving command-line problems, explain your thought process.

2. **Ask clarifying questions**: If a question is ambiguous, seek clarification before answering.

3. **Consider performance and security**: Mention potential performance implications or security concerns with your
   solutions.

4. **Offer alternatives**: If you know multiple ways to solve a problem, mention them.

By understanding these Linux, UNIX, and shell scripting concepts, you'll be well-prepared for the technical aspects of
your Amdocs interview. Remember that practical experience is invaluable, so try to use these commands and write scripts
regularly in your preparation.