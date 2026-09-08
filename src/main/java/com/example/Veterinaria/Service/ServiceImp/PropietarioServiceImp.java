package com.example.Veterinaria.Service.ServiceImp;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Exception.ResourceNotFoundException;
import com.example.Veterinaria.Repository.PropietarioRepository;
import com.example.Veterinaria.Service.PropietarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class PropietarioServiceImp implements PropietarioService {

    private final PropietarioRepository propietarioRepository;


    @Override
    public List<Propietario> listarPropietarios() {
        return propietarioRepository.findAll();
    }

    @Override
    public Optional<Propietario> buscarPropietarioPorId(Long id) {
        return propietarioRepository.findById(id);
    }

    @Override
    public Propietario crearPropietario(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }

    @Override
    public void eliminarPropietario(Long id) {
        if(!propietarioRepository.existsById(id)){
            throw new RuntimeException( "No existe el usuario con el id " + id);

        }
        propietarioRepository.deleteById(id);
    }

    @Override
    public Propietario actualizarPropietario(Long id, Propietario propietario) {
        // 1. Buscar la entidad existente en la BD (esta SÍ tiene el ID)
        Propietario propietarioac = propietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con el id: " + id));

        // 2. Copiar los datos NUEVOS (propietario) hacia la entidad de la BD (propietarioac)
        propietarioac.setNombre(propietario.getNombre());
        propietarioac.setDocumento(propietario.getDocumento());
        propietarioac.setTelefono(propietario.getTelefono());
        propietarioac.setCorreo(propietario.getCorreo());

        // 3. Guardar la entidad de la BD para que JPA ejecute un UPDATE
        return propietarioRepository.save(propietarioac);
    }
}

