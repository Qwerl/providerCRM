package ru.cg.providerCRM.entity;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "HOME_PHONE_NUMBER")
    private String homePhoneNumber;

    @Column(name = "WORK_PHONE_NUMBER")
    private String workPhoneNumber;

    @Column(name = "POSITION")
    private String position;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!id.equals(employee.id)) return false;
        if (!fullName.equals(employee.fullName)) return false;
        if (email != null ? !email.equals(employee.email) : employee.email != null) return false;
        if (homePhoneNumber != null ? !homePhoneNumber.equals(employee.homePhoneNumber) : employee.homePhoneNumber != null)
            return false;
        return !(workPhoneNumber != null ? !workPhoneNumber.equals(employee.workPhoneNumber) : employee.workPhoneNumber != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + fullName.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (homePhoneNumber != null ? homePhoneNumber.hashCode() : 0);
        result = 31 * result + (workPhoneNumber != null ? workPhoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", workPhoneNumber='" + workPhoneNumber + '\'' +
                '}';
    }
}
