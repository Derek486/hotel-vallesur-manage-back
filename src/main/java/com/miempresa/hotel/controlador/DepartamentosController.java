package com.miempresa.hotel.controlador;

import org.springframework.ui.Model;
import java.util.Optional;

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

import com.miempresa.hotel.interfaceServicio.IDepartamentoServicio;
import com.miempresa.hotel.modelo.Departamento;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentosController {

    @Autowired
    private IDepartamentoServicio servicio;

    // Metodo para listar a las habitaciones
    @GetMapping("/listarDepartamentos")
    public List<Departamento> listarDepartamentos() {
        List<Departamento> departamentos = servicio.listar();
        return departamentos;
    }

    @PostMapping("/guardarDepartamento")
    public String guardarDepartamento(@RequestBody Departamento h) {
        servicio.guardar(h);
        return "redirect:/listarDepartamentos";
    }
    // El tema es el request body
    @PutMapping("/editarDepartamento/{id}")
    public String editarDepartamento(@PathVariable int id, @RequestBody Departamento departamentoActualizado,
            RedirectAttributes attributos) {
        Optional<Departamento> optionalDepartamento = servicio.listarId(id);

        if (optionalDepartamento.isPresent()) {
            Departamento departamentoExistente = optionalDepartamento.get();

            // Actualizar los campos de la habitación existente con los valores del
            // departamento actualizada
            departamentoExistente.setNbaños(departamentoActualizado.getNbaños());
            departamentoExistente.setAreatotal(departamentoActualizado.getAreatotal());
            departamentoExistente.setPrecio(departamentoActualizado.getPrecio());
            departamentoExistente.setNhabitaciones(departamentoActualizado.getNhabitaciones());
            departamentoExistente.setEstado(departamentoActualizado.getEstado());
            // ... actualiza otros campos según sea necesario

            // Guardar la habitación actualizada en la base de datos
            servicio.guardar(departamentoExistente);

            attributos.addFlashAttribute("mensaje", "Departamento actualizado exitosamente");
        } else {
            attributos.addFlashAttribute("mensaje", "Error al actualizar el departamento. Departamento no encontrado");
        }

        return "redirect:/mostrarDepartamento";
    }

    @GetMapping("/mostrarDepartamento")
    public String mostrarDepartamento(@ModelAttribute("departamento") Departamento h, Model model) {
        model.addAttribute("departamento", h);
        return "agregarDepartamento";
    }

    @GetMapping("/eliminarDepartamento/{id}")
    public String eliminarDepartamento(@PathVariable int id) {
        servicio.borrar(id);
        return "redirect:/listarDepartamento";
    }
}
