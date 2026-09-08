package com.example.Veterinaria.Controller;


import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Service.MascotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("api/mascota")

public class MascotaController {

    private final MascotaService mascotaService;


    @GetMapping("/ListarMascota")
    public ResponseEntity<List<Mascota>> listarMascotas() {

        List<Mascota> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/ListarMascotaId/{id}")
    public ResponseEntity<Mascota>buscarMascotaPorId(@PathVariable Long id){

        return mascotaService.buscarMascotaPorId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/crearMascota")
    public ResponseEntity<Mascota> crearMascota(Mascota mascota){

        Mascota nuevaMascota = mascotaService.crearMascota(mascota);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }

    @DeleteMapping("/eliminarMascota/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/acutalizarMascota/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascotaDetalles) {
        Mascota mascotaActualizada = mascotaService.actualizarMascota(id, mascotaDetalles);
        return ResponseEntity.ok(mascotaActualizada);
    }

}
