package ar.edu.utn.tup.pro.iii.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LibroDtoResponse {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String categoria;
    private boolean disponible;

}
