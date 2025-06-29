package ar.edu.utn.tup.pro.iii.controllers;

import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoCreate;
import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoResponse;
import ar.edu.utn.tup.pro.iii.services.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public ResponseEntity<List<PrestamoDtoResponse>> getPrestamos(
            @RequestParam(name = "usuario_id") Optional<Long> usuarioId,
            @RequestParam(name = "libro_id") Optional<Long> libroId,
            @RequestParam(name = "estado") Optional<String> estado
            ) {
        if(usuarioId.isPresent()){
            return ResponseEntity.ok(prestamoService.getPrestamoDtoByUsuarioId(usuarioId.get()));
        } else if(libroId.isPresent()){
            return ResponseEntity.ok(prestamoService.getPrestamoDtoByLibroId(libroId.get()));
        } else if(estado.isPresent()){
            return ResponseEntity.ok(prestamoService.getPrestamoDtoByEstado(estado.get()));
        } else {
            return ResponseEntity.ok(prestamoService.getPrestamos());
        }
    }

    @PostMapping("/prestamos")
    public ResponseEntity<PrestamoDtoResponse> createPrestamo(@RequestBody PrestamoDtoCreate prestamo){
        return ResponseEntity.ok(prestamoService.savePrestamo(prestamo));
    }

}
