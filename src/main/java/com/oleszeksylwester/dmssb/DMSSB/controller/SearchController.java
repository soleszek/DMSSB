package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class SearchController {

    private static final Logger LOGGER = Logger.getLogger(SearchController.class.getName());

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/advancedsearch")
    private String displayAllDocuments(){

        return "advancedsearch";
    }

    @GetMapping("/quicksearch")
    private ModelAndView displayQuickSearchResults(@RequestParam ("phrase") String phrase){
        ModelAndView mov = new ModelAndView();

        List<Document> results = searchService.searchDocuments(phrase);

        mov.addObject("results", results);
        mov.setViewName("quicksearch");

        return mov;
    }

    @PostMapping("/adcancedsearch/document")
    private ModelAndView displayAdvancedSearchDocument(){
        ModelAndView mov = new ModelAndView();

        return mov;
    }

    @PostMapping("/adcancedsearch/route")
    private ModelAndView displayAdvancedSearchRoute(){
        ModelAndView mov = new ModelAndView();

        return mov;
    }

    @PostMapping("/adcancedsearch/task")
    private ModelAndView displayAdvancedSearchTask(){
        ModelAndView mov = new ModelAndView();

        return mov;
    }

    @PostMapping("/adcancedsearch/user")
    private ModelAndView displayAdvancedSearchUser(){
        ModelAndView mov = new ModelAndView();

        return mov;
    }
}
