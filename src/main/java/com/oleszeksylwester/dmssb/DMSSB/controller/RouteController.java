package com.oleszeksylwester.dmssb.DMSSB.controller;

import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.service.RouteService;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class RouteController {

    private static final Logger LOGGER = Logger.getLogger(RouteController.class.getName());

    @Autowired
    RouteService routeService;

    @Autowired
    UserService userService;

    @GetMapping("/routeslist")
    private String displayAllRoutes(){

        return "routeslist";
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
}
