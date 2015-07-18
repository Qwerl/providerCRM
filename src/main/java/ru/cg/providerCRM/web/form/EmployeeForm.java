package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Employee;

import javax.validation.constraints.Size;

public class EmployeeForm {

    private Long id;

    @NotBlank
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 2, max = 20)
    private String homePhoneNumber;

    @NotBlank
    @Size(min = 2, max = 20)
    private String workPhoneNumber;

    @NotBlank
    private String position;

    public EmployeeForm() {
    }

    public EmployeeForm(Employee employee) {
        this.setId(employee.getId());
        this.setFullName(employee.getFullName());
        this.setEmail(employee.getEmail());
        this.setHomePhoneNumber(employee.getHomePhoneNumber());
        this.setWorkPhoneNumber(employee.getWorkPhoneNumber());
        this.setPosition(employee.getPosition());
    }

    public void fillEmployee(Employee employee) {
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setHomePhoneNumber(homePhoneNumber);
        employee.setWorkPhoneNumber(workPhoneNumber);
        employee.setPosition(position);
    }

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
    public String toString() {
        return "EmployeeForm{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", workPhoneNumber='" + workPhoneNumber + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

}