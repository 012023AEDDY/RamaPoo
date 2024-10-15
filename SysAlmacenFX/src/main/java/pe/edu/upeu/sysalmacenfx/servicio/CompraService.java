package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Compra;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {
    @Autowired
    CompraRepository service;

    public Compra save(Compra to) {
        return service.save(to);
    }

    public List<Compra> list() {
        return service.findAll();
    }

    public Optional<Compra> update(Long id, String nuevoNombre) {
        Optional<Compra> optionalCompra = service.findById(id);
        if (optionalCompra.isPresent()) {
            Compra holi = optionalCompra.get();
            holi.setNombre(nuevoNombre);
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

    public Optional<Compra> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Compra cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdCompra()), cate.getNombres()));
        }
        return listar;
    }
}


