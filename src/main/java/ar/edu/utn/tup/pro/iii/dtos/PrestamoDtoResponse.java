package ar.edu.utn.tup.pro.iii.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestamoDtoResponse {

    private Long id;
    private UsuarioDtoResponse usuario;
    private LibroDtoResponse libro;
    @JsonProperty("fecha_prestamo")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaPrestamo;
    @JsonProperty("fecha_devolucion")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaDevolucion;
    private String estado;

}
