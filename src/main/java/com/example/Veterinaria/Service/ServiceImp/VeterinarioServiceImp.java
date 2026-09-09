package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class VeterinarioServiceImp implements VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    @Override
    public List<Veterinario> listarVeterinarios() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Optional<Veterinario> buscarPropietarioPorId(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public Veterinario crearVeterinarios(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public void eliminarVeterinarios(Long id) {
        veterinarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Veterinario actualizarVeterinarios(Long id, Veterinario veterinario) {


        Veterinario veterinarioac = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Veterinario no encontrado con el id: " + id));

        if (veterinario.getNombre() != null && !veterinario.getNombre().isBlank()) {
            veterinarioac.setNombre(veterinario.getNombre());
        }
        if (veterinario.getTargetaProfecional() != null && !veterinario.getTargetaProfecional().isBlank()) {
            veterinarioac.setTargetaProfecional(veterinario.getTargetaProfecional());
        }
        if (veterinario.getEspecialidad() != null && !veterinario.getEspecialidad().isBlank()) {
            veterinarioac.setEspecialidad(veterinario.getEspecialidad());
        }
        if (veterinario.getCorreo() != null && !veterinario.getCorreo().isBlank()) {
            veterinarioac.setCorreo(veterinario.getCorreo());
        }

        if (veterinario.getMascotas() != null && !veterinario.getMascotas().isEmpty()) {
            veterinarioac.setMascotas(veterinario.getMascotas());
        }

        return veterinarioRepository.save(veterinarioac);
    }
}

