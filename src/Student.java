public class Student {
    private int id, sub1, sub2, sub3, total;
    private String name, rollNo, dept, email, phone, grade;

    public Student() {}

    public Student(String name, String rollNo, String dept, String email, String phone,
                   int s1, int s2, int s3) {
        this.name = name;
        this.rollNo = rollNo;
        this.dept = dept;
        this.email = email;
        this.phone = phone;
        this.sub1 = s1;
        this.sub2 = s2;
        this.sub3 = s3;
        calculateTotalAndGrade();
        class student {
            private int id, sub1, sub2, sub3, total;
            private String name, rollNo, dept, email, phone, grade;

            public student() {}

            public student(String name, String rollNo, String dept, String email, String phone,
                           int s1, int s2, int s3) {
                this.name = name;
                this.rollNo = rollNo;
                this.dept = dept;
                this.email = email;
                this.phone = phone;
                this.sub1 = s1;
                this.sub2 = s2;
                this.sub3 = s3;
                calculateTotalAndGrade();
            }

            private void calculateTotalAndGrade() {
                total = sub1 + sub2 + sub3;
                int average = total / 3;
                if (average >= 90) grade = "A+";
                else if (average >= 80) grade = "A";
                else if (average >= 70) grade = "B";
                else if (average >= 60) grade = "C";
                else grade = "F";
            }

            public int getId() { return id; }
            public void setId(int id) { this.id = id; }
            public String getName() { return name; }
            public void setName(String name) { this.name = name; }
            public String getRollNo() { return rollNo; }
            public void setRollNo(String rollNo) { this.rollNo = rollNo; }
            public String getDept() { return dept; }
            public void setDept(String dept) { this.dept = dept; }
            public String getEmail() { return email; }
            public void setEmail(String email) { this.email = email; }
            public String getPhone() { return phone; }
            public void setPhone(String phone) { this.phone = phone; }
            public int getSub1() { return sub1; }
            public void setSub1(int sub1) { this.sub1 = sub1; calculateTotalAndGrade(); }
            public int getSub2() { return sub2; }
            public void setSub2(int sub2) { this.sub2 = sub2; calculateTotalAndGrade(); }
            public int getSub3() { return sub3; }
            public void setSub3(int sub3) { this.sub3 = sub3; calculateTotalAndGrade(); }
            public int getTotal() { return total; }
            public String getGrade() { return grade; }
        }

    }

    private void calculateTotalAndGrade() {
        total = sub1 + sub2 + sub3;
        int average = total / 3;
        if (average >= 90) grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else grade = "F";
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getSub1() { return sub1; }
    public void setSub1(int sub1) { this.sub1 = sub1; calculateTotalAndGrade(); }
    public int getSub2() { return sub2; }
    public void setSub2(int sub2) { this.sub2 = sub2; calculateTotalAndGrade(); }
    public int getSub3() { return sub3; }
    public void setSub3(int sub3) { this.sub3 = sub3; calculateTotalAndGrade(); }
    public int getTotal() { return total; }
    public String getGrade() { return grade; }
}
