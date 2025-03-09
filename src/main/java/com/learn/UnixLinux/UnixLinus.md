---

# **1. Introduction to Unix/Linux**

✅ **What is Unix/Linux?**

- **Unix** is a powerful, multiuser, multitasking operating system.
- **Linux** is an open-source Unix-like OS used in servers, development, and DevOps.

✅ **Why Learn Unix/Linux?**

- Used in **software development**, **servers**, **cloud computing**, and **DevOps**.
- Strong **command-line interface (CLI)** skills are valued in tech jobs.

✅ **Common Shells in Unix/Linux:**  
| **Shell**  | **Description** |
|------------|------------------------------|
| **bash**   | Most popular, used in Linux |
| **sh**     | Original Unix shell |
| **zsh**    | Advanced shell with better features |
| **fish**   | User-friendly shell |

---

# **2. Basic Unix/Linux Commands**

### **🔹 Navigating the File System**

| **Command** | **Description**             |
|-------------|-----------------------------|
| `pwd`       | Shows current directory     |
| `ls`        | Lists files and directories |
| `cd dir`    | Changes to directory `dir`  |
| `cd ..`     | Moves up one level          |

#### **Example:**

```bash
pwd
ls
cd /home/user/Documents
```

✅ **Output:**

```
/home/user
Documents  Downloads  Music  Pictures
```

---

### **🔹 File and Directory Operations**

| **Command**      | **Description**              |
|------------------|------------------------------|
| `touch file.txt` | Creates an empty file        |
| `mkdir newdir`   | Creates a new directory      |
| `rm file.txt`    | Deletes a file               |
| `rm -r dir`      | Deletes a directory          |
| `cp file1 file2` | Copies file1 to file2        |
| `mv file1 file2` | Moves/renames file1 to file2 |

#### **Example:**

```bash
touch myfile.txt
mkdir mydir
mv myfile.txt mydir/
ls mydir/
```

✅ **Output:**

```
myfile.txt
```

---

### **🔹 Viewing and Editing Files**

| **Command** | **Description**           |
|-------------|---------------------------|
| `cat file`  | Displays file contents    |
| `less file` | Views file with scrolling |
| `head file` | Shows first 10 lines      |
| `tail file` | Shows last 10 lines       |
| `nano file` | Opens file in Nano editor |

#### **Example:**

```bash
cat myfile.txt
head -5 myfile.txt
```

---

### **🔹 User and Permission Management**

| **Command**       | **Description**          |
|-------------------|--------------------------|
| `whoami`          | Shows current user       |
| `id`              | Shows user ID            |
| `chmod 755 file`  | Changes file permissions |
| `chown user file` | Changes file ownership   |

#### **Example:**

```bash
whoami
chmod 644 myfile.txt
ls -l myfile.txt
```

✅ **Output:**

```
-rw-r--r-- 1 user user 0 Mar 7 12:00 myfile.txt
```

---

### **🔹 Process Management**

| **Command** | **Description**            |
|-------------|----------------------------|
| `ps`        | Shows running processes    |
| `top`       | Displays live system usage |
| `kill PID`  | Kills a process by ID      |

#### **Example:**

```bash
ps aux | grep java
kill 1234
```

---

# **3. Unix/Linux Q&A**

#### 1️⃣ What is the difference between Unix and Linux?

✅ **Unix** is a proprietary OS, while **Linux** is open-source and widely used.

#### 2️⃣ How do you check system resource usage?

✅ Use `top` or `htop`.

#### 3️⃣ How do you search for a file in Linux?

✅ Use the `find` command:

```bash
find /home/user -name "file.txt"
```

---

# ✅ **Next Steps**

📌 Go through these commands and **try them out in a Linux terminal**. Once comfortable, we’ll move on to **SQL! 🚀**