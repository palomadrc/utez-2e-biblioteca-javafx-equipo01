# utez-2e-biblioteca-javafx-equipo01
Biblioteca Tarea Integradora 

Este proyecto es una aplicación de escritorio desarrollada en **JavaFX** que permite gestionar una biblioteca escolar.  
El sistema ofrece funcionalidades para **crear, editar, eliminar, buscar y visualizar libros**, además de generar reportes en formato `.txt`.

---

##  Funcionalidades principales

- **Menú principal**: acceso a las diferentes vistas (formulario, detalles, tabla).
- **Formulario (FormController)**:
  - Crear nuevos libros.
  - Editar libros existentes.
  - Validaciones:
    - Campos obligatorios no vacíos.
    - Mínimo 3 caracteres en texto.
    - Año numérico y mayor a 1500.
    - Selección obligatoria de género y disponibilidad.
- **Detalles (DetailsController)**:
  - Visualizar información de un libro en modo solo lectura.
  - Botón para editar y regresar al menú.
- **Tabla principal (MainController)**:
  - Mostrar todos los libros en una `TableView`.
  - Buscar libro por ID.
  - Eliminar libros con confirmación.
  - Generar reporte de todos los libros en un archivo `.txt`.

---

##  Estructura del proyecto

- **Model**
  - `Book`: clase que representa un libro con atributos como `ISBN`, `autor`, `año`, `título`, `editorial`, `disponibilidad`, `género`.
  - Métodos para convertir a CSV y reconstruir desde CSV.

- **Repository**
  - `BookRepo`: maneja la persistencia en archivo `books.csv`.
  - Métodos: `readAllLines`, `appendLine`, `saveAll`, `update`.

- **Controllers**
  - `Main`: controlador de la tabla y menú principal.
  - `Form`: controlador del formulario de creación/edición.
  - `Details`: controlador de la vista de detalles.

---

##  Validaciones importantes

- **Campos vacíos**: no se permite guardar si algún campo está vacío.
- **Mínimo de caracteres**: cada campo debe tener al menos 3 caracteres.
- **Año numérico**: debe ser un número entero.
- **Año válido**: mayor a 1500.
- **Combos obligatorios**: género y disponibilidad deben seleccionarse.

---

##  Tecnologías utilizadas

- **Java 21**
- **JavaFX**
- **FXML**
- **Streams y Lambdas** para validaciones y manipulación de listas.
- **Alertas (Alert)** para mostrar mensajes de error y éxito.

---

##  Archivos importantes

- `books.csv`: archivo donde se guardan los registros de libros.
- `menu-view.fxml`: vista principal del menú.
- `form-view.fxml`: formulario de creación/edición.
- `details-view.fxml`: vista de detalles.

---

##  Ejemplo de uso

1. Abrir la aplicación.
2. Desde el menú principal, ir al formulario.
3. Ingresar los datos del libro y guardar.
4. El libro aparecerá en la tabla principal.
5. Se puede buscar por ID, editar o eliminar.
6. Generar un reporte en `Downloads/report_fecha.txt`.

---
