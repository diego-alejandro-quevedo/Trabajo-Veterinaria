package com.example.Veterinaria.Service;

import com.example.Veterinaria.Entity.Propietario;

import java.util.List;

public interface PropietarioService {
    // Listar propirtaros

    List<Propietario> listarPropietarios();

// litar propietario por Id

    Propietario buscarPropietarioPorId(Long id);

// Crear una Propietario

    Propietario crearPropietario(Propietario propietario);

// Eliminar Propietario

    void eliminarPropietario(Long id);

// Actualizar datos Propietario

    Propietario actualizarPropietario(Long id, Propietario propietario);

}

