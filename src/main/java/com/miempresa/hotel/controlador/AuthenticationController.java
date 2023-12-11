package com.miempresa.hotel.controlador;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miempresa.hotel.modelo.User;
import com.miempresa.hotel.interfaceServicio.IUserServicio;
import com.miempresa.hotel.schemas.AuthenticationRequest;
import com.miempresa.hotel.schemas.RegisterRequest;
import com.miempresa.hotel.schemas.UserAgenteRequest;
import com.miempresa.hotel.schemas.UserAgenteUserRequest;
import com.miempresa.hotel.schemas.UserPasswordRequest;
import com.miempresa.hotel.schemas.UserRequest;
import com.miempresa.hotel.schemas.UserResponse;
import com.miempresa.hotel.servicio.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import com.miempresa.hotel.schemas.ApiResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:5174"})
public class AuthenticationController {
    
    private final AuthenticationService service;

    @Autowired
    private IUserServicio servicio;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody @Valid RegisterRequest h, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en sus credenciales", b.getAllErrors()));
        }
        service.register(h);
        return ResponseEntity.ok().body(new ApiResponse(200, "Agente Guardado Correctamente", h));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse> editarInquilino(@RequestBody @Valid UserRequest usuarioActualizado, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en los datos", b.getAllErrors()));
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> optionalUser = servicio.listarId(((User)auth.getPrincipal()).getId());

        if (optionalUser.isPresent()) {
            User usuarioExistente = optionalUser.get();

            // Actualizar los campos de la habitación existente con los valores del

            usuarioExistente.setFirstname(usuarioActualizado.getFirstname());
            usuarioExistente.setLastname(usuarioActualizado.getLastname());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());

            servicio.guardar(usuarioExistente);

            return ResponseEntity.ok().body(new ApiResponse(200, "Registro Actualizado Correctamente", new UserResponse(
                usuarioExistente.getFirstname(),
                usuarioExistente.getLastname(),
                usuarioExistente.getEmail(),
                usuarioExistente.getRole().toString(),
                usuarioExistente.getUsername()
            )));
        }
        return ResponseEntity.status(409).body(new ApiResponse(409, "Ocurrio un error", null));
    }
    
    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid AuthenticationRequest request, BindingResult b){
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en sus credenciales", b.getAllErrors()));
        }
        return ResponseEntity.ok(service.authenticate(request));
    }
    
    @PutMapping("/password")
    public ResponseEntity<ApiResponse> modificarContrasena(
        @RequestBody @Valid UserPasswordRequest request, BindingResult b
    ){
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en los datos", b.getAllErrors()));
        }else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Optional<User> optionalUser = servicio.listarId(((User)auth.getPrincipal()).getId());

            if (optionalUser.isPresent()) {
                User usuarioExistente = optionalUser.get();
                service.guardarContraseña(usuarioExistente, request.getPassword());
            }
            return ResponseEntity.ok().body(new ApiResponse(200, "Contraseña modificada correctamente", null));
        }
    }
    
    @GetMapping("/user")
    public ApiResponse getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ApiResponse(403, "Datos obtenidos correctamente", auth.getPrincipal());
    }
    
    @GetMapping
    public ApiResponse listarAdmin() {
        List<User> usuario = servicio.listarAdmins();
        return new ApiResponse(200, "Usuarios listados correctamente", usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editarAgente(@PathVariable int id, @RequestBody @Valid UserAgenteRequest UserActualizado, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en sus credenciales", b.getAllErrors()));
        }
        Optional<User> optionalUser = servicio.listarId(id);

        if (optionalUser.isPresent()) {
            User userExistente = optionalUser.get();

            userExistente.setFirstname(UserActualizado.getFirstname());
            userExistente.setLastname(UserActualizado.getLastname());

            servicio.guardar(userExistente);

            return ResponseEntity.ok().body(new ApiResponse(200, "Registro Actualizado Correctamente", userExistente));
        } 
        return ResponseEntity.status(403).body(new ApiResponse(403, "Ocurrio un error", null));
    }
    
    @PutMapping("/{id}/user")
    public ResponseEntity<ApiResponse> editarAgenteUsuario(@PathVariable int id, @RequestBody @Valid UserAgenteUserRequest UserActualizado, BindingResult b) {
        if (b.hasErrors()) {
            return ResponseEntity.status(409).body(new ApiResponse(409, "Hubo un error en sus credenciales", b.getAllErrors()));
        }
        Optional<User> optionalUser = servicio.listarId(id);

        if (optionalUser.isPresent()) {
            User userExistente = optionalUser.get();

            userExistente.setEmail(UserActualizado.getEmail());
            service.guardarContraseña(userExistente, UserActualizado.getPassword());

            return ResponseEntity.ok().body(new ApiResponse(200, "Registro Actualizado Correctamente", userExistente));
        } 
        return ResponseEntity.status(403).body(new ApiResponse(403, "Ocurrio un error", null));
    }
    
    @GetMapping("/{id}")
    public ApiResponse mostrarAgente(@PathVariable int id, RedirectAttributes attributos) {
        Optional<User> optionalUser = servicio.listarId(id);
        if (optionalUser.isPresent()) {
            User userExistente = optionalUser.get();
            return new ApiResponse(200, "Agente obtenido Correctamente", userExistente);
        } else {
            return new ApiResponse(404, "Error al mostrar el agente. Agente no encontrado", null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ApiResponse eliminarAgente (@PathVariable int id){
        servicio.borrar(id);
        return new ApiResponse(200, "Registro Eliminado Correctamente", null);
    }


}
