package composite;

public abstract class Archivo implements Elemento {
    protected String nombre;
    protected int tamanio;

    public Archivo(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
    }

    @Override
    public int obtenerTamanio() {
        return this.tamanio;
    }
}