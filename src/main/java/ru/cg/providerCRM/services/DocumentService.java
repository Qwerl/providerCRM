package ru.cg.providerCRM.services;

import ru.cg.providerCRM.entity.Document;

public interface DocumentService {
    Document getById(Long id);
}