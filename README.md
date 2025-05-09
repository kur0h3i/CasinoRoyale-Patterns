```
    ________  ______                     ______           _             
   / ____/ / / ____/________ _____      / ____/___ ______(_)___  ____   
  / __/ / / / / __/ ___/ __ `/ __ \    / /   / __ `/ ___/ / __ \/ __ \  
 / /___/ / / /_/ / /  / /_/ / / / /   / /___/ /_/ (__  ) / / / / /_/ /  
/_____/_/  \____/_/   \__,_/_/ /_/    \____/\__,_/____/_/_/ /_/\____/   
    ____            __  ___            __             _                 
   / __ \___       /  |/  /___  ____  / /____  ____  (_)___  ____ ______
  / / / / _ \     / /|_/ / __ \/ __ \/ __/ _ \/ __ \/ / __ \/ __ `/ ___/
 / /_/ /  __/    / /  / / /_/ / / / / /_/  __/ /_/ / / / / / /_/ / /    
/_____/\___/    /_/  /_/\____/_/ /_/\__/\___/ .___/_/_/ /_/\__,_/_/     
                                           /_/                          
``` 

Casino Royale es una aplicación en Java que simula el funcionamiento de un casino virtual. El proyecto hace uso de múltiples patrones de diseño para lograr un sistema modular, extensible y fácil de mantener.


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

