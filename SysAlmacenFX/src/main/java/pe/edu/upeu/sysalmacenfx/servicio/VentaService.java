package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Venta;
import pe.edu.upeu.sysalmacenfx.repositorio.VentaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    VentaRepository service;

    public Venta save(Venta to) {
        return service.save(to);
    }

    public List<Venta> list() {
        return service.findAll();
    }

    public Optional<Venta> update(Long id, String nuevoNombre) {
        Optional<Venta> optionalVenta = service.findById(id);
        if (optionalVenta.isPresent()) {
            Venta holi = optionalVenta.get();
            holi.setNombre (nuevoNombre);
            return Optional.of(service.save(holi));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        service.deleteById(id);
    }

    public void deleteAll() {
        service.deleteAll();
    }

    public Optional<Venta> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Venta cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdVenta()), cate.getNombre()));
        }
        return listar;
    }
}