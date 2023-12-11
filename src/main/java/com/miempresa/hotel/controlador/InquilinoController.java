package com.miempresa.hotel.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.miempresa.hotel.interfaceServicio.IInquilinoServicio;
import com.miempresa.hotel.modelo.Departamento;
import com.miempresa.hotel.modelo.Inquilino;
import com.miempresa.hotel.modelo.OpcionesEstado;
import com.miempresa.hotel.schemas.ApiResponse;

import jakarta.validation.Valid;

import java.util.List;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/inquilinos")
@CrossOrigin(origins = {"http://localhost:5174"})

public class InquilinoController {
    
    @Autowired
    private IInquilinoServicio servicio;

    @Autowired
    private IDepartamentoServicio servicioDepartamento;

    // Metodo para listar los empleados 
    @GetMapping
    public ApiResponse listarInquilinos() {
        List<Inquilino> inquilinos = servicio.listar();
        return new ApiResponse(200, "Registros Obtenidos Correctamente", inquilinos);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> guardarInquilino (@RequestBody @Valid Inquilino p, BindingResult b){
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        Departamento departamentoExistente = servicioDepartamento.listarId(p.getDepartamento().getNdepartamento()).orElse(null);
        if (departamentoExistente != null) {
            p.setDepartamento(departamentoExistente);
            departamentoExistente.setEstado(OpcionesEstado.Ocupado);
            servicioDepartamento.guardar(departamentoExistente);
        }
        servicio.guardar(p);
        return ResponseEntity.ok().body(new ApiResponse(200, "Registro Guardado Correctamente", p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editarInquilino(@PathVariable int id, @RequestBody @Valid Inquilino inquilinoActualizado, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en tus datos", b.getAllErrors()));
        }
        Optional<Inquilino> optionalInquilino = servicio.listarId(id);

        if (optionalInquilino.isPresent()) {
            Inquilino inquilinoExistente = optionalInquilino.get();

            inquilinoExistente.setNombre(inquilinoActualizado.getNombre());
            inquilinoExistente.setApellidos(inquilinoActualizado.getApellidos());
            inquilinoExistente.setDocIden(inquilinoActualizado.getDocIden());
            inquilinoExistente.setEdad(inquilinoActualizado.getEdad());
            inquilinoExistente.setTelefono(inquilinoActualizado.getTelefono());
            inquilinoExistente.setCorreoelectronico(inquilinoActualizado.getCorreoelectronico());

            servicio.guardar(inquilinoExistente);

            return ResponseEntity.ok().body(new ApiResponse(200, "Registro Actualizado Correctamente", inquilinoExistente));
        }
        return ResponseEntity.status(404).body(new ApiResponse(404, "Inquilino no encontrado", null));
    }

    @GetMapping("/{id}")
    public ApiResponse mostrarInquilino(@PathVariable int id, RedirectAttributes attributos) {
        Optional<Inquilino> optionalInquilino = servicio.listarId(id);
        if (optionalInquilino.isPresent()) {
            Inquilino inquilinoExistente = optionalInquilino.get();
            return new ApiResponse(200, "Registro obtenido Correctamente", inquilinoExistente);
        } else {
            return new ApiResponse(404, "Error al mostrar el inquilino. Inquilino no encontrado", null);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse eliminarInquilino (@PathVariable int id){
        Optional<Inquilino> optionalInquilino = servicio.listarId(id);
        
        if (optionalInquilino.isPresent()) {
            Inquilino inquilinoExistente = optionalInquilino.get();
            Departamento dep = inquilinoExistente.getDepartamento();
            dep.setEstado(OpcionesEstado.Disponible);
            servicioDepartamento.guardar(dep);
        }
        servicio.borrar(id);
        return new ApiResponse(200, "Registro Eliminado Correctamente", null);
    }
}
