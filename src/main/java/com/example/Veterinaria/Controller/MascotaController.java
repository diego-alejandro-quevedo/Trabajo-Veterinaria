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


    @GetMapping("/ListarMAscota")
    public ResponseEntity<List<Mascota>> listarMascotas() {

        List<Mascota> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("/ListarMAscotaId/{id}")
    public ResponseEntity<Mascota>buscarMascotaPorId(@PathVariable Long id){

        return mascotaService.buscarMascotaPorId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/crearMascota")
    public ResponseEntity<Mascota> crearMascota(Mascota mascota){

        Mascota nuevaMascota = mascotaService.crearMascota(mascota);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
    }


}
