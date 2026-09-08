package com.example.Veterinaria.Service;

import com.example.Veterinaria.Entity.Mascota;

import java.util.List;
import java.util.Optional;

public interface MascotaService {

// Listar mascotas Genral de mascotas

    List<Mascota> listarMascotas();

// litar mascota por Id

    Optional<Mascota> buscarMascotaPorId(Long id);

// Crear una mascota

    Mascota crearMascota(Mascota mascota);

// Eliminar mascota

    void eliminarMascota(Long id);

// Actualizar mascota

    Mascota actualizarMascota(Long id, Mascota mascota);

// Actualizar mascota

    List<Mascota> listarPorPropietario(Long propietarioId );

// asignar  mascota a veterinario

    Mascota asignarMascotaxVeterinario(Long mascotaId, Long veterinarioId );



}
