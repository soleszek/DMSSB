package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.service.DocumentService;
import com.oleszeksylwester.dmssb.DMSSB.utils.DataOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
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
    private ModelAndView displayAllDocuments(){
        ModelAndView mov = new ModelAndView();

        List<Document> documents = documentService.findAll();
        List<Document> approvedDocuments = documentService.findAllApproved();

        mov.addObject("document", new Document());
        mov.addObject("approvedDocuments", approvedDocuments);
        mov.addObject("documents", documents);
        mov.setViewName("documents");

        return mov;
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
        String name = document.getName();
        List<Document> revisions = documentService.findAllRevisions(name);

        mov.addObject(document);
        mov.addObject("revisions", revisions);
        mov.setViewName("revisions");

        return mov;
    }

    @GetMapping("/document/{documentId}/routes")
    private ModelAndView displayDocumentRoutes(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        mov.addObject("document", document);
        mov.setViewName("routes");

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
    private ModelAndView displayDocumentViewer(@PathVariable("documentId") Long documentId, HttpServletResponse resp){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        int idInt = Math.toIntExact(documentId);
        String fileName = DataOperations.documentsPath + String.valueOf(idInt);

        File file = new File(fileName);

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Length", String.valueOf(fileName));
        resp.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
        try {
            Files.copy(file.toPath(), resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Can't read file from the sorce");
        }

        mov.addObject("document", document);
        mov.setViewName("viewer");

        return mov;
    }

}
