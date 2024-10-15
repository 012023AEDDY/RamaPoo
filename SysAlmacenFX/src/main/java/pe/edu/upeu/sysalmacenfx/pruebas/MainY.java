
package pe.edu.upeu.sysalmacenfx.pruebas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;
import pe.edu.upeu.sysalmacenfx.modelo.Marca;
import pe.edu.upeu.sysalmacenfx.servicio.MarcaService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainY {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Autowired
    MarcaService service;

    public void registro() {
        System.out.println("MAIN CATEGORIA");
        while (true) {
            System.out.println("Escriba el nombre de la categoría o 'salir' si quiere terminar:");
            String reg_Cat = SCANNER.nextLine().trim();
            if (reg_Cat.equalsIgnoreCase("salir")) {
                break;
            }
            if (reg_Cat.isEmpty()) {
                System.out.println("El espacio no puede estar vacío, inténtelo otra vez");
                continue;
            }
            Marca a = new Marca();
            a.setNombre(reg_Cat);
            Marca nuevaMarca = service.save(a);
            System.out.println("Categoría registrada con ID: " + nuevaMarca.getIdMarca() + ", Nombre: " + nuevaMarca.getNombre());
        }
    }

    public void listar() {
        List<Marca> hola = service.list();
        if (hola.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            System.out.println("ID\tNombre");
            for (Marca marquita : hola) {
                System.out.println(marquita.getIdMarca() + "\t" + marquita.getNombre());
            }
        }
    }

    public void actualizar() {
        // era añadir objeto:CATEGORIA TO
        try {

            System.out.println("Escribe el ID de la categoría que quieres modificar:");
            Long idCat = SCANNER.nextLong();
            SCANNER.nextLine();
            System.out.println("Escribe el nuevo nombre para la categoría:");
            String nuevoNombre = SCANNER.nextLine();
            Optional<Marca> marcaActualizada = service.update(idCat,nuevoNombre);
            if (marcaActualizada.isPresent()) {
                System.out.println("Categoría actualizada.");
            } else {
                System.out.println("No se encontró una categoría con el ID proporcionado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID debe ser numérico.");
            SCANNER.nextLine();
        }
    }

    public void borrar() {
        try {
            System.out.println("Escribe el ID de la categoría que quieres eliminar:");
            Long idBorrar = SCANNER.nextLong();
            SCANNER.nextLine();
            service.delete(idBorrar);
            System.out.println("Categoría eliminada.");
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID debe ser numérico.");
            SCANNER.nextLine();  // Limpiar el buffer
        }
    }

    public void borrarTodas() {
        service.deleteAll();
        System.out.println("Todas las marcas han sido eliminadas.");
    }

    public void menumarca() {
        String mensaje = "Seleccione una opción Yo EDDY:\n1 = Crear\n2 = Listar\n3 = Actualizar\n4 = Borrar\n5 = Borrar todas\n0 = Salir";
        int opcion = -1;

        while (opcion != 0) {
            System.out.println(mensaje);
            try {
                opcion = Integer.parseInt(SCANNER.nextLine());
                switch (opcion) {
                    case 1:
                        registro();
                        break;
                    case 2:
                        listar();
                        break;
                    case 3:
                        actualizar();
                        break;
                    case 4:
                        borrar();
                        break;
                    case 5:
                        borrarTodas();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, inténtelo nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }
}
