package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.HistoriaClinica;

import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Service.HistoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("api/historiaClinica")

public class HistoriaController {

    private final HistoriaService historiaService;


    @GetMapping("/ListarHistorias")
    public ResponseEntity<List<HistoriaClinica>> listarPropietarios() {
        List<HistoriaClinica> Historias = historiaService.listarHistorias();
        return ResponseEntity.ok(Historias);
    }

    @GetMapping("/ListarHistoriasId/{id}")
    public ResponseEntity<HistoriaClinica> buscarPropietarioPorId(@PathVariable Long id) {
        return historiaService.buscarHistoriaPorId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crearHistoria")
    public ResponseEntity<HistoriaClinica> crearPropietario( @Valid @RequestBody HistoriaClinica historia){

        HistoriaClinica nuevaHistoria = historiaService.crearHistoria(historia);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaHistoria);

    }

    @DeleteMapping("/eliminarHistoria/{id}")
    public ResponseEntity<Void> eliminarPropietario(@PathVariable Long id) {
        historiaService.eliminarHistoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizarHistoria/{id}")
    public ResponseEntity<HistoriaClinica> actualizarHistoriaClinica(@PathVariable Long id, @RequestBody HistoriaClinica historia) {
        HistoriaClinica historiaActualizada = historiaService.actualizarHistoria(id, historia);
        return ResponseEntity.ok(historiaActualizada);
    }



}
