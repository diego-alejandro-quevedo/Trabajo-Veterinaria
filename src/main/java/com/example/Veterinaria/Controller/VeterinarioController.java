package com.example.Veterinaria.Controller;


import com.example.Veterinaria.Entity.Veterinario;
import com.example.Veterinaria.Service.VeterinarioService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("api/veterinario")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping("/ListaVeterinario")
    public ResponseEntity<List<Veterinario>> listarVeterinario() {
        List<Veterinario> propietarios = veterinarioService.listarVeterinarios();
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/ListarVeterinarioId/{id}")
    public ResponseEntity<Veterinario> buscarVeterinarioPorId(@PathVariable Long id) {
        return veterinarioService.buscarPropietarioPorId(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/crearVeterinario")
    public ResponseEntity<Veterinario> crearVeterinario(Veterinario veterinario){

        Veterinario nuevoVeterinario = veterinarioService.crearVeterinarios(veterinario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoVeterinario);

    }

    @DeleteMapping("/eliminarVeterinario/{id}")
    public ResponseEntity<Void> eliminarveterinario(@PathVariable Long id) {
        veterinarioService.eliminarVeterinarios(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizarVeterinario/{id}")
    public ResponseEntity<Veterinario> actualizarVeterinario(@PathVariable Long id, @RequestBody Veterinario veterinarioDetalles) {
        Veterinario veterinarioActualizado = veterinarioService.actualizarVeterinarios(id, veterinarioDetalles);
        return ResponseEntity.ok(veterinarioActualizado);
    }


}
