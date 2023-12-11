package com.miempresa.hotel.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.hotel.interfaceServicio.IInquilinoServicio;
import com.miempresa.hotel.interfaces.IInquilino;
import com.miempresa.hotel.modelo.Inquilino;

@Service
public class InquilinoServicio implements IInquilinoServicio  {

    @Autowired
    private IInquilino repo;
    
    @Override
    public List<Inquilino> listar() {
        return (List<Inquilino>)repo.findAll();
    }

    @Override
    public Optional<Inquilino> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(Inquilino p) {
        Inquilino em=repo.save(p);
        if(!em.equals(null)){
            return 1;
        }
        return 0;
    }

    @Override
    public void borrar(int id) {
        repo.deleteById(id);
    }
}
