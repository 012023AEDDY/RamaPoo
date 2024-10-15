package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Usuario;
import pe.edu.upeu.sysalmacenfx.repositorio.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository service;

    public Usuario save(Usuario to) {
        return service.save(to);
    }

    public List<Usuario> list() {
        return service.findAll();
    }

    public Optional<Usuario> update(Long id, String nuevoNombre) {
        Optional<Usuario> optionalUsuario = service.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario holi = optionalUsuario.get();
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

    public Optional<Usuario> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Usuario cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdUsuario()), cate.getNombre()));
        }
        return listar;
    }
}