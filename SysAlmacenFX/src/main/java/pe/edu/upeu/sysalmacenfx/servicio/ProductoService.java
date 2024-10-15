package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository repo;
    public Producto save(Producto to) {
        return repo.save(to);
    }

    public List<Producto> list() {
        return repo.findAll();
    }

    public Optional<Producto> update(Long id, String nuevoNombre) {
        Optional<Producto> optionalProducto = repo.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setNombre(nuevoNombre);
            return Optional.of(repo.save(producto));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Optional<Producto> buscarId(Long id) {
        return repo.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Producto cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdProducto()),
                    cate.getNombre()));

        }
        return listar;

    }
}

