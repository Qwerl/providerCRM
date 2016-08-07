package ru.cg.providerCRM.services;

import ru.cg.providerCRM.entity.*;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    void updateEmployee(Employee employee);
    Employee getById(Long id);
    Employee getByName(String name);
    List<Employee> getAllEmployees();
}