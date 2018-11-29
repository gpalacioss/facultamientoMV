package com.legosoft.facultamiento.models.nuevo;

import java.io.Serializable;

public class Compania implements Serializable {

    private Long idCompania;

    private String nombreCompania;

    private Boolean isPadre;

    private Compania companiaHijo;


}
