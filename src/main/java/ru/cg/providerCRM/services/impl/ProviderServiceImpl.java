package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.*;
import ru.cg.providerCRM.repository.DocumentRepository;
import ru.cg.providerCRM.repository.EmployeeRepository;
import ru.cg.providerCRM.repository.ProviderRepository;
import ru.cg.providerCRM.services.ProviderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    public ProviderRepository providerRepository;

    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public DocumentRepository documentRepository;

    public void addProvider(Provider provider) {
        providerRepository.saveAndFlush(provider);
    }

    public void deleteProvider(Provider provider) {
        providerRepository.delete(provider);
    }

    public void updateProvider(Provider provider) {
        providerRepository.saveAndFlush(provider);
    }

    public Provider getById(Long id) {
        return providerRepository.getOne(id);
    }

    public Provider getByName(String name) {
        return providerRepository.findByName(name);
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public List<Product> getAllProductsByProviderName(String providerName) {
        Provider provider = providerRepository.findByName(providerName);
        return provider.getProducts();
    }

    public void addEmployee(Employee newEmployee, Long providerId) {
        Provider provider = providerRepository.findOne(providerId);
        Employee employee = employeeRepository.saveAndFlush(newEmployee);

        provider.addEmployee(employee);
        providerRepository.saveAndFlush(provider);
    }

    public void deleteEmployee(Long employeeId, Long providerId) {
        Provider provider = providerRepository.findOne(providerId);
        Employee employee = employeeRepository.findOne(employeeId);
        provider.removeEmployee(employee);
        providerRepository.saveAndFlush(provider);
    }

    public List<Provider> getProvidersContainsTag(Tag tag) {
        List<Provider> providers = providerRepository.findAll();
        List<Provider> result = new ArrayList<Provider>();
        for (Provider provider : providers) {
            List<Tag> tags = provider.getTags();
            if (tags.contains(tag)) {
                result.add(provider);
            }
        }
        return result;
    }

    public void addDocument(Document newDocument, Long providerId) {
        Provider provider = providerRepository.findOne(providerId);
        Document document = documentRepository.saveAndFlush(newDocument);
        provider.addDocument(document);
        providerRepository.saveAndFlush(provider);
    }
}
