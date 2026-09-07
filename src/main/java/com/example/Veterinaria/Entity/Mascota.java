package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name ="Mascota")
@Data

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede ser nulo")
    @Size(min =2 ,max =20 )
    @Column(name= "nombre",nullable = false)
    private String nombre;

    @NotBlank(message = "la especie no puede ser nulo")
    @Size(min =2 ,max =20 )
    @Column(name= "especie",nullable = false)
    private String especie;

    @NotBlank(message = "El Raza no puede ser nulo")
    @Size(min =2 ,max =20 )
    @Column(name= "raza",nullable = false)
    private String raaza;

    @NotBlank(message = "la edad no puede ser nulo")
    private Integer edad;

    @NotBlank(message = "El nombre no puede ser nulo")
    private double peso;

    @ManyToOne
    @JoinColumn(name = "propietarioId")
    private Propietario propietario;

    @OneToOne(mappedBy = "mascota", cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;

    @ManyToMany
    @JoinTable(
            name = "mascotaVeterinario",
            joinColumns = @JoinColumn(name = "mascotaId"),
            inverseJoinColumns = @JoinColumn(name = "veterinarioId")
    )
    private List<Veterinario> veterinarios = new ArrayList<>();



}
