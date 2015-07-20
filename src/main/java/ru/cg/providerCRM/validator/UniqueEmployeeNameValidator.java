package ru.cg.providerCRM.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cg.providerCRM.repository.EmployeeRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueEmployeeNameValidator implements ConstraintValidator<UniqueEmployeeName,String> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(UniqueEmployeeName uniqueEmployeeName) {
    }

    @Override
    public boolean isValid(String employeeName, ConstraintValidatorContext constraintValidatorContext) {
        if (employeeRepository.findByFullName(employeeName) == null) {
            System.out.println(employeeName);
            return true;
        } else {
            System.out.println(employeeName);
            return false;
        }
    }
}
