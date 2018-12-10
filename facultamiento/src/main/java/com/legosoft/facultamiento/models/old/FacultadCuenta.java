package com.legosoft.facultamiento.models.old;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "HAS_CUENTA_FACULTAD")
public class FacultadCuenta {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @StartNode
    private Facultad facultad;

    @EndNode
    private Cuenta cuenta;

    @Property(name = "limiteOperable")
    private String limiteOperable;

    @Property(name = "limiteIndividual")
    private String limiteIndividual;

    @Property(name = "limiteMancomunado")
    private String limiteMancomunado;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLimiteOperable() {
        return limiteOperable;
    }

    public void setLimiteOperable(String limiteOperable) {
        this.limiteOperable = limiteOperable;
    }

    public String getLimiteIndividual() {
        return limiteIndividual;
    }

    public void setLimiteIndividual(String limiteIndividual) {
        this.limiteIndividual = limiteIndividual;
    }

    public String getLimiteMancomunado() {
        return limiteMancomunado;
    }

    public void setLimiteMancomunado(String limiteMancomunado) {
        this.limiteMancomunado = limiteMancomunado;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getNumCuentaByCuenta(){
        Cuenta c = new Cuenta();
        c = getCuenta();
        return c.getNumeroCuenta();
    }

    public FacultadCuenta getFacultadCuenta(){

        FacultadCuenta c = new FacultadCuenta();

        c.setId(getId());
        c.setLimiteIndividual(getLimiteIndividual());
        c.setLimiteMancomunado(getLimiteMancomunado());
        c.setLimiteOperable(getLimiteOperable());

        return c;
    }
}
