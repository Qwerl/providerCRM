package ru.cg.providerCRM.repository;

import org.springframework.stereotype.Repository;
import ru.cg.providerCRM.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByTagText(String name);
}
