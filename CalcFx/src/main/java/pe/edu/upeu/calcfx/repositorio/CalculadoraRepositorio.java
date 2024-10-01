package pe.edu.upeu.calcfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.calcfx.modelo.calcTO;

@Repository
public interface CalculadoraRepositorio extends JpaRepository<calcTO, Long> {
}
