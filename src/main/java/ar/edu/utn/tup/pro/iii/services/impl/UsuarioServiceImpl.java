package ar.edu.utn.tup.pro.iii.services.impl;

import ar.edu.utn.tup.pro.iii.dtos.UsuarioDtoResponse;
import ar.edu.utn.tup.pro.iii.entities.UsuarioEntity;
import ar.edu.utn.tup.pro.iii.repositories.UsuarioRepository;
import ar.edu.utn.tup.pro.iii.services.UsuarioService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioDtoResponse> getUsuarios() {

        List<UsuarioEntity> usuarioEntities = usuarioRepository.findAll();

        List<UsuarioDtoResponse> usuarioDtoResponses = usuarioEntities.stream().map(
                u -> UsuarioDtoResponse.builder()
                        .id(u.getId())
                        .nombreCompleto(u.getNombreCompleto())
                        .email(u.getEmail())
                        .telefono(u.getTelefono())
                        .build()
        ).toList();

        return usuarioDtoResponses;

    }

}
