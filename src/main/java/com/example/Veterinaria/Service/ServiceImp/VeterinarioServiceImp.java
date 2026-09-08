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
        // 1. Buscas la entidad existente en la BD (esta SÍ tiene ID)
        Veterinario veterinarioac = veterinarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con el id: " + id));

        // 2. Actualizas la entidad persistida con los datos que vienen en la petición
        veterinarioac.setNombre(veterinario.getNombre());
        veterinarioac.setTargetaProfecional(veterinario.getTargetaProfecional());
        veterinarioac.setEspecialidad(veterinario.getEspecialidad());
        veterinarioac.setCorreo(veterinario.getCorreo());

        // 3. Guardas la entidad recuperada (JPA detectará el ID y ejecutará UPDATE)
        return veterinarioRepository.save(veterinarioac);
    }
}

