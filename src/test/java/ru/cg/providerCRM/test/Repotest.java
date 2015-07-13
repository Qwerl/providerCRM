package ru.cg.providerCRM.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Producer;
import ru.cg.providerCRM.repository.EmployeeRepository;
import ru.cg.providerCRM.repository.ProducerRepository;
public class Repotest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-core-config.xml"});
        EmployeeRepository repository;
        repository = context.getBean(EmployeeRepository.class);
        Employee employee;
        //employee = repository.findByFullName("Diman");
        employee = repository.getOne(2L);
        System.out.println(employee);

        ProducerRepository producerRepository = context.getBean(ProducerRepository.class);
        Producer producer = producerRepository.getOne(1L);
        System.out.println(producer);
    }
}
