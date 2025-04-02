package com.proyecto.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    //Atributos
    @NotEmpty
    private String username; 
    private int cedula;
    private String nombre;
    private String apellidos;
    @NotEmpty
    private String correo;
    private String telefono;
    private String direccion;
    @NotEmpty
    private String password;
    private boolean activo;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
}