package ar.edu.utn.tup.pro.iii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrestamoDtoCreate {

    @JsonProperty("usuario_id")
    private Long usuarioId;
    @JsonProperty("libro_id")
    private Long libroId;
    @JsonProperty("fecha_prestamo")
    private LocalDate fechaPrestamo;

}
