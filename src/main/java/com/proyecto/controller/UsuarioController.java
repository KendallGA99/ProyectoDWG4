package com.proyecto.controller;

import com.proyecto.domain.Usuario;
import com.proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario/listado";
    }

    @GetMapping("/nuevo")
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";
    }

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario) {
        // Verificar si es un usuario existente (actualización)
        if (usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0) {
            // Es una actualización
            Usuario usuarioExistente = usuarioService.getUsuario(usuario);

            // Si la contraseña está vacía, mantener la existente
            if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
                usuario.setPassword(usuarioExistente.getPassword());
            } else {
                // Encriptar la nueva contraseña
                var encoder = new BCryptPasswordEncoder();
                usuario.setPassword(encoder.encode(usuario.getPassword()));
            }

            // Mantener el estado activo
            usuario.setActivo(usuarioExistente.isActivo());

            // Guardar sin crear nuevo rol - usar false para NO crear roles
            usuarioService.save(usuario, false);
        } else {
            // Es un usuario nuevo - usar true para crear rol de usuario
            usuarioService.save(usuario, true);
        }
        return "redirect:/";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }
}
