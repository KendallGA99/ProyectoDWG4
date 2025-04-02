package com.proyecto.controller;

import com.proyecto.domain.Usuario;
import com.proyecto.service.RegistroService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    //nuevo usuario
    @GetMapping("/registro")
    public String usuarioRegistro(Model model, Usuario usuario) {
        return "registro/registro";
    }

    //olvidar contraseña
    @GetMapping("/recordar")
    public String recordar(Model model, Usuario usuario) {
        return "/registro/recordar";
    }

    //crear un nuevo usuario
    @PostMapping("/registro")
    public String crearUsuario(Model model, Usuario usuario) throws MessagingException {
        model = registroService.crearUsuario(model, usuario);
        return "/registro/salida";
    }

    //activación en el sistema del usuario
    @GetMapping("/activa/{usuario}/{id}")
    public String activar(Model model, @PathVariable(value = "usuario") String usuario, @PathVariable(value = "id") String id) {
        model = registroService.activar(model, usuario, id);
        if (model.containsAttribute("usuario")) {
            return "/registro/activa";
        } else {
            return "/registro/salida";
        }
    }

    //para activar al usuario
    @PostMapping("/activa")
    public String activar(Usuario usuario) {
        registroService.activar(usuario);
        return "redirect:/";
    }

    //olvidar la contraseña
    @PostMapping("/recordarUsuario")
    public String recordarUsuario(Model model, Usuario usuario) throws MessagingException {
        model = registroService.recordarUsuario(model, usuario);
        return "/registro/salida";
    }
}