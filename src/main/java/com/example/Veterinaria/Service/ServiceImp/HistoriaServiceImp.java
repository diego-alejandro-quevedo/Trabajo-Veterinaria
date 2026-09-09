package com.example.Veterinaria.Service.ServiceImp;


import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.HistoriaRepository;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Service.HistoriaService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HistoriaServiceImp implements HistoriaService {

    private final HistoriaRepository historiaRepository;
    private final MascotaRepository mascotaRepository;

    @Override
    public List<HistoriaClinica> listarHistorias() {
        return historiaRepository.findAll();
    }

    @Override
    public Optional<HistoriaClinica> buscarHistoriaPorId(Long id) {
        return historiaRepository.findById(id);
    }

    @Override
    public HistoriaClinica crearHistoria( HistoriaClinica historia) {
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
    @Transactional
    public HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historia) {


        HistoriaClinica historiaExistente = historiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Historia clínica no encontrada con el id: " + id));


        if (historia.getFechaApertura() != null) {
            historiaExistente.setFechaApertura(historia.getFechaApertura());
        }

        if (historia.getAntecedente() != null && !historia.getAntecedente().isBlank()) {
            historiaExistente.setAntecedente(historia.getAntecedente());
        }

        if (historia.getObservacion() != null && !historia.getObservacion().isBlank()) {
            historiaExistente.setObservacion(historia.getObservacion());
        }

        if (historia.getMascota() != null && historia.getMascota().getId() != null) {
            Mascota mascota = mascotaRepository.findById(historia.getMascota().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con el id: " + historia.getMascota().getId()));
            historiaExistente.setMascota(mascota);
        }

        return historiaRepository.save(historiaExistente);
    }
}
