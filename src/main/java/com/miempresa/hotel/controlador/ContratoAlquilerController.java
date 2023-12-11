package com.miempresa.hotel.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.hotel.interfaceServicio.IContratoAlquilerServicio;
import com.miempresa.hotel.modelo.ContratoAlquiler;
import com.miempresa.hotel.schemas.ApiResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/contratoalquileres")
@CrossOrigin(origins = {"http://localhost:5174"})

public class ContratoAlquilerController {

    @Autowired
    private IContratoAlquilerServicio servicio;

    // Metodo para listar a las habitaciones
    @GetMapping
    public ApiResponse listarContratoAlquileres() {
        List<ContratoAlquiler> contratoalquileres = servicio.listar();
        return new ApiResponse(200, "Registro Obtenido correctamente", contratoalquileres);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> guardarContratoAlquiler(@RequestBody @Valid ContratoAlquiler h, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        servicio.guardar(h);
        return ResponseEntity.ok().body(new ApiResponse(200, "Registro Guardado Correctamente", h));
    }

    // El tema es el request body
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse>  editarContratoAlquiler(@PathVariable int id,
            @RequestBody @Valid ContratoAlquiler contratoalquilerActualizado, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        Optional<ContratoAlquiler> optionalContratoAlquiler = servicio.listarId(id);

        if (optionalContratoAlquiler.isPresent()) {
            ContratoAlquiler contratoalquilerExistente = optionalContratoAlquiler.get();

            contratoalquilerExistente.setFechainiciocontrato(contratoalquilerActualizado.getFechainiciocontrato());
            contratoalquilerExistente.setFechafincontrato(contratoalquilerActualizado.getFechafincontrato());
            contratoalquilerExistente.setMontodeposito(contratoalquilerActualizado.getMontodeposito());
            contratoalquilerExistente.setMontorentamensual(contratoalquilerActualizado.getMontorentamensual());
            contratoalquilerExistente.setEstadocontrato(contratoalquilerActualizado.getEstadocontrato());
            
            servicio.guardar(contratoalquilerExistente);

            return ResponseEntity.ok().body(new ApiResponse(200, "Registrado Editado Correctamente", contratoalquilerExistente));
        }

        return ResponseEntity.status(409).body(new ApiResponse(409, "Ocurrio un Error", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse>  mostrarContrato(@PathVariable int id, RedirectAttributes attributos) {
        Optional<ContratoAlquiler> optionalContrato = servicio.listarId(id);
        if (optionalContrato.isPresent()) {
            ContratoAlquiler contratoExistente = optionalContrato.get();
            return ResponseEntity.ok().body(new ApiResponse(200, "Registro obtenido Correctamente", contratoExistente));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(404, "Error al mostrar el contrato. contrato no encontrado", null));
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse liminarContratoAlquiler(@PathVariable int id) {
        servicio.borrar(id);
        return new ApiResponse(200, "Registrado Eliminado Correctamente", null);
    }

}
