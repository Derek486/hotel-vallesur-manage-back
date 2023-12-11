package com.miempresa.hotel.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.miempresa.hotel.interfaceServicio.IPagoAlquilerServicio;
import com.miempresa.hotel.modelo.PagoAlquiler;
import com.miempresa.hotel.schemas.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pagoalquiler")
@CrossOrigin(origins = {"http://localhost:5174"})

public class PagoAlquilerController {
    
    @Autowired
    private IPagoAlquilerServicio servicio;

    // Metodo para listar los empleados 
    @GetMapping
    public ApiResponse listarPagoAlquilers() {
        List<PagoAlquiler> pagoAlquilers = servicio.listar();
        return new ApiResponse(200, "Registros Obtenidos Correctamente", pagoAlquilers);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> guardarPagoAlquiler (@RequestBody @Valid PagoAlquiler p, BindingResult b){
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        servicio.guardar(p);
        return ResponseEntity.ok().body(new ApiResponse(200, "Registro Guardado Correctamente", p));
    }

    @GetMapping("/mostrarPagoAlquiler")
    public String mostrarPagoAlquiler(@ModelAttribute("pagoalquiler") PagoAlquiler p, Model model){
        model.addAttribute("pagoalquiler", p);
        return "agregarpagoalquiler";
    }

    @DeleteMapping("/{id}")
    public ApiResponse eliminarPagoAlquiler (@PathVariable int id){
        servicio.borrar(id);
        return new ApiResponse(200, "Registro Eliminado Correctamente", null);
    }

}
