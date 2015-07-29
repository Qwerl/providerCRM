package ru.cg.providerCRM.web.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Employee;

import javax.validation.constraints.Size;

/**
 * Класс для редактирования существующих сотрудников
 */
public class EmployeeEditForm {

    private Long id;

    @NotBlank(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String fullName;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email введен некорректно")
    private String email;

    @NotBlank(message = "Мобильный телефон не должен быть пустым")
    @Size(min = 2, max = 20, message = "Длина должна быть больше 2 символов, но не больше 20")
    private String homePhoneNumber;

    @NotBlank(message = "Рабочий телефон не должен быть пустым")
    @Size(min = 2, max = 20, message = "Длина должна быть больше 2 символов, но не больше 20")
    private String workPhoneNumber;

    @NotBlank(message = "Должность не должена быть пустой")
    @Size(min = 2, max = 255, message = "Длина должна быть больше 2 символов, но не больше 255")
    private String position;


    private Long providerId;
    private Long producerId;

    public EmployeeEditForm() {
    }

    public EmployeeEditForm(Employee employee) {
        this.setId(employee.getId());
        this.setFullName(employee.getFullName());
        this.setEmail(employee.getEmail());
        this.setHomePhoneNumber(employee.getHomePhoneNumber());
        this.setWorkPhoneNumber(employee.getWorkPhoneNumber());
        this.setPosition(employee.getPosition());
        if (employee.getProvider() != null) {
            providerId = employee.getProvider().getId();
        } else {
            providerId = 0L;
        }
        if (employee.getProducer() != null) {
            producerId = employee.getProducer().getId();
        } else {
            producerId = 0L;
        }
    }

    public Employee getEmployee() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFullName(fullName);
        employee.setEmail(email);
        employee.setHomePhoneNumber(homePhoneNumber);
        employee.setWorkPhoneNumber(workPhoneNumber);
        employee.setPosition(position);
        return employee;
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

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    @Override
    public String toString() {
        return "EmployeeEditForm{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", homePhoneNumber='" + homePhoneNumber + '\'' +
                ", workPhoneNumber='" + workPhoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", providerId=" + providerId +
                ", producerId=" + producerId +
                '}';
    }

}
