package ar.edu.utn.tup.pro.iii.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "prestamos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UsuarioEntity usuario;

    @ManyToOne
    private LibroEntity libro;

    @Column(nullable = false, name = "fecha_prestamo")
    private LocalDate fechaPrestamo;

    @Column(name = "fecha_devolucion")
    private LocalDate fechaDevolucion;

    @Column(nullable = false)
    private String estado;


}
