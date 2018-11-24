package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.old.Facultad;
import com.legosoft.facultamiento.service.FacultadSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class FacultadController {


    @Autowired
    private FacultadSerivice facultadSerivice;

    @RequestMapping(value = "/getFacultades", method = RequestMethod.GET)
    public List<Facultad> findAllFacultades(){

        List<Facultad> result = facultadSerivice.findAllFacultad();

        return  result;

    }
}
