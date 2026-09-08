package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.MascotaRepository;
import com.example.Veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor

public class MascotaServiceImp implements MascotaService {


    private final MascotaRepository mascotaRepository;

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
        Mascota mascotaac = mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con el id: " + id));

        // Actualiza los campos principales de la entidad Mascota
        mascota.setNombre(mascotaac.getNombre());
        mascota.setEspecie(mascotaac.getEspecie());
        mascota.setRaza(mascotaac.getRaza());
        mascota.setEdad(mascotaac.getEdad());
        mascota.setPeso(mascotaac.getPeso());

        if (mascotaac.getPropietario() != null) {
            mascota.setPropietario(mascotaac.getPropietario());
        }

        return mascotaRepository.save(mascota);
    }
}
