package main;

import factoryMethod.CreadorArchivo;
import factoryMethod.CreadorPDF;
import factoryMethod.CreadorTexto;
import composite.Carpeta;
import adapter.AdaptadorCorreo;
import adapter.CorreoLegacy;
import adapter.Notificador;

public class Main {

    static void comprobar(String nombre, int esperado, int obtenido) {
        if (esperado == obtenido) {
            System.out.println("OK: " + nombre);
        } else {
            System.out.println("FALLO: " + nombre + " | esperado=" + esperado + " obtenido=" + obtenido);
        }
    }

    static void ejecutarPruebas() {
        System.out.println("--- EJECUTANDO PRUEBAS ---");
        CreadorArchivo creadorPDF = new CreadorPDF();
        CreadorArchivo creadorTexto = new CreadorTexto();

        // Caso 1: Carpeta vacía
        Carpeta vacia = new Carpeta("Vacia");
        comprobar("Carpeta vacia", 0, vacia.obtenerTamanio());

        // Caso 2: Carpeta con un PDF de 120
        Carpeta c1 = new Carpeta("C1");
        c1.agregar(creadorPDF.crearArchivo("pdf1.pdf", 120));
        comprobar("Carpeta con un PDF de 120", 120, c1.obtenerTamanio());

        // Caso 3: Carpeta con PDF de 120 y texto de 80
        Carpeta c2 = new Carpeta("C2");
        c2.agregar(creadorPDF.crearArchivo("pdf1.pdf", 120));
        c2.agregar(creadorTexto.crearArchivo("txt1.txt", 80));
        comprobar("Carpeta con PDF de 120 y texto de 80", 200, c2.obtenerTamanio());

        // Caso 4: Ejemplo completo con subcarpeta de 50
        Carpeta cPadre = new Carpeta("Padre");
        cPadre.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
        cPadre.agregar(creadorTexto.crearArchivo("notas.txt", 80));
        Carpeta cHija = new Carpeta("Hija");
        cHija.agregar(creadorTexto.crearArchivo("ejemplo.txt", 50));
        cPadre.agregar(cHija);
        comprobar("Ejemplo completo con subcarpeta de 50", 250, cPadre.obtenerTamanio());

        // Caso 5: Carpeta con un archivo de tamaño 0
        Carpeta cZero = new Carpeta("CZero");
        cZero.agregar(creadorTexto.crearArchivo("vacio.txt", 0));
        comprobar("Carpeta con archivo de tamaño 0", 0, cZero.obtenerTamanio());

        System.out.println("---------------------------\n");
    }

    static void enviarResultado(Carpeta carpeta, String destino, Notificador notificador) {
        int tamanioTotal = carpeta.obtenerTamanio();
        String mensaje = "Tamanio total: " + tamanioTotal;
        notificador.enviar(destino, mensaje);
    }

    public static void main(String[] args) {
        // 1. Ejecución de pruebas
        ejecutarPruebas();

        // 2. Ejecución del programa principal
        CreadorArchivo creadorPDF = new CreadorPDF();
        CreadorArchivo creadorTexto = new CreadorTexto();

        Carpeta clase = new Carpeta("MyP");
        clase.agregar(creadorPDF.crearArchivo("practica.pdf", 120));
        clase.agregar(creadorTexto.crearArchivo("notas.txt", 80));

        Carpeta ejemplos = new Carpeta("Ejemplos");
        ejemplos.agregar(creadorTexto.crearArchivo("ejemplo.txt", 50));

        clase.agregar(ejemplos);

        // Cálculo e impresión en terminal (250)
        System.out.println(clase.obtenerTamanio());

        // Envío mediante adaptador
        Notificador notificador = new AdaptadorCorreo(new CorreoLegacy());
        enviarResultado(clase, "profesor@universidad.edu", notificador);
    }
}
