package com.legosoft.facultamiento.controller;

import com.legosoft.facultamiento.models.nuevo.Rol;
import com.legosoft.facultamiento.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class RolController {

    @Autowired
    private RolService rolService;


    @RequestMapping(value = "/getAllRolles", method = RequestMethod.GET)
    public List<Rol> getAllRolles(){

        return rolService.getAllRoles();
    }

}
