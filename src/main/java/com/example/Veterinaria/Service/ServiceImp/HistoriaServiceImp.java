package com.example.Veterinaria.Service.ServiceImp;


import com.example.Veterinaria.Entity.HistoriaClinica;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
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
    public HistoriaClinica actualizarHistoria(Long id, HistoriaClinica historia) {

        HistoriaClinica historiaClinica = historiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Historia clínica no encontrada con el id: " + id));

        historiaClinica.setFechaApertura(historiaClinica.getFechaApertura());
        historiaClinica.setAntecedente(historiaClinica.getAntecedente());
        historiaClinica.setObservacion(historiaClinica.getObservacion());

        // Si la historia clínica está asociada a una Mascota o un Veterinario y necesitas permitir su actualización:
        if (historiaClinica.getMascota() != null) {
            historiaClinica.setMascota(historiaClinica.getMascota());
        }

        return historiaRepository.save(historiaClinica);

    }
}
