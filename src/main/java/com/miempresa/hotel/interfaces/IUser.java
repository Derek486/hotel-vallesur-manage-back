package com.miempresa.hotel.interfaces;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.miempresa.hotel.modelo.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface IUser extends JpaRepository<User, Integer>{
    
    Optional<User> findByEmail(String email);
    
    @Query(value = "select * from _user where role = 'MANAGER' ", nativeQuery = true)
    public List<User> filtroList();

}
