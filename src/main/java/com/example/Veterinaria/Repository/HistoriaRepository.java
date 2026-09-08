package com.example.Veterinaria.Repository;

import com.example.Veterinaria.Entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoriaRepository extends JpaRepository<HistoriaClinica, Long> {

}
