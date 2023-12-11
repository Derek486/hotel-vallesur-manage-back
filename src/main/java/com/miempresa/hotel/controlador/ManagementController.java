package com.miempresa.hotel.controlador;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/management")
@CrossOrigin(origins = {"http://localhost:5174"})

public class ManagementController {
    
    @GetMapping
    public String get(){
        return "GET:: management controller";
    }
    @PostMapping
    public String post(){
        return "POST:: management controller";
    }
    @PutMapping
    public String put(){
        return "PUT:: management controller";
    }
    @DeleteMapping
    public String delete(){
        return "DELETE:: management controller";
    }
}
