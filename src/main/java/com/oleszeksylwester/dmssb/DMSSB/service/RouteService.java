package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.enums.ObjectTypes;
import com.oleszeksylwester.dmssb.DMSSB.enums.RouteStates;
import com.oleszeksylwester.dmssb.DMSSB.factory.NameFactory;
import com.oleszeksylwester.dmssb.DMSSB.model.Document;
import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.model.User;
import com.oleszeksylwester.dmssb.DMSSB.repository.DocumentRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.RouteRepository;
import com.oleszeksylwester.dmssb.DMSSB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private static final Logger LOGGER = Logger.getLogger(RouteService.class.getName());


    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final NameFactory nameFactory;

    @Autowired
    public RouteService(RouteRepository routeRepository, UserRepository userRepository, DocumentRepository documentRepository, NameFactory nameFactory) {
        this.routeRepository = routeRepository;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.nameFactory = nameFactory;
    }

    @Transactional
    public Route SaveOrUpdate(Route route, String checkingDueDateString, String deadlineString, Long id){

        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User owner = userRepository.findByUsername(username);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate checkingDueDate = LocalDate.parse(checkingDueDateString, formatter);
        LocalDate deadline = LocalDate.parse(deadlineString, formatter);

        Document document = documentRepository.getOne(id);

        route.setOwner(owner);
        route.setState(RouteStates.NOT_STARTED.getState());
        route.setCreationDate(LocalDate.now());
        route.setCheckingDueDate(checkingDueDate);
        route.setDeadline(deadline);
        route.setDocumentBeingApprovedId(document);

        routeRepository.save(route);

        Long routeId = route.getId();
        String name = nameFactory.createName(routeId, ObjectTypes.ROUTE.getObjectType());

        route.setName(name);

        routeRepository.save(route);

        return route;
    }

    @Transactional(readOnly = true)
    public Route findById(Long id){
        return routeRepository.findById(id).orElseThrow(()-> new RuntimeException("There is no route with this id"));
    }

    @Transactional(readOnly = true)
    public List<Route> findAll(){
        return routeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Route> findRoutesOfDocument(Long documentId){
        List<Route> allRoutes = routeRepository.findAll();

        return allRoutes.stream()
                .filter(r -> r.getDocumentBeingApprovedId().getId().equals(documentId))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id){
        routeRepository.deleteById(id);
    }

    @Transactional
    public void delete(Route route){
        routeRepository.delete(route);
    }
}
