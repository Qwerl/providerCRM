package ru.cg.providerCRM.web.form;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import ru.cg.providerCRM.entity.Employee;

import javax.validation.constraints.Size;

/**
 * Класс для редактирования существующих сотрудников
 */
@Data
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

    private Boolean isUnbound = false;

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

}