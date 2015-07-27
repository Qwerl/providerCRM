package ru.cg.providerCRM.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.Test;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.repository.*;


import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;


@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring-core-config.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class RepositoryTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProducerRepository producerRepository;

    @Test
    public void mustAddProviderAndCheckExist() {
        Provider toDB = new Provider();

        toDB.setId(1L);
        toDB.setAddress("Test address55");
        toDB.setPhoneNumber("111-111-111");
        toDB.setStorageAddress("Test storage address");
        toDB.setName("Test Name");

        System.out.println("toDB");
        System.out.println(toDB);

        providerRepository.saveAndFlush(toDB);

        Provider fromDB = providerRepository.getOne(1L);

        System.out.println("fromDB");
        System.out.println(fromDB);

        assertNotNull("not null", fromDB);
        assertEquals(toDB, fromDB);
    }

    @Test
    public void mustGetAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        assertTrue(employees.size() > 0);
    }



}
