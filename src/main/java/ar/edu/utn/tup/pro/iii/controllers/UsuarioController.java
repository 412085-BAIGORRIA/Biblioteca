package ar.edu.utn.tup.pro.iii.controllers;

import ar.edu.utn.tup.pro.iii.dtos.UsuarioDtoResponse;
import ar.edu.utn.tup.pro.iii.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioDtoResponse>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

}
