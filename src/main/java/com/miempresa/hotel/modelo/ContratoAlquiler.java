package com.miempresa.hotel.modelo;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;


@Entity
@Table(name = "contratoalquiler")
public class ContratoAlquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contrato_alquiler;

    @Column(name ="fechainiciocontrato")
    @NotNull(message = "No puede dejar este campo vacio")
    private Date fechainiciocontrato;

    @Column(name = "fechafincontrato")
    @NotNull(message = "No puede dejar este campo vacio")
    private Date fechafincontrato;

    @Column(name="montodeposito")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 1000,message = "No puede ser menor a S./1000")
    private float montodeposito;

    @Column(name="montorentamensual")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 200,message = "No puede ser menor a S./200")
    private float montorentamensual;

    @Column(name = "estadocontrato")
    @Enumerated(EnumType.STRING)
    private OpcionesEstadoContrato estadocontrato;

    @OneToOne
    @JoinColumn(name = "id_inquilino")
    @JsonBackReference
    private Inquilino inquilinos;

    @OneToMany(mappedBy = "contratoalquiler", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<PagoAlquiler> pagoAlquileres;
    
    public ContratoAlquiler (){

    }

    // Métodos getter y setter
    public int getId() {
        return id_contrato_alquiler;
    }

    public void setId(int id) {
        this.id_contrato_alquiler = id;
    }

    public Set<PagoAlquiler> getPagoAlquileres() {
        return pagoAlquileres;
    }

    public void setPagoAlquileres(Set<PagoAlquiler> pagoAlquileres) {
        this.pagoAlquileres = pagoAlquileres;
    }

    public Date getFechainiciocontrato() {
        return fechainiciocontrato;
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato = fechainiciocontrato;
    }

    public Date getFechafincontrato() {
        return fechafincontrato;
    }

    public void setFechafincontrato(Date fechafincontrato) {
        this.fechafincontrato = fechafincontrato;
    }

    public float getMontodeposito() {
        return montodeposito;
    }

    public void setMontodeposito(float montodeposito) {
        this.montodeposito = montodeposito;
    }

    public float getMontorentamensual() {
        return montorentamensual;
    }

    public void setMontorentamensual(float montorentamensual) {
        this.montorentamensual = montorentamensual;
    }

    public OpcionesEstadoContrato getEstadocontrato() {
        return estadocontrato;
    }

    public void setEstadocontrato(OpcionesEstadoContrato estadocontrato) {
        this.estadocontrato = estadocontrato;
    }

    public Inquilino getInquilinos() {
        return inquilinos;
    }

    public void setInquilinos(Inquilino inquilinos) {
        this.inquilinos = inquilinos;
    }

}
