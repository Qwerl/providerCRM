package ru.cg.providerCRM.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cg.providerCRM.entity.Document;
import ru.cg.providerCRM.repository.DocumentRepository;
import ru.cg.providerCRM.services.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document getById(Long id) {
        return documentRepository.findOne(id);
    }
}
