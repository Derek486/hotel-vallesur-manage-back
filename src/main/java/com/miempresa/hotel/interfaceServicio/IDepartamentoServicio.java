package com.miempresa.hotel.interfaceServicio;

import java.util.List;
import java.util.Optional;
import com.miempresa.hotel.modelo.Departamento;

public interface IDepartamentoServicio {
    public List<Departamento> listar();
    public Optional <Departamento> listarId (int id);
    public int guardar (Departamento p);
    public void borrar (int id);
}
