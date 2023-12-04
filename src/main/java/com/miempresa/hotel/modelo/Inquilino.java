package com.miempresa.hotel.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "inquilino")
public class Inquilino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_inquilino;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "apellidos")
    private String apellidos;

    // Debe aparecer para seleccionar si es que es pasaporte o dni
    @Column(name = "documento_de_identificacion")
    @Enumerated(EnumType.STRING)
    private OpcionesDocIden docIden;

    @Column(name = "edad")
    private int edad;

    @Column(name ="fechainiciocontrato")
    private Date fechainiciocontrato;

    @Column(name ="fechafincontrato")
    private Date fechafincontrato;

    @Column(name = "telefono")
    private String telefono;

     @Column(name = "correoelectronico")
    private String correoelectronico;

    // Se me ocurre que debe existir una pestaña emergente
    // Faltan los metodos getter y setter 
    @OneToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;  

    // Constructor vacio 
    public Inquilino (){

    }

    public int getId() {
        return id_inquilino;
    }

    public void setId(int id) {
        this.id_inquilino = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public OpcionesDocIden getDocIden() {
        return docIden;
    }

    public void setDocIden(OpcionesDocIden docIden) {
        this.docIden = docIden;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
