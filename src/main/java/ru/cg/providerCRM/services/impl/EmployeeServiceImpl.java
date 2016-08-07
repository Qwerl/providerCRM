package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.repository.EmployeeRepository;
import ru.cg.providerCRM.services.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee addEmployee(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    public void deleteEmployee(Employee employee) {
        repository.delete(employee);
    }

    public void updateEmployee(Employee employee) {
        repository.saveAndFlush(employee);
    }

    public Employee getById(Long id) {
        return repository.getOne(id);
    }

    public Employee getByName(String name) {
        return repository.findByFullName(name);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

}