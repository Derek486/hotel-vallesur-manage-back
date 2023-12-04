package com.miempresa.hotel.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.hotel.modelo.ContratoAlquiler;


public interface IContratoAlquilerServicio {
    public List<ContratoAlquiler> listar();
    public Optional <ContratoAlquiler> listarId (int id);
    public int guardar (ContratoAlquiler p);
    public void borrar (int id);
        
}
