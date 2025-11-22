# 🌊 Batalla Naval: Duelo en el Mar

Una aplicación de escritorio de combate por turnos, donde la marina española se enfrenta a los piratas más temidos en una épica batalla 1 contra 1. ¿Quién se alzará con el control del mar?



## 🎮 Características

-   **Sistema de Combate por Turnos:** Un duelo estratégico basado en estadísticas.
-   **Estadísticas Únicas:** Cada combatiente tiene sus propios puntos de **Fuerza**, **Agilidad** y **Suerte** que influyen en el resultado de cada ataque.
-   **Interfaz Gráfica Temática:** Una interfaz visualmente atractiva y temática con paneles diferenciados para España y los Piratas.
-   **Combate Dinámico:** El resultado de cada ataque se calcula con una fórmula que combina las estadísticas y un toque de suerte (RNG).
-   **Barras de Vida Animadas:** Visualiza el daño en tiempo real con barras de vida que se reducen con cada impacto.
-   **Menú de Opciones:** Salir de forma cómoda o jugar en modo pantalla completa.
-   **Rejugabilidad:** Botón de reinicio para empezar una nueva batalla al instante.

## 🚀 Cómo Empezar

Sigue estas instrucciones para tener una copia del proyecto funcionando en tu máquina local.

### Prerrequisitos

-   **JDK 17** o una versión superior.
-   Un IDE como **IntelliJ IDEA** (recomendado) o Eclipse.
-   **Maven** (normalmente viene integrado en los IDEs modernos).

### Instalación

1.  **Clona el repositorio:**
    ```bash
    git clone https://github.com/JoseGlezHerrera/BatallaNaval.git
    ```

2.  **Abre el proyecto en tu IDE:**
    -   En IntelliJ IDEA, selecciona `File` -> `Open` y busca la carpeta del proyecto (`BatallaNaval`).
    -   IntelliJ detectará que es un proyecto Maven y te preguntará si quieres importarlo. Acepta.

3.  **Añade los recursos gráficos (¡Importante!):**
    -   El proyecto necesita dos imágenes para los combatientes.
    -   Asegúrate de que los archivos `espana.png` y `pirata.png` se encuentran dentro de la carpeta `src/main/resources/`.
    -   Puedes usar tus propias imágenes o buscar unas que te gusten.

4.  **Ejecuta la aplicación:**
    -   Navega hasta el archivo `src/main/java/com/proyecto/Main.java`.
    -   Haz clic derecho sobre el archivo y selecciona `Run 'Main.main()'`.

¡Y listo! El juego debería iniciarse y estar listo para la batalla.

## 🕹️ Cómo Jugar

1.  Al iniciar la partida, verás los stats de ambos combatientes.
2.  Pulsa el botón **"¡DISPARAR!"** para lanzar tu ataque.
3.  El resultado se calcula comparando tu tirada de ataque (basada en tu Fuerza) con la tirada de defensa del enemigo (basada en su Agilidad).
4.  Después de tu ataque, el pirata contraatacará automáticamente.
5.  El combate continúa hasta que las vidas de uno de los dos combatientes lleguen a 0.
6.  ¡El primero en derrotar a su oponente gana!
7.  Usa el botón **"REINICIAR"** para empezar una nueva partida en cualquier momento.

## 🛠️ Tecnologías Utilizadas

-   **Java 17:** Lenguaje de programación principal.
-   **JavaFX:** Framework para la creación de la interfaz gráfica de usuario.
-   **Maven:** Herramienta de gestión de dependencias y construcción del proyecto.
-   **FXML:** Lenguaje basado en XML para definir la estructura de la interfaz.
-   **CSS:** Hojas de estilo para personalizar el aspecto visual de la aplicación.

## 📜 Licencia

Este proyecto está bajo la Licencia MIT. Siéntete libre de usarlo, modificarlo y distribuirlo como quieras.

## 👨‍💻 Autor

**Jose González**.

---
