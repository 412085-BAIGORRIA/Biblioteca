package ar.edu.utn.tup.pro.iii.services;

import ar.edu.utn.tup.pro.iii.dtos.LibroDtoResponse;
import ar.edu.utn.tup.pro.iii.entities.LibroEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibroService {

    List<LibroDtoResponse> getLibros();
    List<LibroDtoResponse> getLibrosByDisponible(boolean disponible);
    List<LibroDtoResponse> getLibrosByCategoria(String categoria);

}
