package ar.edu.utn.tup.pro.iii.services;

import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoCreate;
import ar.edu.utn.tup.pro.iii.dtos.PrestamoDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrestamoService {

    List<PrestamoDtoResponse> getPrestamoDtoByLibroId(Long libroId);
    List<PrestamoDtoResponse> getPrestamoDtoByUsuarioId(Long usuarioId);
    List<PrestamoDtoResponse> getPrestamoDtoByEstado(String estado);
    List<PrestamoDtoResponse> getPrestamos();
    PrestamoDtoResponse savePrestamo(PrestamoDtoCreate prestamo);
    PrestamoDtoResponse getPrestamoById(Long id);

}
