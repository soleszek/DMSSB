package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final DocumentService documentService;

    @Autowired
    public SearchService(DocumentService documentService) {
        this.documentService = documentService;
    }

    public List<Document> searchDocuments(String phrase){
        List<Document> documents = documentService.findAll();

        return documents.stream()
                .filter(d ->
                                d.getName().equals(phrase) ||
                                d.getType().equals(phrase) ||
                                d.getTitle().equals(phrase) ||
                                d.getDescription().equals(phrase) ||
                                d.getState().equals(phrase) ||
                                d.getOwner().getUsername().equals(phrase))
                .collect(Collectors.toList());
    }
}
