package com.oleszeksylwester.dmssb.DMSSB.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class SearchController {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class.getName());

    @GetMapping("/advancedsearch")
    private String displayAllDocuments(){

        return "advancedsearch";
    }
}
