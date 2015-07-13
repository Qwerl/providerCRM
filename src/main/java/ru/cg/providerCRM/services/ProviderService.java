package ru.cg.providerCRM.services;
import ru.cg.providerCRM.entity.*;

import java.util.List;

public interface ProviderService {
    void addProvider(Provider provider);
    void deleteProvider(Provider provider);
    void updateProvider(Provider provider);
    Provider getById(Long id);
    Provider getByName(String name);
    List<Provider> getAllProviders();
    List<Product> getAllProductsByProviderName(String providerName);
    List<Provider> getProvidersContainsTag(Tag tag);
    void addEmployee(Employee employee, Long providerId);
    void deleteEmployee(Employee employee, Long providerId);
    void addDocument(Document document, Long providerId);
}
