package ar.edu.utn.tup.pro.iii.controllers;

import ar.edu.utn.tup.pro.iii.dtos.LibroDtoResponse;
import ar.edu.utn.tup.pro.iii.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LibroController {

    private final LibroService libroService;

    @GetMapping("libros")
    public ResponseEntity<List<LibroDtoResponse>> getLibros() {
        return ResponseEntity.ok(libroService.getLibros());
    }

    @GetMapping("/libros/disponibles")
    public ResponseEntity<List<LibroDtoResponse>> getLibrosByDisponible(@RequestParam boolean disponible) {
        return ResponseEntity.ok(libroService.getLibrosByDisponible(disponible));
    }

    @GetMapping("/libros/categorias")
    public ResponseEntity<List<LibroDtoResponse>> getLibrosByCategoria(@RequestParam String categoria) {
        return ResponseEntity.ok(libroService.getLibrosByCategoria(categoria));
    }
}
