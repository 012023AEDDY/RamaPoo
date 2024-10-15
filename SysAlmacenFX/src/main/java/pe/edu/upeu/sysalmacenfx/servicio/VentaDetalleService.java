package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.VentaDetalle;
import pe.edu.upeu.sysalmacenfx.repositorio.VentaDetalleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentaDetalleService {
    @Autowired
    VentaDetalleRepository service;

    public VentaDetalle save(VentaDetalle to) {
        return service.save(to);
    }

    public List<VentaDetalle> list() {
        return service.findAll();
    }

    public Optional<VentaDetalle> update(Long id, String nuevoNombre) {
        Optional<VentaDetalle> optionalVentaDetalle= service.findById(id);
        if (optionalVentaDetalle.isPresent()) {
            VentaDetalle holi = optionalVentaDetalle.get();
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

    public Optional<VentaDetalle> buscarId(Long id) {
        return service.findById(id);
    }

    public List
            <ComboBoxOption> listarCombobox(){
        List<ComboBoxOption> listar =new ArrayList<>();//instanciando y listar UN OBJETO
        for
        (VentaDetalle cate : service.findAll()) {
            listar.add(new ComboBoxOption(String.valueOf(cate.getIdVentaDetalle()), cate.getNombre()));
        }
        return listar;
    }
}
