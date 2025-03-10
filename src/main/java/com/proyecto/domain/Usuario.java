package com.proyecto.domain;

import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //Indicar que esa varibable es la de la BD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long idUsuario;

    //Atributos
    private int cedula;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
    private String password;

    //Constructores
    public Usuario() {
    }
    
    public Usuario(int cedula, String nombre, String apellidos, String correo, String telefono, String direccion, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this. correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.password = password;
    }
}