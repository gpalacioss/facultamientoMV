package com.legosoft.facultamiento.models.nuevo;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import java.io.Serializable;

public class Compania implements Serializable {

    @Id
    @GeneratedValue
    private Long idCompania;

    private String nombreCompania;

    private Boolean isPadre;

    private Compania companiaHijo;


}
