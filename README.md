```
   ______           _                  ____                    __   
  / ____/___ ______(_)___  ____       / __ \____  __  ______ _/ /__ 
 / /   / __ `/ ___/ / __ \/ __ \     / /_/ / __ \/ / / / __ `/ / _ \
/ /___/ /_/ (__  ) / / / / /_/ /    / _, _/ /_/ / /_/ / /_/ / /  __/
\____/\__,_/____/_/_/ /_/\____/    /_/ |_|\____/\__, /\__,_/_/\___/ 
                                               /____/                                                      |___/                                      
``` 

Casino Royale es una aplicación en Java que simula el funcionamiento de un casino virtual. 
El proyecto hace uso de múltiples patrones de diseño para lograr un sistema modular y extensible.


## 📂 Repositorio

Puedes clonar el repositorio oficial:
````
    git clone https://github.com/kur0h3i/Casino-Patterns.git
    cd Casino-Patterns
````

## 🚀 Tecnologías y Herramientas
- Lenguaje: Java 8+
- Control de versiones: Git + GitHub
- Patrones de diseño implementados: Strategy, Observer, Factory, Singleton, Facade, Template

## 🗺️ Estructura del Proyecto
````
src/
├── acciones/     # Clases que gestionan las acciones del jugador y eventos
├── ascii/        # Recursos de arte ASCII para la presentación en consola
├── excep/        # Definición de excepciones personalizadas
├── items/        # Objetos que el jugador puede recoger o usar
├── juegos/       # Lógica de los distintos juegos (dados, ruleta, etc.)
├── mapas/        # Definiciones de mapas y niveles del casino
├── patterns/     # Implementación de patrones de diseño
│   ├── observer/ # Patrón Observer para notificaciones entre Jugador y elementos
│   └── template/ # Patrón Template para flujo de cambio de salas
├── personas/     # Clases relacionadas con entidades del juego (Jugador, NPCs)
├── recursos/     # Ficheros de configuración, imágenes y otros recursos
└── salas/        # Definición de salas y su comportamiento específico        
````

## 🎲 Patrones de Diseño
- *Strategy*: Para encapsular diferentes estrategias de cálculo de ganancias.

- *Observer*: Para notificaciones de estado entre el jugador y elementos del entorno.

- *Factory*: Para la creación de mesas de juego según el tipo.

- *Singleton*: Para gestión global de estadísticas del casino.

- *Facade*: Para ofrecer una interfaz sencilla de inicialización y cambio de salas.

- *Template*: Para definir el algoritmo estándar de transición entre salas.

