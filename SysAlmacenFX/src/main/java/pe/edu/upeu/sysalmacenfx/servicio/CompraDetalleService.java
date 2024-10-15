package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.CompraDetalle;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraDetalleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompraDetalleService {
    @Autowired
    CompraDetalleRepository service;

    public CompraDetalle save(CompraDetalle to) {
        return service.save(to);
    }

    public List<CompraDetalle> list() {
        return service.findAll();
    }

    public Optional<CompraDetalle> update(Long id, String nuevoNombre) {
        Optional<CompraDetalle> optionalCompraDetalle = service.findById(id);
        if (optionalCompraDetalle.isPresent()) {
            CompraDetalle client = optionalCompraDetalle.get();
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

    public Optional<CompraDetalle> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (CompraDetalle cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdCompraDetalle()), cate.getNombre()));
        }
        return listar;
    }
}


