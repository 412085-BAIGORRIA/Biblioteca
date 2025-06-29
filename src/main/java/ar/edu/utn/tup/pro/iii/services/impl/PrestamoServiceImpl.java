package ar.edu.utn.tup.pro.iii.services.impl;

import ar.edu.utn.tup.pro.iii.dtos.LibroDtoResponse;
import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoResponse;
import ar.edu.utn.tup.pro.iii.dtos.UsuarioDtoResponse;
import ar.edu.utn.tup.pro.iii.entities.PrestamoEntity;
import ar.edu.utn.tup.pro.iii.entities.UsuarioEntity;
import ar.edu.utn.tup.pro.iii.repositories.PrestamoRepository;
import ar.edu.utn.tup.pro.iii.services.PrestamoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
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
