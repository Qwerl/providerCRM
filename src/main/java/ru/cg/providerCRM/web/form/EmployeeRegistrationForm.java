package ru.cg.providerCRM.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Employee;

import javax.validation.constraints.Size;

@Data
public class EmployeeRegistrationForm {

    private Long id;

    @NotBlank(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String fullName;

    @NotBlank(message = "Email не должен быть путым")
    @Email(message = "Email введен некорректно")
    private String email;

    @NotBlank(message = "Мобильный телефон не должен быть путым")
    @Size(min = 2, max = 20, message = "Длина должна быть больше 2 символов, но не больше 20")
    private String homePhoneNumber;

    @NotBlank(message = "Рабочий телефон не должен быть путым")
    @Size(min = 2, max = 20, message = "Длина должна быть больше 2 символов, но не больше 20")
    private String workPhoneNumber;

    @NotBlank(message = "Должность не должена быть путой")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String position;

    public EmployeeRegistrationForm() {
    }

    public EmployeeRegistrationForm(Employee employee) {
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

}