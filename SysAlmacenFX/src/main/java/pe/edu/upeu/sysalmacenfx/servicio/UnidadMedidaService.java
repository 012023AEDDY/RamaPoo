package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.UnidadMedida;
import pe.edu.upeu.sysalmacenfx.repositorio.UnidadMedidaRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaService {
    @Autowired
    UnidadMedidaRepository repo;
    public UnidadMedida save(UnidadMedida to) {
        return repo.save(to);
    }

    public List<UnidadMedida> list() {
        return repo.findAll();
    }

    public Optional<UnidadMedida> update(Long id, String nuevoNombre) {
        Optional<UnidadMedida> optionalCategoria = repo.findById(id);
        if (optionalCategoria.isPresent()) {
            UnidadMedida nombremedida = optionalCategoria.get();
            nombremedida.setNombreMedida(nuevoNombre);
            return Optional.of(repo.save(nombremedida));
        }
        return Optional.empty();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAll() {
        repo.deleteAll();
    }

    public Optional<UnidadMedida> buscarId(Long id) {
        return repo.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (UnidadMedida cate : repo.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdUnidad()),
                    cate.getNombreMedida()));

        }
        return listar;

    }
}

