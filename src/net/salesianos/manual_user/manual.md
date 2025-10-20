# Manual de Usuario - Gestor de Notas de Estudiantes

## Índice

1. [Introducción](#introducción)
2. [Requisitos del Sistema](#requisitos-del-sistema)
3. [Instalación](#instalación)
4. [Uso del Programa](#uso-del-programa)
5. [Funcionalidades](#funcionalidades)
6. [Solución de Problemas](#solución-de-problemas)

## Introducción

Este programa permite gestionar y analizar las notas de estudiantes, permitiendo consultar notas individuales, medias, máximas y mínimas para cada estudiante.

## Requisitos del Sistema

- Java Runtime Environment (JRE) 8 o superior
- Sistema operativo: Windows, Linux o macOS
- 50MB de espacio libre en disco
- 1GB de RAM mínimo

## Instalación

1. Asegúrate de tener Java instalado en tu sistema
2. Descarga el proyecto completo
3. Navega hasta la carpeta del proyecto
4. Compila el proyecto usando el comando:
   ```
   javac -d bin src/net/salesianos/proceso/Principal.java src/net/salesianos/subprocesos/medias/CalculateMedia.java src/net/salesianos/subprocesos/nmax/HighNote.java src/net/salesianos/nmin/MinNote.java
   ```

## Uso del Programa

1. Abre una terminal o línea de comandos
2. Navega hasta la carpeta del proyecto
3. Ejecuta el programa con el comando:
   ```
   java -cp bin net.salesianos.proceso.Principal
   ```

## Funcionalidades

### 1. Lista de Estudiantes

Al iniciar el programa, verás una lista completa de todos los estudiantes disponibles:

```
=== Lista de Estudiantes ===
- Ana
- Luis
- María
...
```

### 2. Consulta de Notas

1. Escribe el nombre exacto del estudiante cuando se te solicite
2. El programa mostrará:
   - Las notas individuales de cada evaluación
   - La nota media del estudiante
   - La nota más alta
   - La nota más baja

Ejemplo de salida:

```
=== Notas de Ana ===
Evaluación 1: 7.5
Evaluación 2: 8.0
Evaluación 3: 6.5
Evaluación 4: 9.0
Evaluación 5: 7.8

=== Estadísticas de Ana ===
Nota Media: 7.76
Nota Máxima: 9.0
Nota Mínima: 6.5
```

### 3. Continuar o Salir

Después de cada consulta, el programa preguntará si deseas:

- Consultar otro estudiante (S/SI)
- Salir del programa (N/cualquier otra tecla)

## Solución de Problemas

### Errores Comunes

1. "Error: No se encuentra el archivo de notas"

   - Verifica que el archivo notas.txt existe en la carpeta src/net/salesianos/ficherito/

2. "Error: Estudiante no encontrado"

   - Asegúrate de escribir el nombre exactamente como aparece en la lista
   - Respeta mayúsculas y minúsculas

3. "Error ejecutando proceso"
   - Verifica que todos los archivos .class están en la carpeta bin
   - Recompila el proyecto

### Contacto y Soporte

Si encuentras algún problema o necesitas ayuda adicional:

1. Revisa la documentación en el README del proyecto
2. Abre un issue en el repositorio de GitHub
3. Contacta con el equipo de desarrollo

## Notas Importantes

- El programa es sensible a mayúsculas y minúsculas en los nombres
- Los resultados se guardan temporalmente en la carpeta "resultados"
- El archivo de notas debe mantener el formato: "Nombre: nota1 nota2 nota3..."

## Historial de Versiones

- v1.0: Versión inicial con funcionalidades básicas
- v1.1: Agregada la opción de continuar/salir
- v1.2: Mejorada la presentación de resultados

---

_Manual actualizado: Octubre 2025_
