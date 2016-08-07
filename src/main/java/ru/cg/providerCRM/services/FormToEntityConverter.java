package ru.cg.providerCRM.services;

import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.web.form.EmployeeEditForm;

public class FormToEntityConverter {

    public static Employee EmployeeFormToEmployee(EmployeeEditForm form,
                                                  ProviderService providerService,
                                                  ProducerService producerService) {
        Employee employee = new Employee();
        employee.setId(form.getId());
        employee.setFullName(form.getFullName());
        employee.setEmail(form.getEmail());
        employee.setHomePhoneNumber(form.getHomePhoneNumber());
        employee.setWorkPhoneNumber(form.getWorkPhoneNumber());
        employee.setPosition(form.getPosition());
        if (form.getProviderId() != 0) {
            employee.setProvider(providerService.getById(form.getProviderId()));
        } else {
            employee.setProvider(null);
        }

        if (form.getProducerId() != 0) {
            employee.setProducer(producerService.getById(form.getProducerId()));
        } else {
            employee.setProducer(null);
        }
        return employee;
    }

}