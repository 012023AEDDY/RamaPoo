package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.repositorio.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository service;
    public Cliente save(Cliente to) {
        return service.save(to);
    }

    public List<Cliente> list() {
        return service.findAll();
    }

    public Optional<Cliente> update(Long id, String nuevoNombre) {
        Optional<Cliente> optionalCliente = service.findById(id);
        if (optionalCliente.isPresent()) {
            Cliente client = optionalCliente.get();
            client.setNombres(nuevoNombre);
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

    public Optional<Cliente> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (Cliente cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdCliente()), cate.getNombres()));
        }
        return listar;
    }
}

