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

import com.miempresa.hotel.interfaceServicio.IPagoAlquilerServicio;
import com.miempresa.hotel.modelo.PagoAlquiler;

@RestController
@RequestMapping("/api/pagoalquiler")
public class PagoAlquilerController {
    
    @Autowired
    private IPagoAlquilerServicio servicio;

    // Metodo para listar los empleados 
    @GetMapping("/listarPagoAlquiler")
    public List<PagoAlquiler> listarPagoAlquilers() {
        List<PagoAlquiler> pagoAlquilers = servicio.listar();
        return pagoAlquilers;
    }

    @PostMapping("/guardarPagoAlquiler")
    public String guardarPagoAlquiler (@RequestBody PagoAlquiler p){
        servicio.guardar(p);
        return "redirect:/listarPagoAlquiler";
    }

    @PutMapping("/editarPagoAlquiler/{id}")
    public String editarPagoAlquiler(@PathVariable int id, @RequestBody PagoAlquiler inquilinoActualizado,
            RedirectAttributes attributos) {
        Optional<PagoAlquiler> optionalPagoAlquiler = servicio.listarId(id);

        if (optionalPagoAlquiler.isPresent()) {
            PagoAlquiler pagoalquilerExistente = optionalPagoAlquiler.get();

            // Actualizar los campos de la habitación existente con los valores del
            // departamento actualizada
            pagoalquilerExistente.setFechapago(inquilinoActualizado.getFechapago());
            pagoalquilerExistente.setMetodopago(inquilinoActualizado.getMetodopago());
            pagoalquilerExistente.setMontopago(inquilinoActualizado.getMontopago());
            pagoalquilerExistente.setContratoalquiler(inquilinoActualizado.getContratoalquiler());
            
            // ... actualiza otros campos según sea necesario

            // Guardar la habitación actualizada en la base de datos
            servicio.guardar(pagoalquilerExistente);

            attributos.addFlashAttribute("mensaje", "PagoAlquiler actualizado exitosamente");
        } else {
            attributos.addFlashAttribute("mensaje", "Error al actualizar el PagoAlquiler. PagoAlquiler no encontrado");
        }

        return "redirect:/mostrarPagoAlquiler";
    }

    @GetMapping("/mostrarPagoAlquiler")
    public String mostrarPagoAlquiler(@ModelAttribute("pagoalquiler") PagoAlquiler p, Model model){
        model.addAttribute("pagoalquiler", p);
        return "agregarpagoalquiler";
    }

    @GetMapping("/eliminarPagoAlquiler/{id}")
    public String eliminarPagoAlquiler (@PathVariable int id){
        servicio.borrar(id);
        return "redirect:/listarPagoAlquiler";
    }

}
