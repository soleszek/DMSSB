package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.service.DocumentService;
import com.oleszeksylwester.dmssb.DMSSB.service.RouteService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class RouteController {

    private static final Logger LOGGER = Logger.getLogger(RouteController.class.getName());

    private RouteService routeService;
    private UserService userService;
    private DocumentService documentService;

    @Autowired
    public RouteController(RouteService routeService, UserService userService, DocumentService documentService) {
        this.routeService = routeService;
        this.userService = userService;
        this.documentService = documentService;
    }

    @GetMapping("/routeslist")
    private String displayAllRoutes(){

        return "routeslist";
    }

    @GetMapping("/document/{documentId}/routes")
    private ModelAndView displayDocumentRoutes(@PathVariable("documentId") Long documentId){
        ModelAndView mov = new ModelAndView();
        Document document = documentService.findById(documentId);

        List<User> checkers = userService.findCheckers();
        List<User> approvers = userService.findApprovers();

        List<Route> routes = routeService.findRoutesOfDocument(documentId);

        mov.addObject("checkers", checkers);
        mov.addObject("approvers", approvers);
        mov.addObject("route", new Route());
        mov.addObject("document", document);
        mov.addObject("routes", routes);
        mov.setViewName("routes");

        return mov;
    }

    @PostMapping("/new/route")
    private ModelAndView createNewRoute(@ModelAttribute("route") Route route,
                                        @RequestParam("checkingDueDateString") String checkingDueDate,
                                        @RequestParam("deadlineString") String deadline,
                                        @RequestParam("documentId") Long id){

        ModelAndView mov = new ModelAndView();

        Route savedRoute = routeService.SaveOrUpdate(route, checkingDueDate, deadline, id);

        mov.addObject("route", savedRoute);
        mov.setViewName("route");

        return mov;
    }

    @GetMapping("/route/{routeId}")
    private ModelAndView displayRoute(@PathVariable("routeId") Long routeId){
        ModelAndView mov = new ModelAndView();

        Route route = routeService.findById(routeId);

        mov.addObject("route", route);
        mov.setViewName("route");

        return mov;
    }
}
