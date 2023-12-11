package com.miempresa.hotel.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miempresa.hotel.interfaceServicio.IUserServicio;
import com.miempresa.hotel.interfaces.IUser;
import com.miempresa.hotel.modelo.User;

@Service
public class UserServicio implements IUserServicio{

    @Autowired
    private IUser repo;

    @Override
    public List<User> listar() {
        
        return (List<User>)repo.findAll();
    }

    @Override
    public Optional<User> listarId(int id) {
        return repo.findById(id);
    }

    @Override
    public int guardar(User usuario) {
        return repo.save(usuario).equals(null) ? 0 : 1;
    }

    @Override
    public void borrar(int id) {
        repo.deleteById(id);
    }

    @Override
    public List<User> listarAdmins() {
        return repo.filtroList();
    }
    
}
