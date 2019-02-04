package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.service.DocumentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class DocumentController {

    private static final Logger LOGGER = Logger.getLogger(DocumentController.class.getName());

    @Autowired
    DocumentService documentService;

    @GetMapping("/documents")
    private String displayAllDocuments(Model model){
        List<Document> documents = documentService.findAll();
        List<Document> approvedDocuments = documentService.findAllApproved();

        model.addAttribute("document", new Document());
        model.addAttribute("approvedDocuments", approvedDocuments);
        model.addAttribute("documents", documents);

        return "documents";
    }

    @PostMapping("/createDocument")
    private ModelAndView createDocument(@ModelAttribute("document") Document document, @RequestParam("file") MultipartFile file, @RequestParam("doctype") String type, ModelMap modelMap) {

        String path = Paths.get(file.getOriginalFilename()).getFileName().toString();
        modelMap.addAttribute("file", file);
        ModelAndView mov = new ModelAndView();

        InputStream fileContent = null;
        try {
            fileContent = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Problem with acquring stream from MultipartFile");
        }
        documentService.SaveOrUpdate(document, fileContent, type, path);
        mov.addObject("document", document);
        mov.setViewName("document");

        return mov;
    }

    @GetMapping("/document")
    private ModelAndView displayDocument(@ModelAttribute("document") Document document){
        ModelAndView mov = new ModelAndView();
        mov.addObject("document", document);
        mov.setViewName("document");

        return mov;
    }

    @GetMapping("/document/{documentId}")
    private ModelAndView displayDocument(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("document");

        return mov;
    }

    @PostMapping("/updateDocument")
    private ModelAndView updateDocument(@ModelAttribute Document document){
        ModelAndView mov = new ModelAndView();
        mov.addObject(document);
        mov.setViewName("document");

        return mov;
    }

    @GetMapping("/document/{documentId}/revisions")
    private ModelAndView displayDocumentRevisions(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("revisions");

        return mov;
    }

    @GetMapping("/document/{documentId}/routes")
    private ModelAndView displayDocumentRoutes(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("route");

        return mov;
    }

    @GetMapping("/document/{documentId}/lifecycle")
    private ModelAndView displayDocumentLifecycle(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("lifecycle");

        return mov;
    }

    @GetMapping("/document/{documentId}/viewer")
    private ModelAndView displayDocumentViewer(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("viewer");

        return mov;
    }

}
