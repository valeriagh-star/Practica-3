package factoryMethod;

import composite.Archivo;
import composite.ArchivoTexto;

public class CreadorTexto extends CreadorArchivo {
    @Override
    public Archivo crearArchivo(String nombre, int tamanio) {
        return new ArchivoTexto(nombre, tamanio);
    }
}