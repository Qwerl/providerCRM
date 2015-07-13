package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.*;
import ru.cg.providerCRM.repository.*;
import ru.cg.providerCRM.services.ProducerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    public ProducerRepository producerRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    public void addProducer(Producer producer) {
        producerRepository.saveAndFlush(producer);
    }

    public void deleteProducer(Producer producer) {
        producerRepository.delete(producer);
    }

    public void updateProducer(Producer producer) {
        producerRepository.saveAndFlush(producer);
    }

    public Producer getById(Long id) {
        return producerRepository.getOne(id);
    }

    public Producer getByName(String name) {
        return producerRepository.findByName(name);
    }

    public List<Producer> getAllProducers() {
        return producerRepository.findAll();
    }

    public List<Provider> getAllProvidersByProducerId(Long producerId) {
        Producer currentProducer = producerRepository.findOne(producerId);
        return currentProducer.getProviders();
    }

    public void addEmployee(Employee newEmployee, Long producerId) {
        Producer producer = producerRepository.findOne(producerId);

        employeeRepository.saveAndFlush(newEmployee);
        Employee employee = employeeRepository.findByFullName(newEmployee.getFullName());

        producer.addEmployee(employee);
        producerRepository.saveAndFlush(producer);
    }

    public List<Producer> getProducersContainsTag(Tag tag) {
        List<Producer> producers = producerRepository.findAll();
        List<Producer> result = new ArrayList<Producer>();
        for (Producer producer : producers) {
            List<Tag> tags = producer.getTags();
            if (tags.contains(tag)) {
                result.add(producer);
            }
        }
        return result;
    }


    public void deleteEmployee(Employee employeeToDelete, Long producerId) {
        Producer producer = producerRepository.findOne(producerId);
        Employee employee = employeeRepository.findByFullName(employeeToDelete.getFullName());
        producer.removeEmployee(employee);
        producerRepository.saveAndFlush(producer);
    }
}