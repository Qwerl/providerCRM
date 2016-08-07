package ru.cg.providerCRM.services.impl;

import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Document;
import ru.cg.providerCRM.entity.Employee;
import ru.cg.providerCRM.entity.Product;
import ru.cg.providerCRM.entity.Provider;
import ru.cg.providerCRM.repository.DocumentRepository;
import ru.cg.providerCRM.repository.ProductRepository;
import ru.cg.providerCRM.repository.ProviderRepository;
import ru.cg.providerCRM.services.EmployeeService;
import ru.cg.providerCRM.services.ProviderService;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;
    private final EmployeeService employeeService;
    private final ProductRepository productRepository;
    private final DocumentRepository documentRepository;

    @Autowired
    public ProviderServiceImpl(ProductRepository productRepository, DocumentRepository documentRepository, EmployeeService employeeService, ProviderRepository providerRepository) {
        this.productRepository = productRepository;
        this.documentRepository = documentRepository;
        this.employeeService = employeeService;
        this.providerRepository = providerRepository;
    }

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
        Employee employee = employeeService.addEmployee(newEmployee);

        provider.addEmployee(employee);
        providerRepository.saveAndFlush(provider);
    }

    public void deleteEmployee(Long employeeId, Long providerId) {
        Employee employee = employeeService.getById(employeeId);
        employeeService.deleteEmployee(employee);
    }

    public void addDocument(Document newDocument, Long providerId) {
        Provider provider = providerRepository.findOne(providerId);
        Document document = documentRepository.saveAndFlush(newDocument);
        document.setProvider(provider);
        documentRepository.saveAndFlush(document);
    }

    public List<Provider> getProviderBySpecificText(String text) {
        String textToSearch = "%" + text + "%";

        List<Provider> providers = providerRepository.findDistinctProviderByNameLikeIgnoreCaseOrNoteLikeIgnoreCaseOrAddressLikeIgnoreCaseOrStorageAddressLikeIgnoreCaseOrProductsNameLikeIgnoreCaseOrEmployeesFullNameLikeOrDocumentsNameLikeIgnoreCase(textToSearch, textToSearch, textToSearch, textToSearch, textToSearch, textToSearch, textToSearch);

        if (providers == null) {
            return ListUtils.EMPTY_LIST;
        } else {
            return providers;
        }
    }

    public void deleteProduct(Long productId, Long providerId) {
        Product product = productRepository.getOne(productId);
        Provider provider = providerRepository.getOne(providerId);
        provider.getProducts().remove(product);
        providerRepository.saveAndFlush(provider);
    }

}