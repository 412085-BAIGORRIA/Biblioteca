package ar.edu.utn.tup.pro.iii.services.impl;

import ar.edu.utn.tup.pro.iii.dtos.LibroDtoResponse;
import ar.edu.utn.tup.pro.iii.entities.LibroEntity;
import ar.edu.utn.tup.pro.iii.repositories.LibroRepository;
import ar.edu.utn.tup.pro.iii.services.LibroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;


    @Override
    public List<LibroDtoResponse> getLibros() {

        List<LibroEntity> librosEntities = libroRepository.findAll();

        List<LibroDtoResponse> librosDtoResponses = librosEntities.stream().map(
                l -> LibroDtoResponse.builder()
                        .id(l.getId())
                        .titulo(l.getTitulo())
                        .autor(l.getAutor())
                        .isbn(l.getIsbn())
                        .categoria(l.getCategoria())
                        .disponible(l.isDisponible())
                        .build()
        ).toList();

        return librosDtoResponses;
    }

    @Override
    public List<LibroDtoResponse> getLibrosByDisponible(boolean disponible) {

        List<LibroEntity> librosEntities = libroRepository.findByDisponible(disponible);

        List<LibroDtoResponse> librosDtoResponses = librosEntities.stream().map(
                l -> LibroDtoResponse.builder()
                        .id(l.getId())
                        .titulo(l.getTitulo())
                        .autor(l.getAutor())
                        .isbn(l.getIsbn())
                        .categoria(l.getCategoria())
                        .disponible(l.isDisponible())
                        .build()
        ).toList();

        return librosDtoResponses;
    }

    @Override
    public List<LibroDtoResponse> getLibrosByCategoria(String categoria) {

        List<LibroEntity> librosEntities = libroRepository.findByCategoria(categoria);

        List<LibroDtoResponse> librosDtoResponses = librosEntities.stream().map(
                l -> LibroDtoResponse.builder()
                        .id(l.getId())
                        .titulo(l.getTitulo())
                        .autor(l.getAutor())
                        .isbn(l.getIsbn())
                        .categoria(l.getCategoria())
                        .disponible(l.isDisponible())
                        .build()
        ).toList();

        return librosDtoResponses;

    }
}
