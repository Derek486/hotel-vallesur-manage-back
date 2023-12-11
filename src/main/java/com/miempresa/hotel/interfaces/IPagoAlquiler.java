package com.miempresa.hotel.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.hotel.modelo.PagoAlquiler;

@Repository
public interface IPagoAlquiler extends CrudRepository <PagoAlquiler,Integer> {
    
}
