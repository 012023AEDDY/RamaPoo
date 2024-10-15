package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Perfil;
import pe.edu.upeu.sysalmacenfx.repositorio.PerfilRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerfilServicio {
    @Autowired
    PerfilRepository service;

    public Perfil save(Perfil to) {
        return service.save(to);
    }

    public List<Perfil> list() {
        return service.findAll();
    }

    public Optional<Perfil> update(Long id, String nuevoNombre) {
        Optional<Perfil> optionalPerfil = service.findById(id);
        if (optionalPerfil.isPresent()) {
            Perfil holi = optionalPerfil.get();
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

    public Optional<Perfil> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Perfil cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdPerfil()), cate.getNombre()));
        }
        return listar;
    }
}


