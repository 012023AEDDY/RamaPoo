package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.modelo.Venta;

@Repository //este es el crud
public interface VentaRepository  extends JpaRepository<Venta, Long> {// toda la logica esta enn JpaRepository
}
