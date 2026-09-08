package com.example.Veterinaria.Controller;

import com.example.Veterinaria.Entity.Mascota;
import com.example.Veterinaria.Entity.Propietario;
import com.example.Veterinaria.Service.PropietarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor

@RequestMapping("api/propietario")

public class PropietarioController {

    private final PropietarioService propietarioService;


    @GetMapping("/ListaPropietarios")
    public ResponseEntity<List<Propietario>> listarPropietarios() {
        List<Propietario> propietarios = propietarioService.listarPropietarios();
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/ListarPropietarioId/{id}")
    public ResponseEntity<Propietario> buscarPropietarioPorId(@PathVariable Long id) {
        return propietarioService.buscarPropietarioPorId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crearPropietario")
    public ResponseEntity<Propietario> crearPropietario(Propietario propietario){

        Propietario nuevoPropietario = propietarioService.crearPropietario(propietario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPropietario);

    }

    @DeleteMapping("/eliminarMascota/{id}")
    public ResponseEntity<Void> eliminarPropietario(@PathVariable Long id) {
        propietarioService.eliminarPropietario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizarPropietario/{id}")
    public ResponseEntity<Propietario> actualizarPropietario(@PathVariable Long id, @RequestBody
    Propietario propietarioDetalles) {
        Propietario propietarioActualizado = propietarioService.actualizarPropietario(id, propietarioDetalles);
        return ResponseEntity.ok(propietarioActualizado);
    }



}
