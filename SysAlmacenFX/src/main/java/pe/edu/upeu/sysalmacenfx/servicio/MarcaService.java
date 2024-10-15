package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;
import pe.edu.upeu.sysalmacenfx.modelo.Marca;
import pe.edu.upeu.sysalmacenfx.repositorio.MarcaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired // inyeccion de dependencias por atibutos
    MarcaRepository repo;
    //CategoriaRepository repo=new CategoriaRepository() no se puede hacer esto
//C
    public Marca save(Marca to) {
        return repo.save(to);
    }

    public List<Marca> list() {
        return repo.findAll();
    }

    public Optional<Marca> update(Long id, String nuevoNombre) {
        Optional<Marca> optionalMarca = repo.findById(id);
        if (optionalMarca.isPresent()) {
            Marca marca = optionalMarca.get();
            marca.setNombre(nuevoNombre);
            return Optional.of(repo.save(marca));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Optional<Marca> buscarId(Long id) {
        return repo.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Marca cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdMarca()),
                    cate.getNombre()));

        }
        return listar;

    }
}