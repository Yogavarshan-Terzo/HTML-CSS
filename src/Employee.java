

import java.util.Date;

public class Employee {
    int id;
    String name;
    int salary;
    String department;
    Date joiningDate;
    String mobileNo;

    public Employee(int id, String name, int salary, String department, Date joiningDate, String mobileNo) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.joiningDate = joiningDate;
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", joiningDate=" + joiningDate +
                ", mobileNo='" + mobileNo + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
