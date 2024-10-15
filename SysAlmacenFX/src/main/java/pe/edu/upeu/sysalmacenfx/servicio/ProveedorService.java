package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Proveedor;
import pe.edu.upeu.sysalmacenfx.repositorio.ProveedorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository service;

    public Proveedor save(Proveedor to) {
        return service.save(to);
    }

    public List<Proveedor> list() {
        return service.findAll();
    }

    public Optional<Proveedor> update(Long id, String nuevoNombre) {
        Optional<Proveedor> optionalProveedor = service.findById(id);
        if (optionalProveedor.isPresent()) {
            Proveedor holi = optionalProveedor.get();
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

    public Optional<Proveedor> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Proveedor cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdProveedor()), cate.getNombre()));
        }
        return listar;
    }
}

