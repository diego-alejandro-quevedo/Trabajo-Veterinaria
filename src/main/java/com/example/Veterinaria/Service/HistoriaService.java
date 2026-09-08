package com.example.Veterinaria.Service;

import com.example.Veterinaria.Entity.HistoriaClinica;

import java.util.List;
import java.util.Optional;

public interface HistoriaService {

    // Listar Historias

    List<HistoriaClinica> listarHistorias();

// litar Historia por Id

    Optional<HistoriaClinica> buscarHistoriaPorId(Long id);

// Crear una Veterinarios

    HistoriaClinica crearHistoria(HistoriaClinica historia);

// Eliminar Historia

    void eliminarHistoria(Long id);

// Actualizar datos Historia

    HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historia);

}

