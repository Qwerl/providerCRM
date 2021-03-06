package ru.cg.providerCRM.services;

import ru.cg.providerCRM.entity.*;

import java.util.List;

public interface ProducerService {
    void addProducer(Producer producer);
    void deleteProducer(Producer producer);
    void updateProducer(Producer producer);
    Producer getById(Long id);
    Producer getByName(String name);
    List<Producer> getAllProducers();
    List<Provider> getAllProvidersByProducerId(Long producerId);
    void addEmployee(Employee employee, Long producerId);
    void deleteEmployee(Long employeeId, Long producerId);
    List<Producer> getProducerBySpecificText(String text);
}