package com.miempresa.hotel.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miempresa.hotel.modelo.ContratoAlquiler;

@Repository
public interface IContratoAlquiler extends CrudRepository <ContratoAlquiler,Integer> {
}
