package ru.cg.providerCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.*;

import java.util.List;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Producer findByName(String name);

    List<Producer> findDistinctProducerByNameLikeIgnoreCaseOrAddressLikeIgnoreCaseOrNoteLikeIgnoreCaseOrEmployeesFullNameLikeIgnoreCaseOrProvidersNameLikeIgnoreCase(String magic1, String magic2, String magic3, String magic4, String magic5);
}
