package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.DocumentStates;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.repository.DocumentRepository;
import com.oleszeksylwester.dmssb.DMSSB.utils.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private NameFactory nameFactory;
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @Autowired
    public DocumentService(DocumentRepository documentRepository, NameFactory nameFactory){
        this.documentRepository = documentRepository;
        this.nameFactory = nameFactory;
    }

    @Transactional
    public void SaveOrUpdate(Document document, InputStream fileContent) {
        document.setRevision(1);
        document.setState(DocumentStates.INWORK.getState());
        document.setLastModification(LocalDate.now());

        documentRepository.save(document);

        String fileName = document.getId().toString();
        DataOperations.saveData(document.getType(), fileContent, fileName);

        Long documentId = document.getId();
        String name = nameFactory.createName(documentId, document.getType());
        document.setName(name);
    }

    @Transactional(readOnly = true)
    public Document findById(Long id){
        return documentRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no document with this id"));
    }

    @Transactional(readOnly = true)
    public List<Document> findAll(){
        return documentRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        documentRepository.deleteById(id);
    }

    @Transactional
    public void delete(Document document){
        documentRepository.delete(document);
    }
}
