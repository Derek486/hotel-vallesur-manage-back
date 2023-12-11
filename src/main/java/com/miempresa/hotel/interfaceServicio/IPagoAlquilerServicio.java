package com.miempresa.hotel.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.hotel.modelo.PagoAlquiler;

public interface IPagoAlquilerServicio {
    public List<PagoAlquiler> listar();
    public Optional <PagoAlquiler> listarId (int id);
    public int guardar (PagoAlquiler p);
    public void borrar (int id);
}
