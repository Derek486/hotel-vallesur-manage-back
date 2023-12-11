package com.miempresa.hotel.controlador;

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

import com.miempresa.hotel.interfaceServicio.IDepartamentoServicio;
import com.miempresa.hotel.modelo.Departamento;
import com.miempresa.hotel.schemas.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departamentos")
@CrossOrigin(origins = {"http://localhost:5174"})

public class DepartamentosController {

    @Autowired
    private IDepartamentoServicio servicio;

    // Metodo para listar a las habitaciones
    @GetMapping
    public ApiResponse listarDepartamentos() {
        List<Departamento> departamentos = servicio.listar();
        return new ApiResponse(200, "Registrados Obtenidos Correctamente", departamentos);
    }

    @GetMapping("/{id}/inquilino")
    public ApiResponse mostrarInquilinoDepartamento(@PathVariable int id, RedirectAttributes attributos) {
        Optional<Departamento> optionalDep = servicio.listarId(id);
        if (optionalDep.isPresent()) {
            Departamento depExistente = optionalDep.get();
            return new ApiResponse(200, "Registro obtenido Correctamente", depExistente.getInquilino());
        } else {
            return new ApiResponse(404, "Error al mostrar el inquilino. Inquilino no encontrado", null);
        }
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse> guardarDepartamento(@RequestBody @Valid Departamento h, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        servicio.guardar(h);
        return ResponseEntity.ok().body(new ApiResponse(200, "Registro Guardado Correctamente", h));
    }
    // El tema es el request body
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editarDepartamento(@PathVariable int id, @RequestBody @Valid Departamento departamentoActualizado, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        Optional<Departamento> optionalDepartamento = servicio.listarId(id);

        if (optionalDepartamento.isPresent()) {
            Departamento departamentoExistente = optionalDepartamento.get();

            departamentoExistente.setNbaños(departamentoActualizado.getNbaños());
            departamentoExistente.setAreatotal(departamentoActualizado.getAreatotal());
            departamentoExistente.setPrecio(departamentoActualizado.getPrecio());
            departamentoExistente.setNhabitaciones(departamentoActualizado.getNhabitaciones());
            departamentoExistente.setEstado(departamentoActualizado.getEstado());

            servicio.guardar(departamentoExistente);

            return ResponseEntity.ok().body(new ApiResponse(200, "Registro Actualizado Correctamente", departamentoExistente));
        } else {
            return ResponseEntity.status(404).body(new ApiResponse(404, "Departamento no encontrado", null));
        }
    }

    @GetMapping("/{id}")
    public ApiResponse mostrarDepartamento(@PathVariable int id, RedirectAttributes attributos) {
        Optional<Departamento> optionalDepartamento = servicio.listarId(id);
        if (optionalDepartamento.isPresent()) {
            Departamento departamentoExistente = optionalDepartamento.get();
            return new ApiResponse(200, "Registro obtenido Correctamente", departamentoExistente);
        } else {
            return new ApiResponse(404, "Error al mostrar el departamento. Departamento no encontrado", null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse eliminarDepartamento(@PathVariable int id) {
        servicio.borrar(id);
        return new ApiResponse(200, "Registro Eliminado Correctamente", null);
    }
}
