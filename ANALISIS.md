# Práctica 3

## Análisis de Refactorización 

## Etapa 1: Diagnóstico del programa inicial

1. **`agregarArchivo`**: Se encarga de evaluar mediante estructuras condicionales (`if/else`) qué tipo concreto de archivo instanciar (`ArchivoPDF` o `ArchivoTexto`) y agregarlo a la lista de archivos de una carpeta. 


2. **`obtenerTamanio`**: Se encarga de realizar el cálculo recursivo del tamaño total de una carpeta, recorriendo de manera explícita y separada su lista de archivos y su lista de subcarpetas.


3. **`enviarResultado`**: Se encarga de instanciar directamente un servicio de correo rígido (`CorreoLegacy`) y enviarle el mensaje con el tamaño total calculado de una carpeta.

---

## Tres problemas concretos de diseño

1. **Decisión de creación dispersa y acoplada**


   * **¿Dónde aparece?**: En el método `agregarArchivo` de la clase `Main`.
   
   
   * **¿Por qué es difícil cambiar?**: Si se agrega un nuevo tipo de archivo (por ejemplo, `ArchivoImagen`), habría que modificar directamente la estructura `if/else` dentro de `Main`.
   
   
   * **Responsable**: Se resuelve implementando el patrón **Factory Method** (`CreadorArchivo`, `CreadorPDF`, `CreadorTexto`).
   

2. **Doble lista y lógica condicional/recursiva explícita (Falta de abstracción en la estructura)**


   * **¿Dónde aparece'**: En la clase `Carpeta` (listas separadas `archivos` y `subcarpetas`) y en el método `obtenerTamanio`.
   
   
   * **¿Por qué es difícil cambiar'**: Trata a los archivos y a las carpetas de forma distinta, obligando a usar algoritmos explícitos para iterar ambos tipos por separado.
   
   
   * **Responsable**: Se resuelve mediante el patrón **Composite** (`Elemento`), permitiendo tratar componentes simples y compuestos de manera unificada.


3. **Acoplamiento fuerte a una implementación concreta de correo**


   * **¿Dónde aparece?**: En el método `enviarResultado`, donde se instancia directamente `new CorreoLegacy()`.
   
   
   * **¿Por qué es difícil cambiar?**: Si se decide cambiar de proveedor de correo o la firma del método cambia (p. ej. `send_email` a `enviar`), habría que reescribir la lógica del cliente.
   
   
   * **Responsable**: Se resuelve aplicando el patrón **Adapter** (`Notificador` y `AdaptadorCorreo`).

---

## Etapa 6: Justificación y Responsabilidades

### Responsabilidades de cada clase tras la refactorización
* **`Elemento` (Interfaz Composite)**: Define el contrato unificado (`obtenerTamanio()`) para hojas y contenedores.


* **`Archivo` (Hoja)**: Mantiene los datos del archivo y responde a `obtenerTamanio()` devolviendo su tamaño individual.


* **`Carpeta` (Contenedor/Composite)**: Mantiene una única lista de tipo `Elemento` y suma recursivamente los tamaños delegando la llamada a cada elemento de su lista.


* **`CreadorArchivo` / `CreadorPDF` / `CreadorTexto` (Factory Method)**: Encapsulan la lógica de instanciación de archivos concretos.


* **`Notificador` (Target / Interfaz)**: Interfaz esperada por el cliente para el envío de notificaciones.


* **`AdaptadorCorreo` (Adapter)**: Adapta la interfaz de `CorreoLegacy` a la interfaz `Notificador`.


* **`CorreoLegacy` (Adaptee)**: Componente legacy original sin modificaciones.

### Lo que permaneció igual para el usuario final
El comportamiento externo permanece intacto: la salida por terminal muestra el mismo cálculo de tamaño (`250`) y la salida del correo simulado mantiene la misma estructura y contenido.

---

## Diagrama de Clases

```text
       +-------------------+
       |    <<interface>>  |
       |     Elemento      |
       +-------------------+
       | +obtenerTamanio() |
       +---------+---------+
                 ^
       +---------+---------+
       |                   |
+------+------+     +------+------+
|   Archivo   |     |   Carpeta   |
+-------------+     +-------------+
| -nombre     |     | -elementos: |
| -tamanio    |     |   List<Elem>|
+-------------+     +-------------+
  ^         ^       | +agregar()  |
  |         |       +-------------+
+--+----+ +--+----+
|PDF    | |Texto  |
+-------+ +-------+

   +--------------------+
   |   CreadorArchivo   |
   +--------------------+
   | +crearArchivo()    |
   +----------+---------+
              ^
     +--------+--------+
     |                 |
+----+-----+     +-----+----+
|CreadorPDF|     |CreadorTxt|
+----------+     +----------+

+------------------+         +-----------------+         +--------------+
|  <<interface>>   | <-------| AdaptadorCorreo |-------> | CorreoLegacy |
|   Notificador    |         +-----------------+         +--------------+
+------------------+         | -correoLegacy   |         | +send_email()|
| +enviar()        |         +-----------------+         +--------------+
+------------------+