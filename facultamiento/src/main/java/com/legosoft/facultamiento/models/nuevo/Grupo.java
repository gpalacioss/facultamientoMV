package com.legosoft.facultamiento.models.nuevo;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import java.io.Serializable;

public class Grupo implements Serializable {


    @Id
    @GeneratedValue
    private Long idGrupo;


    private String nombreGrupo;



}
