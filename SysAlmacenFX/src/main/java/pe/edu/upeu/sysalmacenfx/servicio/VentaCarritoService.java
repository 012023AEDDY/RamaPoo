package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.VentCarrito;
import pe.edu.upeu.sysalmacenfx.repositorio.VentaCarritoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaCarritoService {
    @Autowired
    VentaCarritoRepository service;

    public VentCarrito save(VentCarrito to) {
        return service.save(to);
    }

    public List<VentCarrito> list() {
        return service.findAll();
    }

    public Optional<VentCarrito> update(Long id, String nuevoNombre) {
        Optional<VentCarrito> optionalVentCarrito = service.findById(id);
        if (optionalVentCarrito.isPresent()) {
            VentCarrito holi = optionalVentCarrito.get();
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

    public Optional<VentCarrito> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (VentCarrito cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdVentCarrito()), cate.getNombre()));
        }
        return listar;
    }
}
