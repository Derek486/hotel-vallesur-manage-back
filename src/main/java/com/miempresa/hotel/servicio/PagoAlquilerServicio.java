package com.miempresa.hotel.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.hotel.interfaceServicio.IPagoAlquilerServicio;
import com.miempresa.hotel.interfaces.IPagoAlquiler;
import com.miempresa.hotel.modelo.PagoAlquiler;

@Service
public class PagoAlquilerServicio implements IPagoAlquilerServicio {

    @Autowired
    private IPagoAlquiler repo;

    @Override
    public List<PagoAlquiler> listar() {
        return (List<PagoAlquiler>)repo.findAll();
    }

    @Override
    public Optional<PagoAlquiler> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(PagoAlquiler p) {
        PagoAlquiler hb = repo.save(p);
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
