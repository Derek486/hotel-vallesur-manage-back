package com.miempresa.hotel.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.hotel.interfaceServicio.IContratoAlquilerServicio;
import com.miempresa.hotel.interfaces.IContratoAlquiler;
import com.miempresa.hotel.modelo.ContratoAlquiler;



@Service
public class ContratoAlquilerServicio implements IContratoAlquilerServicio {

    @Autowired
    private IContratoAlquiler repo;

    @Override
    public List<ContratoAlquiler> listar() {
        
        return (List<ContratoAlquiler>)repo.findAll();
    }

    @Override
    public Optional<ContratoAlquiler> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(ContratoAlquiler h) {
        ContratoAlquiler hb = repo.save(h);
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
