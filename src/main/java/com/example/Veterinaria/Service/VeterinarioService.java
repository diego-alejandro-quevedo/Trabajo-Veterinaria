package com.example.Veterinaria.Service;

import com.example.Veterinaria.Entity.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {

    // Listar Veterinarios

    List<Veterinario> listarVeterinarios();

// litar Veterinario por Id

    Optional<Veterinario> buscarPropietarioPorId(Long id);

// Crear una Veterinarios

    Veterinario crearVeterinarios(Veterinario veterinario);

// Eliminar Veterinarios

    void eliminarVeterinarios(Long id);

// Actualizar datos Veterinarios

    Veterinario actualizarVeterinarios(Long id, Veterinario veterinario);

}


