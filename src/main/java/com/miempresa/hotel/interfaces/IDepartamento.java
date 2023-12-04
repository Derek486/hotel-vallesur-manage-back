package com.miempresa.hotel.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.hotel.modelo.Departamento;

@Repository
public interface IDepartamento extends CrudRepository <Departamento,Integer> { 
    
}
