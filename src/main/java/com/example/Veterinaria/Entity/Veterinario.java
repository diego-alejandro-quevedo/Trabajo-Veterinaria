package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Veterinario")
@Data

public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @NotBlank(message = "El nombre no puede ser nulo")
    @Size(min =2 ,max =20 )
    @Column(name= "nombre",nullable = false)
    private String nombre;


    @NotBlank(message = "La targeta no puede ser nulo")
    @Size(min =8 ,max =10 )
    @Column(name= "TargetaProfecional",nullable = false)
    private String targetaProfecional;

    @NotBlank(message = "El Telefono no puede ser nulo")
    @Size(min =10 ,max =10 )
    @Column(name= "Especialidad",nullable = false)
    private String Especialidad;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    @ManyToMany(mappedBy = "veterinarios")
    private List<Mascota> mascotas = new ArrayList<>();

}
