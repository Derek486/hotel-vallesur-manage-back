package com.miempresa.hotel.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.hotel.interfaceServicio.IContratoAlquilerServicio;
import com.miempresa.hotel.modelo.ContratoAlquiler;


@RestController
@RequestMapping("/api/contratoalquileres")
public class ContratoAlquilerController {

    @Autowired
    private IContratoAlquilerServicio servicio;

    // Metodo para listar a las habitaciones
    @GetMapping("/listarcontratoalquileres")
    public List<ContratoAlquiler> listarContratoAlquileres() {
        List<ContratoAlquiler> contratoalquileres = servicio.listar();
        return contratoalquileres;
    }

    @PostMapping("/guardarContratoAlquiler")
    public String guardarContratoAlquiler(@RequestBody ContratoAlquiler h) {
        servicio.guardar(h);
        return "redirect:/listarcontratoalquileres";
    }

    // El tema es el request body
    @PutMapping("/editarContratoAlquiler/{id}")
    public String editarContratoAlquiler(@PathVariable int id,
            @RequestBody ContratoAlquiler contratoalquilerActualizado,
            RedirectAttributes attributos) {
        Optional<ContratoAlquiler> optionalContratoAlquiler = servicio.listarId(id);

        if (optionalContratoAlquiler.isPresent()) {
            ContratoAlquiler contratoalquilerExistente = optionalContratoAlquiler.get();

            // Actualizar los campos de la habitación existente con los valores del
            // departamento actualizada
            contratoalquilerExistente.setFechainiciocontrato(contratoalquilerActualizado.getFechainiciocontrato());
            contratoalquilerExistente.setDuracioncontrato(contratoalquilerActualizado.getDuracioncontrato());
            contratoalquilerExistente.setMontodeposito(contratoalquilerActualizado.getMontodeposito());
            contratoalquilerExistente.setMontorentamensual(contratoalquilerActualizado.getMontorentamensual());
            contratoalquilerExistente.setEstadocontrato(contratoalquilerActualizado.getEstadocontrato());
            
            // ... actualiza otros campos según sea necesario

            // Guardar la habitación actualizada en la base de datos
            servicio.guardar(contratoalquilerExistente);

            attributos.addFlashAttribute("mensaje", "contratoalquilerExistente actualizado exitosamente");
        } else {
            attributos.addFlashAttribute("mensaje", "Error al actualizar el contratoalquilerExistente. contratoalquilerExistente no encontrado");
        }

        return "redirect:/mostrarContratoAlquiler";
    }

    @GetMapping("/mostrarContratoAlquiler")
    public String mostrarContratoAlquiler(@ModelAttribute("contratoAlquiler") ContratoAlquiler h, Model model) {
        model.addAttribute("contratoalquiler", h);
        return "agregarContratoAlquiler";
    }

    @GetMapping("/eliminarContratoAlquiler/{id}")
    public String liminarContratoAlquiler(@PathVariable int id) {
        servicio.borrar(id);
        return "redirect:/listarcontratoalquileres";
    }

}
