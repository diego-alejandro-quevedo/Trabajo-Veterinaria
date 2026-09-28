package com.example.Veterinaria.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name ="HistoriaClinica")
@Data

public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaApertura;

    @NotBlank(message = "debe dejar los antecedente ")

    @Column(name= "antecedentes",nullable = false)
    private String antecedente;


    @NotBlank(message = "bebe dejar una obsercacion")
    @Column(name= "observaciones",nullable = false)
    private String observacion;

    @OneToOne
    @JsonBackReference // Le indica a Jackson que no vuelva a serializar el Propietario
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name = "mascota_id", unique = true)
    private Mascota mascota;

}
