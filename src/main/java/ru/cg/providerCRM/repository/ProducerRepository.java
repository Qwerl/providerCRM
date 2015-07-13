package ru.cg.providerCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.*;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Producer findByName(String name);
}
