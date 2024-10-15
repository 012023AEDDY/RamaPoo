package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.CompCarrito;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraCarritoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class CompraCarritoService {
    @Autowired
    CompraCarritoRepository service;

    public CompCarrito save(CompCarrito to) {
        return service.save(to);
    }

    public List<CompCarrito> list() {
        return service.findAll();
    }

    public Optional<CompCarrito> update(Long id, String nuevoNombre) {
        Optional<CompCarrito> optionalCompCarrito = service.findById(id);
        if (optionalCompCarrito.isPresent()) {
            CompCarrito client = optionalCompCarrito.get();
            client.setNombre(nuevoNombre);
            return Optional.of(service.save(client));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        service.deleteById(id);
    }

    public void deleteAll() {
        service.deleteAll();
    }

    public Optional<CompCarrito> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (CompCarrito cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdCompCarrito()), cate.getNombre()));
        }
        return listar;
    }
}
