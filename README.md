# SmartStudent: Java-Based Student Management System

## 📘 Overview
SmartStudent is a **Java Swing GUI-based Student Management System** with MySQL integration.  
It allows an admin to log in, manage student records, search and filter data, update/delete students, view statistics, and export data to CSV.

---

## ⚙️ Features

| Feature | Description |
|---------|-------------|
| 🔐 Admin Login | Default credentials: `admin` / `admin123`. Login validation included. |
| 📋 Add Student | Add new student with Name, Roll No, Department, Email, Phone, and Marks. |
| ✏️ Update Student | Update existing student details. |
| ❌ Delete Student | Delete student records from the database. |
| 🔍 Search & Filter | Search by Name, Roll No, Department, or Marks range. |
| 📊 View Students | View all students in a table format in GUI. |
| 🧾 Export to CSV | Export student data to a CSV file. |
| ✅ Automatic Grade | Calculates grade automatically based on marks. |
| 📈 Statistics | Show total students, highest/lowest marks, department-wise count. |
| 💾 Database | MySQL database integration via JDBC. |

---

## 🛠️ Tools Used

| Tool | Purpose |
|------|---------|
| Java (JDK 8+) | Core programming language |
| Swing GUI | Desktop interface |
| JDBC | Database connectivity |
| MySQL / XAMPP / Workbench | Database setup and management |
| IntelliJ IDEA / Eclipse / VS Code | IDE for coding |

---

## 🗂️ Folder Structure

| Folder/File | Description |
|------------|-------------|
| `src/` | Java source code |
| `src/AdminService.java` | CRUD operations for students |
| `src/CSVExporter.java` | Export data to CSV |
| `src/DatabaseConnection.java` | MySQL connection class |
| `src/LoginFrame.java` | GUI login form |
| `src/Student.java` | Model class for student |
| `src/StudentDAO.java` | Database operations (DAO) |
| `src/StudentFrame.java` | GUI for managing students |
| `src/StudentManagementSystem.java` | Console-based testing |
| `Student.sql` | SQL schema for MySQL |
| `students.csv` | Exported CSV file |
| `README.md` | Project documentation |
| `SmartStudent.iml` | IDE project file |
| `External Libraries/` | Required libraries (JDK, MySQL connector) |

---

## 💻 How to Run

| Step | Instruction | Screenshot |
|------|------------|------------|
| 1    | Import the project in IDE | ![Step1](screenshots/step1_import.png) |
| 2    | Execute Student.sql in MySQL | ![Step2](screenshots/step2_mysql.png) |
| 3    | Update DatabaseConnection.java | ![Step3](screenshots/step3_db_credentials.png) |
| 4    | Run Main.java | ![Step4](screenshots/step4_run.png) |
| 5    | Login using admin/admin123 | ![Step5](screenshots/step5_login.png) |
| 6    | Add/Update/Delete/Search student records | ![Step6](screenshots/step6_add_student.png) |
| 7    | View statistics | ![Step7](screenshots/step7_statistics.png) |


---

## 📁 Database Table: `students`

| Column      | Type          |
|------------|---------------|
| id         | INT (Primary Key, Auto Increment) |
| name       | VARCHAR(100) |
| roll_no    | VARCHAR(50)  |
| department | VARCHAR(50)  |
| email      | VARCHAR(100) |
| phone      | VARCHAR(20)  |
| subject1   | INT          |
| subject2   | INT          |
| subject3   | INT          |
| total      | INT          |
| grade      | VARCHAR(5)   |

---

**👨‍💻 Developed by:** [Gaurav]  
**🎓 Project:** SmartStudent – Java-Based Student Management System  
**📅 Submission:** [21-10-2025]
