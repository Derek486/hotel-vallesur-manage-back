package com.miempresa.hotel.modelo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="departamento")
public class Departamento {
    
    @Id
    @NotNull(message = "Debe especificar el Número del departamento")
    @Min(value = 1, message = "Mínimo valor: 1")
    private Integer ndepartamento;

    @Column(name = "nhabitaciones")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 1, message = "Debe haber un mínimo de 1 habitación")
    private Integer nhabitaciones;

    @Column(name = "nbaños")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 1, message = "Debe haber un mínimo de 1 bño")
    private int nbaños;

    @Column(name ="areatotal")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 5, message = "Debe haber un mínimo de 5m2")
    private float areatotal;

    @Column (name = "precio")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 1000, message = "Debe haber un mínimo de S./1000")
    private float precio;

    @NotNull(message = "No puede dejar este campo vacio")
    @Enumerated(EnumType.STRING)
    private OpcionesEstado estado;

    @OneToOne(mappedBy = "departamento", cascade = CascadeType.REMOVE) // Nombre del campo en la entidad Inquilino que hace referencia al Departamento
    @JsonIgnore
    private Inquilino inquilino;

    public Departamento (){

    }

    // Métodos getter y setter
    public int getNdepartamento() {
        return ndepartamento;
    }

    public void setNdepartamento(int ndepartamento) {
        this.ndepartamento = ndepartamento;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
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
