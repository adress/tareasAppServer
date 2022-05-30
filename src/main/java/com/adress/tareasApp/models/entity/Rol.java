package com.adress.tareasApp.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 20)
    private String nombre;

    // relacion inversa de la tabla muchos a muchos
    //@ManyToMany(mappedBy = "roles")
    //private List<Usuario> usuarios;
}
