package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class MascotaServiceImp implements MascotaService {


    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

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
    public Mascota actualizarMascota(Long id, Mascota mascota) {

        Mascota mascotaExistente = mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con el id: " + id));

        mascotaExistente.setNombre(mascota.getNombre());
        mascotaExistente.setEspecie(mascota.getEspecie());
        mascotaExistente.setRaza(mascota.getRaza());
        mascotaExistente.setEdad(mascota.getEdad());
        mascotaExistente.setPeso(mascota.getPeso());

        if (mascota.getPropietario() != null) {
            mascotaExistente.setPropietario(mascota.getPropietario());
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
