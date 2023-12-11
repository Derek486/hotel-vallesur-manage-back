package com.miempresa.hotel.modelo;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "inquilino")
public class Inquilino {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_inquilino;

    @Column(name = "nombre")
    @NotBlank(message = "No puede dejar este campo vacio")
    private String nombre;
    
    @Column(name = "apellidos")
    @NotBlank(message = "No puede dejar este campo vacio")
    private String apellidos;

    // Debe aparecer para seleccionar si es que es pasaporte o dni
    @Column(name = "documento_de_identificacion")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "No puede dejar este campo vacio")
    private OpcionesDocIden docIden;

    @Column(name = "edad")
    @NotNull(message = "No puede dejar este campo vacio")
    @Min(value = 18, message = "Debe ser un mayor de edad")
    private Integer edad;

    @Column(name = "telefono")
    @NotBlank(message = "No puede dejar este campo vacio")
    @Length(min = 9, max = 9, message = "Debe ser de 9 dígitos")
    private String telefono;

    @Column(name = "correoelectronico")
    @NotBlank(message = "No puede dejar este campo vacio")
    @Email(message = "Formato invalido")
    private String correoelectronico;

    // Se me ocurre que debe existir una pestaña emergente
    // Faltan los metodos getter y setter 
    @OneToOne
    @JoinColumn(name = "departamento_id")
    @NotNull(message = "Debe elegir un departamento")
    private Departamento departamento;  

    @OneToOne(mappedBy = "inquilinos", cascade = CascadeType.REMOVE) // Nombre del campo en la entidad Inquilino que hace referencia al Departamento
    @JsonManagedReference
    private ContratoAlquiler contratoAlquiler;

    // Constructor vacio 
    public Inquilino (){

    }

    public int getId() {
        return id_inquilino;
    }

    public void setId(int id) {
        this.id_inquilino = id;
    }

    public ContratoAlquiler getContratoAlquiler() {
        return contratoAlquiler;
    }

    public void setContratoAlquiler(ContratoAlquiler contratoAlquiler) {
        this.contratoAlquiler = contratoAlquiler;
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
