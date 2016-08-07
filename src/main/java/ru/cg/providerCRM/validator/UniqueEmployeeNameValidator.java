package ru.cg.providerCRM.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cg.providerCRM.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmployeeNameValidator implements ConstraintValidator<UniqueEmployeeName,String> {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public UniqueEmployeeNameValidator(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void initialize(UniqueEmployeeName uniqueEmployeeName) {
    }

    @Override
    public boolean isValid(String employeeName, ConstraintValidatorContext constraintValidatorContext) {
        return employeeRepository.findByFullName(employeeName) == null;
    }
}
