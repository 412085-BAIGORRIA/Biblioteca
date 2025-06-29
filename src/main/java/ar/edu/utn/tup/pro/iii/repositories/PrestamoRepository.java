package ar.edu.utn.tup.pro.iii.repositories;

import ar.edu.utn.tup.pro.iii.entities.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<PrestamoEntity, Long> {

    List<PrestamoEntity> findPrestamoEntitiesByLibroId(Long libroId);
    List<PrestamoEntity> findPrestamoEntitiesByUsuarioId(Long usuarioId);
    List<PrestamoEntity> findPrestamoEntitiesByEstado(String estado);


}
