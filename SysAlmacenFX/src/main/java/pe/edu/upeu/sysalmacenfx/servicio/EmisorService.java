package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Emisor;
import pe.edu.upeu.sysalmacenfx.repositorio.EmisorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmisorService {
    @Autowired
    EmisorRepository service;
    public Emisor save(Emisor to) {
        return service.save(to);
    }

    public List<Emisor> list() {
        return service.findAll();
    }

    public Optional<Emisor> update(Long id, String nuevoNombre) {
        Optional<Emisor> optionalEmisor = service.findById(id);
        if (optionalEmisor.isPresent()) {
            Emisor holi = optionalEmisor.get();
            holi.setNombres (nuevoNombre);
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

    public Optional<Emisor> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Emisor cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdEmisor()), cate.getNombres()));
        }
        return listar;
    }
}
