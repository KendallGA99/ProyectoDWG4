package com.proyecto.service.impl;

import com.proyecto.domain.Usuario;
import com.proyecto.service.CorreoService;
import com.proyecto.service.RegistroService;
import com.proyecto.service.UsuarioService;
import jakarta.mail.MessagingException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    private CorreoService correoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private MessageSource messageSource;

    //activar la cuenta
    @Override
    public Model activar(Model model, String username, String clave) { //(lo que viene de la vista, usuario, contraseña)
        Usuario usuario = 
                usuarioService.getUsuarioPorUsernameYPassword(username, clave); //buscarlo a ver si existe
        if (usuario != null) {
            model.addAttribute("usuario", usuario); //agregar al model si existe
        } else {
            model.addAttribute( //sino manda error de que no existe
                    "titulo", 
                    messageSource.getMessage(
                            "registro.activar", 
                            null, Locale.getDefault()));
            model.addAttribute(
                    "mensaje", 
                    messageSource.getMessage(
                            "registro.activar.error", 
                            null, Locale.getDefault()));
        }
        return model;
    }
    
    @Override
    public void activar(Usuario usuario) {
        var codigo = new BCryptPasswordEncoder();
        usuario.setPassword(codigo.encode(usuario.getPassword())); //encriptar la contraseña
        usuario.setActivo(true);
        usuarioService.save(usuario, true); //guardar al usuario
    }
    
    //crear el usuario
    @Override
    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException {
        String mensaje;
        
        if (!usuarioService.existeUsuarioPorUsernameOCorreo(usuario.getUsername(), usuario.getCorreo())) { //no permitir si ya exisate el nombre de usuario o contraseña
            String clave = demeClave(); //contraseña aleatoria
            usuario.setPassword(clave);
            usuario.setActivo(false); //para que el usuario lo active
            usuarioService.save(usuario, true); //salvar
            enviaCorreoActivar(usuario, clave); //enviar correo
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.activacion.ok", 
                            null, 
                            Locale.getDefault()),
                    usuario.getCorreo());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo", 
                            null, 
                            Locale.getDefault()),
                    usuario.getUsername(), usuario.getCorreo());
        }
        model.addAttribute(
                "titulo", 
                messageSource.getMessage(
                        "registro.activar", 
                        null, 
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje", 
                mensaje);
        return model;
    }

    //para el de olvidar contraseña
    @Override
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException {
        String mensaje;
        Usuario usuario2 = usuarioService.getUsuarioPorUsernameOCorreo( usuario.getUsername(), usuario.getCorreo()); //obtener el correo 
        if (usuario2 != null) {
            String clave = demeClave(); //nueva contra
            usuario2.setPassword(clave);
            usuario2.setActivo(false); //activarla
            usuarioService.save(usuario2, false);
            enviaCorreoRecordar(usuario2, clave); //enviar correo
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.recordar.ok", 
                            null, 
                            Locale.getDefault()),
                    usuario.getCorreo());
        } else {
            mensaje = String.format(
                    messageSource.getMessage(
                            "registro.mensaje.usuario.o.correo", 
                            null, 
                            Locale.getDefault()),
                    usuario.getCorreo(), usuario.getCorreo());
        }
        model.addAttribute(
                "titulo", 
                messageSource.getMessage(
                        "registro.activar", 
                        null, 
                        Locale.getDefault()));
        model.addAttribute(
                "mensaje", 
                mensaje);
        return model;
    }

    //para hacer la contraseña aleatoria
    private String demeClave() {
        String tira = "ABCDEFGHIJKLMNOPQRSTUXYZabcdefghijklmnopqrstuvwxyz0123456789_";
        String clave = "";
        for (int i = 0; i < 40; i++) {
            clave += tira.charAt((int) (Math.random() * tira.length()));
        }
        return clave;
    }

    //Ojo cómo le lee una informacion del application.properties
    @Value("${servidor.http}")
    private String servidor;

    //enviar el correo con toda la información del usuario
    private void enviaCorreoActivar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(
                "registro.correo.activar", 
                null, Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getNombre(), 
                usuario.getApellidos(), servidor, 
                usuario.getUsername(), clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.activacion", 
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(usuario.getCorreo(), asunto, mensaje);
    }

    //enviar correo para el de olvidar contraseña
    private void enviaCorreoRecordar(Usuario usuario, String clave) throws MessagingException {
        String mensaje = messageSource.getMessage(""
                + "registro.correo.recordar", 
                null, 
                Locale.getDefault());
        mensaje = String.format(
                mensaje, usuario.getNombre(), 
                usuario.getApellidos(), servidor, 
                usuario.getUsername(), clave);
        String asunto = messageSource.getMessage(
                "registro.mensaje.recordar", 
                null, Locale.getDefault());
        correoService.enviarCorreoHtml(
                usuario.getCorreo(), 
                asunto, mensaje);
    }
}