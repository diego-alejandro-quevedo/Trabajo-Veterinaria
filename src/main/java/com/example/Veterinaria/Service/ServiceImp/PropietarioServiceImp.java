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
        Propietario propietarioac = propietarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Propietario no encontrado con el id: " + id));

        // Actualiza los campos necesarios según los atributos de tu entidad Propietario
        propietario.setNombre(propietarioac.getNombre());
        propietario.setDocumento(propietarioac.getDocumento());
        propietario.setTelefono(propietarioac.getTelefono());
        propietario.setCorreo(propietarioac.getCorreo());

        return propietarioRepository.save(propietario);
    }
}

