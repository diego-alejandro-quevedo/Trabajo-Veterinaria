package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    private String raza;

    @NotNull(message = "la edad no puede ser nulo")
    private Integer edad;

    @NotNull(message = "El peso no puede ser nulo")
    private double peso;

    @ManyToOne
    @JsonBackReference // Le indica a Jackson que no vuelva a serializar el Propietario
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
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
    @JsonIgnoreProperties("mascotas")
    private List<Veterinario> veterinarios = new ArrayList<>();





}
