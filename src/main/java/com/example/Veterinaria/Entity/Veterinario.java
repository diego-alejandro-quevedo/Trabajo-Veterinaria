package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @Size(min =2 ,max =50 )
    @Column(name= "nombre",nullable = false)
    private String nombre;


    @NotBlank(message = "La targeta no puede ser nulo")
    @Size(min =8 ,max =20 )
    @Column(name= "TargetaProfecional",nullable = false)
    private String targetaProfecional;

    @NotBlank(message = "El Especialidad no puede ser nulo")
    @Size(min = 3, max = 50, message = "La especialidad debe tener entre 3 y 50 caracteres")
    @Column(name= "Especialidad",nullable = false)
    private String Especialidad;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    @ManyToMany(mappedBy = "veterinarios")
    @JsonBackReference // Le indica a Jackson que no vuelva a serializar el Propietario
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Mascota> mascotas = new ArrayList<>();

}
