package com.miempresa.hotel.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="departamento")
public class Departamento {
    
    @Id
    private int ndepartamento;

    @Column(name = "nhabitaciones")
    private int nhabitaciones;

    @Column(name = "nbaños")
    private int nbaños;

    @Column(name ="areatotal")
    private float areatotal;

    @Column (name = "precio")
    private float precio;

    @Enumerated(EnumType.STRING)
    private OpcionesEstado estado;

    public Departamento (){

    }

    // Métodos getter y setter
    public int getNdepartamento() {
        return ndepartamento;
    }

    public void setNdepartamento(int ndepartamento) {
        this.ndepartamento = ndepartamento;
    }

    public int getNhabitaciones() {
        return nhabitaciones;
    }

    public void setNhabitaciones(int nhabitaciones) {
        this.nhabitaciones = nhabitaciones;
    }

    public int getNbaños() {
        return nbaños;
    }

    public void setNbaños(int nbaños) {
        this.nbaños = nbaños;
    }

    public float getAreatotal() {
        return areatotal;
    }

    public void setAreatotal(float areatotal) {
        this.areatotal = areatotal;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public OpcionesEstado getEstado() {
        return estado;
    }

    public void setEstado(OpcionesEstado estado) {
        this.estado = estado;
    }

}
