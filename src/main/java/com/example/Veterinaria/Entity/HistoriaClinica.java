package com.example.Veterinaria.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

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
    @Size(min =4 ,max =80 )
    @Column(name= "antecedentes",nullable = false)
    private String antecedente;


    @NotBlank(message = "bebe dejar una obsercacion")
    @Size(min =2 ,max =20 )
    @Column(name= "observaciones",nullable = false)
    private String observacion;

    @OneToOne
    @JoinColumn(name = "mascota_id", unique = true)
    private Mascota mascota;

}
