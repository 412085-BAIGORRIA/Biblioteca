package ar.edu.utn.tup.pro.iii.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoResponse {

    private Long id;
    @JsonProperty("nombre_completo")
    private String nombreCompleto;
    private String email;
    private String telefono;

}
