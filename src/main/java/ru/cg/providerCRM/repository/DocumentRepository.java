package ru.cg.providerCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>  {

    Document findByName(String name);

}