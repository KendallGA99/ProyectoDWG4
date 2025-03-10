package com.proyecto.service;

import com.proyecto.domain.Usuario;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {
    // Se obtiene un listado de usuarios en un List
    public List<Usuario> getUsuarios(boolean activos);
    
   // Se obtiene un Usuario, a partir del id de un usuario
    public Usuario getUsuario(Usuario usuario);
    
    // Se inserta un nuevo usuario si el id del usuario esta vacío
    // Se actualiza un usuario si el id del usuario NO esta vacío
    public void save(Usuario usuario);
}