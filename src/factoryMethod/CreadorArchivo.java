package factoryMethod;

import composite.Archivo;

public abstract class CreadorArchivo {
    public abstract Archivo crearArchivo(String nombre, int tamanio);
}
