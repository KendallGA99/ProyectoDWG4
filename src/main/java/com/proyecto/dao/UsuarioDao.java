package com.proyecto.dao;

import com.proyecto.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository <Usuario,Long>{
    Usuario findByUsername(String username); //encontrar por el nombre de usuario
    
    Usuario findByUsernameAndPassword(String username, String Password); //para el login

    Usuario findByUsernameOrCorreo(String username, String correo); //encontrar por el nombre de usuario o correo

    boolean existsByUsernameOrCorreo(String username, String correo); //verificar que existe el nombre de usuario o correo
}