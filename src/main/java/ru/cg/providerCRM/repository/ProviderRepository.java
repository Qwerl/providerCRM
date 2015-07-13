package ru.cg.providerCRM.repository;

import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByName(String name);
}
