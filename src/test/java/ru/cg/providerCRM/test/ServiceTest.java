package ru.cg.providerCRM.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;
import ru.cg.providerCRM.entity.*;
import ru.cg.providerCRM.services.*;

import java.util.List;

import static org.testng.Assert.assertNotNull;

@ContextConfiguration(locations = "/test-app-context.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class ServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private ProducerService producerService;

    @Test
    public void testSaveEmployee() {
        Employee vasya = new Employee();
        vasya.setFullName("Vasya");
        vasya.setEmail("Vasek@test.com");
        vasya.setHomePhoneNumber("777-777");
        vasya.setWorkPhoneNumber("666-666");
        employeeService.addEmployee(vasya);

        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees);

        for (Employee employee : employees) {
            System.out.println(employee.getId() + " " + employee.getFullName());
        }
    }

    @Test
    public void testSaveEmployeeToProvider() {
        Employee petya = new Employee();
        petya.setFullName("Petr");
        petya.setEmail("Petr@test.com");
        petya.setHomePhoneNumber("43-234");
        petya.setWorkPhoneNumber("632466-6643536");
        employeeService.addEmployee(petya);

        Producer producer = producerService.getById(1L);
        producerService.addEmployee(petya, producer.getId());


        List<Employee> employees = employeeService.getAllEmployees();
        assertNotNull(employees);

        for (Employee employee : employees) {
            System.out.println(employee.getId() + " " + employee.getFullName());
            System.out.println(employee);
        }

        System.out.println(producerService.getById(1L));
    }
}
