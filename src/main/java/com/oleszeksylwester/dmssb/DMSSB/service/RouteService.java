package com.oleszeksylwester.dmssb.DMSSB.service;

import com.oleszeksylwester.dmssb.DMSSB.model.Route;
import com.oleszeksylwester.dmssb.DMSSB.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Transactional
    public void SaveOrUpdate(Route route){
        routeRepository.save(route);
    }

    /*@Transactional(readOnly = true)
    public Route findById(Long id){
        return routeRepository.findOne(id);
    }*/

    @Transactional(readOnly = true)
    public List<Route> findAll(){
        return routeRepository.findAll();
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
