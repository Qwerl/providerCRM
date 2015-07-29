package ru.cg.providerCRM.services.impl;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Producer;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.repository.ProducerRepository;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProducerService;

import java.util.List;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    public ProducerRepository producerRepository;

    @Autowired
    public EmployeeService employeeService;

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
        Employee employee = employeeService.addEmployee(newEmployee);
        producer.addEmployee(employee);
        producerRepository.saveAndFlush(producer);
    }

    public void deleteEmployee(Long employeeId, Long producerId) {
        Employee employee = employeeService.getById(employeeId);
        employeeService.deleteEmployee(employee);
    }

    @Override
    public List<Producer> getProducerBySpecificText(String text) {
        String textToSearch = "%" + text + "%";

        List<Producer> producers = producerRepository.findDistinctProducerByNameLikeIgnoreCaseOrAddressLikeIgnoreCaseOrNoteLikeIgnoreCaseOrEmployeesFullNameLikeIgnoreCaseOrProvidersNameLikeIgnoreCase(textToSearch,textToSearch,textToSearch,textToSearch,textToSearch);

        if (producers == null) {
            return ListUtils.EMPTY_LIST;
        } else {
            return producers;
        }
    }
}