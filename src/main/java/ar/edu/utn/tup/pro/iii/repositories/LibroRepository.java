package ar.edu.utn.tup.pro.iii.repositories;

import ar.edu.utn.tup.pro.iii.entities.LibroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
}
