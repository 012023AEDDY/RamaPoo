package pe.edu.upeu.calcfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.calcTO;
import pe.edu.upeu.calcfx.repositorio.CalculadoraRepositorio;

import java.util.ArrayList;
import java.util.List;
@Service

public class CalcServiceImp  implements calcServivio{
    @Autowired
    CalculadoraRepositorio repository;
    //List<calcTO> dbOper = new ArrayList<>();

    @Override
    public void guardarResultados(calcTO to) {repository.save(to);}

    @Override
    public List<calcTO> obtenerResultado() {
        return repository.findAll();
    }

    @Override
    public void actualizarResultado(calcTO to, Long index) {
        to.setId(index);
        repository.save(to);}
    @Override
    public void eliminarResultado(Long index) {
        repository.deleteById(index);
    }
}
