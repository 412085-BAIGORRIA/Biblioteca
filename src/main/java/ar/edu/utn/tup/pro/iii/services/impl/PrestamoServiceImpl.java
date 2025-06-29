package ar.edu.utn.tup.pro.iii.services.impl;

import ar.edu.utn.tup.pro.iii.dtos.LibroDtoResponse;
import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoCreate;
import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoResponse;
import ar.edu.utn.tup.pro.iii.dtos.UsuarioDtoResponse;
import ar.edu.utn.tup.pro.iii.entities.LibroEntity;
import ar.edu.utn.tup.pro.iii.entities.PrestamoEntity;
import ar.edu.utn.tup.pro.iii.entities.UsuarioEntity;
import ar.edu.utn.tup.pro.iii.repositories.PrestamoRepository;
import ar.edu.utn.tup.pro.iii.services.LibroService;
import ar.edu.utn.tup.pro.iii.services.PrestamoService;
import ar.edu.utn.tup.pro.iii.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final LibroService libroService;
    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;

    @Override
    public List<PrestamoDtoResponse> getPrestamoDtoByLibroId(Long libroId) {

        List<PrestamoEntity> prestamosEntities = prestamoRepository.findPrestamoEntitiesByLibroId(libroId);

        return toDto(prestamosEntities);
    }

    @Override
    public List<PrestamoDtoResponse> getPrestamoDtoByUsuarioId(Long usuarioId) {

        List<PrestamoEntity> prestamosEntities = prestamoRepository.findPrestamoEntitiesByUsuarioId(usuarioId);

        return toDto(prestamosEntities);

    }

    @Override
    public List<PrestamoDtoResponse> getPrestamoDtoByEstado(String estado) {
        List<PrestamoEntity> prestamosEntities = prestamoRepository.findPrestamoEntitiesByEstado(estado);

        return toDto(prestamosEntities);
    }

    @Override
    public List<PrestamoDtoResponse> getPrestamos() {
        List<PrestamoEntity> prestamosEntities = prestamoRepository.findAll();

        return toDto(prestamosEntities);
    }

    @Override
    public PrestamoDtoResponse savePrestamo(PrestamoDtoCreate prestamo) {

        UsuarioDtoResponse usuarioDtoResponse = usuarioService.getUsuarioById(prestamo.getUsuarioId());
        LibroDtoResponse libroDtoResponse = libroService.getLibroById(prestamo.getLibroId());

        if(!libroDtoResponse.isDisponible()){
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), "Libro no disponible");
        }

        if(usuarioDtoResponse != null && libroDtoResponse != null) {
            PrestamoEntity prestamoEntity = PrestamoEntity.builder()
                    .usuario(modelMapper.map(usuarioDtoResponse, UsuarioEntity.class))
                    .libro(modelMapper.map(libroDtoResponse, LibroEntity.class))
                    .fechaPrestamo(prestamo.getFechaPrestamo())
                    .estado("ACTIVO")
                    .build();

            prestamoEntity.getLibro().setDisponible(false);

            PrestamoEntity savedPrestamoEntity = prestamoRepository.save(prestamoEntity);

            return modelMapper.map(savedPrestamoEntity, PrestamoDtoResponse.class);
        } else {
            throw new EntityNotFoundException("Usuario o Libro no encontrado");
        }

    }

    @Override
    public PrestamoDtoResponse getPrestamoById(Long id) {

        PrestamoEntity prestamoEntity = prestamoRepository.getReferenceById(id);

        prestamoEntity.setEstado("DEVUELTO");
        prestamoEntity.setFechaDevolucion(LocalDate.now());
        prestamoEntity.getLibro().setDisponible(true);

        PrestamoEntity savedPrestamoEntity = prestamoRepository.save(prestamoEntity);

        return modelMapper.map(savedPrestamoEntity, PrestamoDtoResponse.class);

    }


    private List<PrestamoDtoResponse> toDto(List<PrestamoEntity> prestamosEntities) {

        List<PrestamoDtoResponse> prestamosDtoResponses = prestamosEntities.stream().map(
                p -> PrestamoDtoResponse.builder()
                        .id(p.getId())
                        .usuario(modelMapper.map(p.getUsuario(), UsuarioDtoResponse.class))
                        .libro(modelMapper.map(p.getLibro(), LibroDtoResponse.class))
                        .fechaPrestamo(p.getFechaPrestamo())
                        .fechaDevolucion(p.getFechaDevolucion())
                        .estado(p.getEstado())
                        .build()
        ).toList();

        return prestamosDtoResponses;
    }
}
