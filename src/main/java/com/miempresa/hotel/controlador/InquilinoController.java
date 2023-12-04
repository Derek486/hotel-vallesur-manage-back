package com.miempresa.hotel.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.hotel.interfaceServicio.IInquilinoServicio;

import com.miempresa.hotel.modelo.Inquilino;

import java.util.List;
import org.springframework.ui.Model;

import java.util.Optional;

@RestController
@RequestMapping("/api/inquilinos")
public class InquilinoController {
    
    @Autowired
    private IInquilinoServicio servicio;

    // Metodo para listar los empleados 
    @GetMapping("/listarInquilinos")
    public List<Inquilino> listarInquilinos() {
        List<Inquilino> inquilinos = servicio.listar();
        return inquilinos;
    }

    @PostMapping("/guardarInquilino")
    public String guardarInquilino (@RequestBody Inquilino p){
        servicio.guardar(p);
        return "redirect:/listarInquilinos";
    }

    @PutMapping("/editarInquilino/{id}")
    public String editarInquilino(@PathVariable int id, @RequestBody Inquilino inquilinoActualizado,
            RedirectAttributes attributos) {
        Optional<Inquilino> optionalInquilino = servicio.listarId(id);

        if (optionalInquilino.isPresent()) {
            Inquilino inquilinoExistente = optionalInquilino.get();

            // Actualizar los campos de la habitación existente con los valores del
            // departamento actualizada
            inquilinoExistente.setNombre(inquilinoActualizado.getNombre());
            inquilinoExistente.setApellidos(inquilinoActualizado.getApellidos());
            inquilinoExistente.setDocIden(inquilinoActualizado.getDocIden());
            inquilinoExistente.setEdad(inquilinoActualizado.getEdad());
            inquilinoExistente.setFechainiciocontrato(inquilinoActualizado.getFechainiciocontrato());
            inquilinoExistente.setFechafincontrato(inquilinoActualizado.getFechafincontrato());
            inquilinoExistente.setTelefono(inquilinoActualizado.getTelefono());
            inquilinoExistente.setCorreoelectronico(inquilinoActualizado.getCorreoelectronico());

            // ... actualiza otros campos según sea necesario

            // Guardar la habitación actualizada en la base de datos
            servicio.guardar(inquilinoExistente);

            attributos.addFlashAttribute("mensaje", "Inquilino actualizado exitosamente");
        } else {
            attributos.addFlashAttribute("mensaje", "Error al actualizar el Inquilino. Inquilino no encontrado");
        }

        return "redirect:/mostrarInquilino";
    }

    @GetMapping("/mostrarInquilino")
    public String mostrarInquilino(@ModelAttribute("inquilino") Inquilino p, Model model){
        model.addAttribute("inquilino", p);
        return "agregarInquilino";
    }

    @GetMapping("/eliminarInquilino/{id}")
    public String eliminarInquilino (@PathVariable int id){
        servicio.borrar(id);
        return "redirect:/listarInquilinos";
    }
}
