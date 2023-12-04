package com.miempresa.hotel.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.hotel.modelo.Inquilino;

public interface IInquilinoServicio {
    public List<Inquilino> listar ();
    public Optional <Inquilino> listarId (int id);
    public int guardar (Inquilino p);
    public void borrar (int id);

}
