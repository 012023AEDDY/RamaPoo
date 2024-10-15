package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;
import pe.edu.upeu.sysalmacenfx.repositorio.CategoriaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired // inyeccion de dependencias por atibutos
    CategoriaRepository repo;
    //CategoriaRepository repo=new CategoriaRepository() no se puede hacer esto
//C
    public Categoria save(Categoria to) {
        return repo.save(to);
    }

    public List<Categoria> list() {
        return repo.findAll();
    }

    public Optional<Categoria> update(Long id, String nuevoNombre) {
        Optional<Categoria> optionalCategoria = repo.findById(id);
        if (optionalCategoria.isPresent()) {
            Categoria categoria = optionalCategoria.get();
            categoria.setNombre(nuevoNombre);
            return Optional.of(repo.save(categoria));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Optional<Categoria> buscarId(Long id) {
        return repo.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Categoria cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdCategoria()),
                    cate.getNombre()));

        }
        return listar;

    }
}




