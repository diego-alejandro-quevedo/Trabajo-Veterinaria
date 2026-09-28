package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Propietario")
@Data

public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 50)
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El Documento no me puede ser nulo")
    @Size(min = 8, max = 10)
    @Column(name = "documento", nullable = false)
    private String documento;

    @NotBlank(message = "El Telefono no puede ser nulo")
    @Size(min = 10, max = 10)
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    private String correo;

    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL)
    @JsonManagedReference // 1. Evita la recursividad en la salida JSON
    @ToString.Exclude // 2. Evita el bucle infinito en toString() de Lombok
    @EqualsAndHashCode.Exclude // 3. Evita el bucle en equals/hashCode
    private List<Mascota> mascotas = new ArrayList<>();
}