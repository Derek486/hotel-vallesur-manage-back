package com.miempresa.hotel.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.hotel.interfaceServicio.IDepartamentoServicio;
import com.miempresa.hotel.interfaces.IDepartamento;
import com.miempresa.hotel.modelo.Departamento;

@Service
public class DepartamentoServicio implements IDepartamentoServicio {

    @Autowired
    private IDepartamento repo;

    @Override
    public List<Departamento> listar() {
        return (List<Departamento>)repo.findAll();
    }

    @Override
    public Optional<Departamento> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(Departamento h) {
        Departamento hb = repo.save(h);
        if(!hb.equals(null)){
            return 1;
        }
        return 0;
    }

    @Override
    public void borrar(int id) {
        repo.deleteById(id);
    }
    
}
