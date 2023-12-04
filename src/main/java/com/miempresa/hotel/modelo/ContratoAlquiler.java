package com.miempresa.hotel.modelo;

import java.sql.Date;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;


@Entity
@Table(name = "contratoalquiler")
public class ContratoAlquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contrato_alquiler;

    @Column(name ="fechainiciocontrato")
    private Date fechainiciocontrato;

    @Column(name = "duracioncontrato")
    private Date duracioncontrato;

    @Column(name="montodeposito")
    private float montodeposito;

    @Column(name="montorentamensual")
    private float montorentamensual;

    @Column(name = "estadocontrato")
    @Enumerated(EnumType.STRING)
    private OpcionesEstadoContrato estadocontrato;

    @OneToOne
    @JoinColumn(name = "id_inquilino")
    private Inquilino inquilinos;
    
    public ContratoAlquiler (){

    }

    // Métodos getter y setter
    public int getId() {
        return id_contrato_alquiler;
    }

    public void setId(int id) {
        this.id_contrato_alquiler = id;
    }

    public Date getFechainiciocontrato() {
        return fechainiciocontrato;
    }

    public void setFechainiciocontrato(Date fechainiciocontrato) {
        this.fechainiciocontrato = fechainiciocontrato;
    }

    public Date getDuracioncontrato() {
        return duracioncontrato;
    }

    public void setDuracioncontrato(Date duracioncontrato) {
        this.duracioncontrato = duracioncontrato;
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
