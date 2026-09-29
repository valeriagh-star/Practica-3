package composite;

import java.util.ArrayList;
import java.util.List;

public class Carpeta implements Elemento {
    private String nombre;
    private List<Elemento> elementos = new ArrayList<>();

    public Carpeta(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(Elemento elemento) {
        elementos.add(elemento);
    }

    @Override
    public int obtenerTamanio() {
        int total = 0;
        for (Elemento elemento : elementos) {
            total += elemento.obtenerTamanio();
        }
        return total;
    }
}
