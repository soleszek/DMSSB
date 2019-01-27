package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    @Transactional
    public void SaveOrUpdate(Document document){
        documentRepository.save(document);
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
