package com.miempresa.hotel.interfaceServicio;

import java.util.List;
import java.util.Optional;

import com.miempresa.hotel.modelo.User;

public interface IUserServicio {
    public List<User> listar();
    public Optional <User> listarId (int id);
    public int guardar (User usuario);
    public void borrar (int id);
    public List<User> listarAdmins();
}
