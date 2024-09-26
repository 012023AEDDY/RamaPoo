package pe.edu.upeu.calcfx.servicio;

import pe.edu.upeu.calcfx.modelo.calcTO;

import java.util.List;

public interface calcServivio {

    public void guardarResultados(calcTO to);
    public List<calcTO> obtenerResultado();
    public void actualizarResultado(calcTO to, int index);
    public void eliminarResultado(int index);
}
