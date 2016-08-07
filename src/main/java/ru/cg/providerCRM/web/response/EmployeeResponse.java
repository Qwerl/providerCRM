package ru.cg.providerCRM.web.response;

import lombok.Data;
import ru.cg.providerCRM.entity.Employee;

@Data
public class EmployeeResponse extends ValidationResponse {

    private Long id;
    private String fullName;
    private String email;
    private String homePhoneNumber;
    private String workPhoneNumber;
    private String position;

    public EmployeeResponse(Employee employee) {
        this.setId(employee.getId());
        this.setFullName(employee.getFullName());
        this.setEmail(employee.getEmail());
        this.setHomePhoneNumber(employee.getHomePhoneNumber());
        this.setWorkPhoneNumber(employee.getWorkPhoneNumber());
        this.setPosition(employee.getPosition());
    }

}