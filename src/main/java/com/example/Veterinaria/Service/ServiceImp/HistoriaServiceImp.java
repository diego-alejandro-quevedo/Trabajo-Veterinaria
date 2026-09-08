package com.example.Veterinaria.Service.ServiceImp;


import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Repository.HistoriaRepository;
import com.example.Veterinaria.Service.HistoriaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoriaServiceImp implements HistoriaService {

    private final HistoriaRepository historiaRepository;

    @Override
    public List<HistoriaClinica> listarHistorias() {
        return historiaRepository.findAll();
    }

    @Override
    public Optional<HistoriaClinica> buscarHistoriaPorId(Long id) {
        return historiaRepository.findById(id);
    }

    @Override
    public HistoriaClinica crearHistoria(HistoriaClinica historia) {
        return historiaRepository.save(historia);
    }

    @Override
    public void eliminarHistoria(Long id) {
        if(!historiaRepository.existsById(id)){
            throw new RuntimeException( "No existe el usuario con el id " + id);

        }
        historiaRepository.deleteById(id);

    }

    @Override
    public Veterinario actualizarHistoria(Long id, HistoriaClinica historia) {
        return null;
    }
}
