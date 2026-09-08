package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.VeterinarioRepository;
import com.example.Veterinaria.Service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Veterinario actualizarVeterinarios(Long id, Veterinario veterinario) {
        Veterinario veterinarioac = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con el id: " + id));


        veterinario.setNombre(veterinarioac.getNombre());
        veterinario.setTargetaProfecional(veterinarioac.getTargetaProfecional());
        veterinario.setEspecialidad(veterinarioac.getEspecialidad());
        veterinario.setCorreo(veterinarioac.getCorreo());

        return veterinarioRepository.save(veterinario);
    }
}
