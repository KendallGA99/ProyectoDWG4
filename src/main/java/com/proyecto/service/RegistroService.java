package com.proyecto.service;

import com.proyecto.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;

public interface RegistroService {

    public Model activar(Model model, String usuario, String clave); //activar cuenta

    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException; //crear usuario
    
    public void activar(Usuario usuario); //activar
    
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException; //olvidar contraseña
}