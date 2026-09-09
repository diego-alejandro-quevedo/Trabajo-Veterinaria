package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class MascotaServiceImp implements MascotaService {


    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final PropietarioRepository propietarioRepository;

    @Override
    public List<Mascota> listarMascotas() {
        return mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> buscarMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public void eliminarMascota(Long id) {
        if(!mascotaRepository.existsById(id)){
            throw new RuntimeException( "No existe el usuario con el id " + id);
        }

        mascotaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Mascota actualizarMascota(Long id, Mascota mascota) {

        Mascota mascotaExistente = mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con el id: " + id));

        if (mascota.getNombre() != null && !mascota.getNombre().isBlank()) {
            mascotaExistente.setNombre(mascota.getNombre());
        }
        if (mascota.getEspecie() != null && !mascota.getEspecie().isBlank()) {
            mascotaExistente.setEspecie(mascota.getEspecie());
        }
        if (mascota.getRaza() != null && !mascota.getRaza().isBlank()) {
            mascotaExistente.setRaza(mascota.getRaza());
        }
        if (mascota.getEdad() != null) {
            mascotaExistente.setEdad(mascota.getEdad());
        }
        if (mascota.getPeso() > 0.0) {
            mascotaExistente.setPeso(mascota.getPeso());
        }
        if (mascota.getPropietario() != null && mascota.getPropietario().getId() != null) {
            Propietario propietario = propietarioRepository.findById(mascota.getPropietario().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con el id: " + mascota.getPropietario().getId()));
            mascotaExistente.setPropietario(propietario);
        }
        if (mascota.getVeterinarios() != null && !mascota.getVeterinarios().isEmpty()) {
            mascotaExistente.setVeterinarios(mascota.getVeterinarios());
        }

        return mascotaRepository.save(mascotaExistente);
    }

    @Override
    public List<Mascota> listarPorPropietario(Long propietarioId) {
         return mascotaRepository.findByPropietarioId(propietarioId);
    }

    @Override
    public Mascota asignarMascotaxVeterinario(Long mascotaId, Long veterinarioId) {
        Mascota mascota = mascotaRepository.findById(mascotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con el id: " + mascotaId));

        Veterinario veterinario = veterinarioRepository.findById(veterinarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con el id: " + veterinarioId));

        if (veterinario.getMascotas() == null) {
            veterinario.setMascotas(new ArrayList<>());
        }
        if (!veterinario.getMascotas().contains(mascota)) {
            veterinario.getMascotas().add(mascota);
        }

        if (mascota.getVeterinarios() == null) {
            mascota.setVeterinarios(new ArrayList<>());
        }
        if (!mascota.getVeterinarios().contains(veterinario)) {
            mascota.getVeterinarios().add(veterinario);
        }

        if (!mascota.getVeterinarios().contains(veterinario)) {
            mascota.getVeterinarios().add(veterinario);
        }
        mascotaRepository.save(mascota);

        return mascotaRepository.save(mascota);
    }


}
