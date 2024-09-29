package pe.edu.upeu.calcfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.dao.CalculadoraDao;
import pe.edu.upeu.calcfx.modelo.calcTO;

import java.util.ArrayList;
import java.util.List;
@Service

public class CalcServiceImp  implements calcServivio{
    @Autowired
    CalculadoraDao dbOper;
    //List<calcTO> dbOper = new ArrayList<>();

    @Override
    public void guardarResultados(calcTO to) {dbOper.insertar(to);}

    @Override
    public List<calcTO> obtenerResultado() {return dbOper.listar();}

    @Override
    public void actualizarResultado(calcTO to, int index) {
       // dbOper.set(index, to);
    }
    @Override
    public void eliminarResultado(int index) {
        //dbOper.remove(index);
    }
}
