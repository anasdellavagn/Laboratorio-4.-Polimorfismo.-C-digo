# Laboratorio-4.-Polimorfismo.-C-digo
Lab 4 POO 
Universidad del Valle de Guatemala
Curso: CC2008 – Programación Orientada a Objetos
Laboratorio 4: Polimorfismo
Lenguaje: Java

# Descripción del proyecto

ArenaGT Lite es un programa desarrollado en Java que permite administrar una copa deportiva entre empresas. El sistema registra equipos, encuentros de fútbol sala y baloncesto, exhibiciones, resultados y puntos acumulados.

El programa utiliza programación orientada a objetos, herencia, interfaces, clases abstractas y polimorfismo.

# Funcionalidades

- Registrar equipos.
- Crear encuentros de fútbol sala y baloncesto.
- Registrar exhibiciones.
- Programar y reprogramar eventos.
- Registrar resultados deportivos.
- Mostrar la tabla de posiciones.
- Consultar el calendario ordenado.
- Validar entradas incorrectas y conflictos de programación.

# Estructura del proyecto

Los archivos Java se encuentran en la carpeta `src`.

- `Main.java`: Contiene el menú principal y permite interactuar con el programa.
- `Copa.java`: Administra los equipos y eventos de la copa.
- `Equipo.java`: Guarda la información de los equipos y sus puntos.
- `Encuentro.java`: Clase abstracta que contiene las características comunes de los encuentros.
- `FutbolSala.java`: Define las reglas y puntuaciones del fútbol sala.
- `Baloncesto.java`: Define las reglas y puntuaciones del baloncesto.
- `Exhibicion.java`: Representa las actividades que no otorgan puntos.
- `Programable.java`: Interfaz que permite programar eventos.
- `Puntuable.java`: Interfaz que permite registrar resultados.
- `ComparadorCalendario.java`: Ordena los eventos por fecha, hora y sede.
- `EstadoEncuentro.java`: Define los estados de los encuentros.
- `EstadoExhibicion.java`: Define los estados de las exhibiciones.

# Conceptos de POO

## Herencia

Las clases `FutbolSala` y `Baloncesto` heredan de la clase abstracta `Encuentro`, compartiendo atributos y métodos.

## Polimorfismo

El programa utiliza polimorfismo para manejar diferentes deportes y eventos sin depender directamente de sus clases concretas.

## Interfaces

Se utilizan dos interfaces principales:

- `Programable`: Define las operaciones relacionadas con la programación de eventos.
- `Puntuable`: Define las operaciones relacionadas con los resultados deportivos.

## Clase abstracta

La clase `Encuentro` contiene los atributos y comportamientos compartidos por los encuentros deportivos, mientras que sus subclases implementan las reglas específicas de cada deporte.

# Datos iniciales

El programa inicia con información cargada automáticamente:

- Cuatro equipos de dos empresas diferentes.
- Cuatro encuentros deportivos.
- Encuentros de fútbol sala y baloncesto.
- Resultados registrados previamente.
- Una exhibición programada.

Estos datos permiten observar las reglas de puntuación y el orden de la tabla de posiciones.

# Reglas de puntuación

## Fútbol sala

- Victoria: 3 puntos.
- Empate: 1 punto para cada equipo.
- Derrota: 0 puntos.

## Baloncesto

- Victoria: 2 puntos.
- Derrota: 1 punto.
- No se permiten empates.

# Validaciones del sistema
El programa verifica las siguientes condiciones:

- No permite registrar equipos con códigos repetidos o vacíos.
- No permite crear encuentros de un equipo contra sí mismo.
- Rechaza marcadores negativos.
- No permite empates en baloncesto.
- Verifica que las sedes estén disponibles.
- Evita que un equipo tenga dos encuentros simultáneos.
- No permite registrar resultados en encuentros sin programar.
- No permite modificar resultados de encuentros finalizados.
- Valida que las horas estén entre 0 y 23.
- Muestra mensajes de error cuando se ingresan datos incorrectos.

# Calendario

El calendario muestra los encuentros y exhibiciones que ya están programados.

Los eventos se organizan según:

1. Fecha.
2. Hora.
3. Nombre de la sede.

# Pruebas realizadas
Se realizaron pruebas para comprobar el funcionamiento del programa, incluyendo:

- Registro de equipos.
- Creación de encuentros y exhibiciones.
- Programación de eventos.
- Registro de resultados.
- Empates en fútbol sala.
- Validación de empates en baloncesto.
- Orden de la tabla de posiciones.
- Orden cronológico del calendario.
- Manejo de entradas incorrectas.

Las evidencias y resultados de las pruebas se presentan en el documento PDF del laboratorio.
