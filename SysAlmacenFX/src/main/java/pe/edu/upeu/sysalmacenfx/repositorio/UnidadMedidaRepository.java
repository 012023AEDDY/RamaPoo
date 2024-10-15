package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.modelo.UnidadMedida;
@Repository// ya lo seria nesesario porque jps esta herdan do repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida,Long> {
}
