# Práctica 3

## Refactorización y Diseño modular

> ### Integrantes del Equipo:
> * García Herrera Valeria
> * Grajeda Palacios Dulce Abril
> * Pérez Megchun Pablo de Jesús

> ### Breve descripción de la práctica:
> Esta práctica consiste en refactorizar un programa en Java que calcula el tamaño total de carpetas y subcarpetas y envía el resultado por correo. El objetivo principal es reestructurar y mejorar la calidad del código original sin alterar su comportamiento ni los resultados visibles para el usuario.

---

 ## Patrones de Diseño Aplicados

 1. **Composite**
   - **Propósito:** Tratar archivos individuales y carpetas mediante una interfaz común (`Elemento`).
   - **Beneficio:** Simplifica el cálculo recursivo del tamaño total sin necesidad de verificar si un objeto es un archivo o una carpeta.

 2. **Factory Method**
   - **Propósito:** Encapsular y delegar la creación de los distintos tipos de archivos (`ArchivoPDF`, `ArchivoTexto`) a creadores especializados (`CreadorArchivo`).
   - **Beneficio:** Desacopla la clase principal (`Main`) de la instanciación directa de objetos.

 3. **Adapter**
   - **Propósito:** Conectar el sistema con un servicio de correo simulado (`CorreoLegacy`).
   - **Beneficio:** Permite que el cliente trabaje contra una interfaz genérica (`Notificador`) sin depender de los nombres de métodos específicos del componente de correo.
---

 ## Estrategia de Trabajo
- **Pruebas Unitarias Iniciales:** Implementación de comprobaciones sencillas para verificar casos borde (carpetas vacías, subcarpetas, archivos con tamaño 0) antes de refactorizar.
- **Evolución Gradual:** Aplicación de los cambios paso a paso para asegurar que las pruebas sigan pasando después de cada modificación.
- **Validación Final:** Verificación del total de tamaño obtenido (250) y del mensaje de correo simulado.

---

## Requisitos
- Java JDK 25 o superior (o configurar el flag `--release`).
- Git (para la clonación del repositorio).

---

## Instrucciones para ejecutar el programa:

* Clonar el repositorio remoto e ingresar a la carpeta del proyecto:

  `git clone https://github.com/valeriagh-star/Practica-3.git`

  `cd Practica-3`

* Crear carpeta bin y compilar:

  `mkdir -p bin`
  
  `javac --release 25 -d bin $(find src -name "*.java")`
  
* Ejecutar la clase `Main.java`:

  `java -cp bin main.Main`

---

## Salida Esperada

```text
--- EJECUTANDO PRUEBAS ---
OK: Carpeta vacia
OK: Carpeta con un PDF de 120
OK: Carpeta con PDF de 120 y texto de 80
OK: Ejemplo completo con subcarpeta de 50
OK: Carpeta con archivo de tamaño 0
---------------------------

250
Para: profesor@universidad.edu
Tamanio total: 250