package com.oleszeksylwester.dmssb.DMSSB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocumentController {

    @GetMapping("documents")
    private String displayAllDocuments(){
        return "documents";
    }
}
