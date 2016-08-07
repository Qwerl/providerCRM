package ru.cg.providerCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.Provider;

import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
    Provider findByName(String name);

    List<Provider> findDistinctProviderByNameLikeIgnoreCaseOrNoteLikeIgnoreCaseOrAddressLikeIgnoreCaseOrStorageAddressLikeIgnoreCaseOrProductsNameLikeIgnoreCaseOrEmployeesFullNameLikeOrDocumentsNameLikeIgnoreCase(String magic, String magic2, String magic3, String magic4, String magic5, String magic6, String magic7);

}