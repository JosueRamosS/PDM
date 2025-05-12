# Juego de Colores

Este repositorio contiene una aplicación Android desarrollada en Kotlin que implementa un juego interactivo de colores. La app desafía al usuario a pulsar el botón que coincide con el color mostrado en pantalla durante 30 segundos.

---

## Requisitos Previos

- **Sistema Operativo donde se desarrolló:** macOS
- **IDE:** Android Studio (versión recomendada 2022.3.1 o superior)
- **JDK:** Java Development Kit 11 o superior
- **Gradle:** La configuración ya está incluida en el proyecto

---

## Cómo Clonar el Repositorio

1. Abre la terminal en tu pc.
2. Ejecuta el siguiente comando para clonar el repositorio:

   ```bash
   git clone https://github.com/JosueRamosS/PDM.git
   ```

3. Accede al directorio del proyecto:

   ```bash
   cd ExamenParcial/colorsgame
   ```

---

## Abrir y Ejecutar el Proyecto

1. **Abrir en Android Studio:**

   - Inicia Android Studio.
   - Selecciona **"Open an Existing Project"** y navega hasta el directorio clonado.
   - Android Studio sincronizará el proyecto y descargará las dependencias necesarias.
   - Puedes utilizar Clean Project y Rebuild a modo de previsión.

2. **Ejecutar la Aplicación:**
   - Conecta un dispositivo Android o inicia un Emulador desde Android Studio.
   - Haz clic en el botón **"Run"** (ícono de play) en la barra de herramientas o usa el atajo **Shift + F10**.
   - La aplicación se compilará y se instalará en el dispositivo/emulador.

---

# Estructura del Proyecto

## Tree Relevante del Proyecto

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/colorsgame/
│   │   │   ├── fragments/
│   │   │   │   ├── WelcomeFragment.kt
│   │   │   │   ├── GameFragment.kt
│   │   │   │   └── ResultFragment.kt
│   │   │   ├── utils/
│   │   │   │   └── ColorGameUtils.kt
│   │   │   └── MainActivity.kt
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── ic_launcher_background.xml
│   │   │   │   └── ic_launcher_foreground.xml
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── fragment_welcome.xml
│   │   │   │   ├── fragment_game.xml
│   │   │   │   └── fragment_result.xml
│   │   │   ├── navigation/
│   │   │   │   └── nav_graph.xml
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   ├── themes.xml
│   │   │   │   └── styles.xml
│   │   │   └── raw/
│   │   │       ├── correct_sound.mp3
│   │   │       ├── wrong_sound.mp3
│   │   │       └── game_over_sound.mp3
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── build.gradle
```

El proyecto sigue la arquitectura recomendada usando fragments y Navigation Component para gestionar la navegación.

### Paquetes

- `com.example.colorsgame`  
  Paquete principal con la actividad principal.

- `com.example.colorsgame.fragments`  
  Contiene los tres fragmentos del juego.

- `com.example.colorsgame.utils`  
  Utilidades para el juego.

---

### Archivos

#### Actividad Principal

- `MainActivity.kt`  
  Aloja y gestiona la navegación entre los fragmentos.

#### Fragmentos

- `WelcomeFragment.kt`  
  Pantalla de bienvenida y selección de dificultad.

- `GameFragment.kt`  
  Lógica y visualización del juego.

- `ResultFragment.kt`  
  Muestra los resultados y permite reiniciar el juego.

#### Utilidades

- `ColorGameUtils.kt`  
  Contiene funciones y constantes útiles para el desarrollo del juego.

---

### Recursos

#### Layouts (`res/layout`)

- `activity_main.xml`  
  Layout de la actividad principal.

- `fragment_welcome.xml`  
  Layout de la pantalla de bienvenida.

- `fragment_game.xml`  
  Layout del juego.

- `fragment_result.xml`  
  Layout de la pantalla de resultados.

#### Navegación (`res/navigation`)

- `nav_graph.xml`  
  Define la navegación entre fragmentos utilizando Navigation Component.

#### Valores (`res/values`)

- `colors.xml`  
  Define los colores utilizados en la interfaz del juego.

- `strings.xml`  
  Contiene las cadenas de texto de la aplicación.

#### Sonidos (`res/raw`)

- `correct_sound.mp3`  
  Sonido que se reproduce al acertar.

- `wrong_sound.mp3`  
  Sonido que se reproduce al fallar.

- `game_over_sound.mp3`  
  Sonido que indica el fin del juego.

---

# CURSO

📱 _Programación de Dispositivos Móviles_

# DOCENTE

👨‍🏫 _Josué Miguel Flores Parra_

# AUTOR

🧑‍💻 _Josué Carlos Alberto Ramos Suyoc
11/05/2025_
