package com.miempresa.hotel.modelo;

 
import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pagoalquiler")
public class PagoAlquiler {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago_alquiler;

    @Column(name = "fechapago", nullable = false, updatable = false)
    @CreationTimestamp
    private Date fechapago;

    @Column(name = "metodopago")
    @NotNull(message = "No puede dejar este campo vacio")
    @Enumerated(EnumType.STRING)
    private OpcionesPago metodopago;
    
    @Column(name = "montopago")
    @NotNull(message = "No puede dejar este campo vacio") 
    @Min(value = 1, message = "Ingrese un pago válido")
    private float montopago;

    @ManyToOne
    @JoinColumn(name = "id_contrato_alquiler")
    @NotNull
    private ContratoAlquiler contratoalquiler;

    public PagoAlquiler (){

    }

     // Métodos getter y setter
     public int getId() {
        return id_pago_alquiler;
    }

    public void setId(int id) {
        this.id_pago_alquiler = id;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public OpcionesPago getMetodopago() {
        return metodopago;
    }

    public void setMetodopago(OpcionesPago metodopago) {
        this.metodopago = metodopago;
    }

    public float getMontopago() {
        return montopago;
    }

    public void setMontopago(float montopago) {
        this.montopago = montopago;
    }

    public ContratoAlquiler getContratoalquiler() {
        return contratoalquiler;
    }

    public void setContratoalquiler(ContratoAlquiler contratoalquiler) {
        this.contratoalquiler = contratoalquiler;
    }
}
    

