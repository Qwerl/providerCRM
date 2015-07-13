package ru.cg.providerCRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.cg.providerCRM.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>  {
    Document findByName(String name);
}
