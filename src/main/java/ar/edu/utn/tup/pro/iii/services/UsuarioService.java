package ar.edu.utn.tup.pro.iii.services;

import ar.edu.utn.tup.pro.iii.dtos.UsuarioDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {

    List<UsuarioDtoResponse> getUsuarios();
    UsuarioDtoResponse getUsuarioById(Long id);

}
