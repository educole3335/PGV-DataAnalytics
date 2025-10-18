package net.salesianos.proceso;

import java.io.*;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            try {
                File input = new File("src/net/salesianos/ficherito/notas.txt");
                if (!input.exists()) {
                    System.err.println("Error: No se encuentra el archivo de notas.");
                    break;
                }

                // Mostrar todos los nombres de estudiantes
                System.out.println("\n=== Lista de Estudiantes ===");
                List<String> estudiantes = obtenerEstudiantes(input);
                for (String estudiante : estudiantes) {
                    System.out.println("- " + estudiante);
                }

                // Solicitar nombre del estudiante
                System.out.println("\nEscriba el nombre del estudiante para ver sus notas:");
                String nombreEstudiante = scanner.nextLine();

                if (!estudiantes.contains(nombreEstudiante)) {
                    System.out.println("Error: Estudiante no encontrado.");
                } else {

                    File dirResultados = new File("resultados");
                    if (!dirResultados.exists())
                        dirResultados.mkdir();

                    // Crear procesos para el estudiante específico
                    ProcessBuilder pbMedia = new ProcessBuilder("java", "-cp", "bin",
                            "net.salesianos.subprocesos.medias.CalculateMedia", input.getPath(), nombreEstudiante);
                    pbMedia.redirectOutput(new File("resultados/media.txt"));

                    ProcessBuilder pbMaximo = new ProcessBuilder("java", "-cp", "bin",
                            "net.salesianos.subprocesos.nmax.HighNote", input.getPath(), nombreEstudiante);
                    pbMaximo.redirectOutput(new File("resultados/maximo.txt"));

                    ProcessBuilder pbMinimo = new ProcessBuilder("java", "-cp", "bin", "net.salesianos.nmin.MinNote",
                            input.getPath(), nombreEstudiante);
                    pbMinimo.redirectOutput(new File("resultados/minimo.txt"));

                    // Ejecutar procesos
                    Process p1 = pbMedia.start();
                    Process p2 = pbMaximo.start();
                    Process p3 = pbMinimo.start();

                    // Esperar a que terminen
                    p1.waitFor();
                    p2.waitFor();
                    p3.waitFor();

                    // Mostrar notas individuales
                    System.out.println("\n=== Notas de " + nombreEstudiante + " ===");
                    mostrarNotasIndividuales(input, nombreEstudiante);

                    // Mostrar resultados estadísticos
                    System.out.println("\n=== Estadísticas de " + nombreEstudiante + " ===");
                    mostrarResultado("Nota Media", "resultados/media.txt");
                    mostrarResultado("Nota Máxima", "resultados/maximo.txt");
                    mostrarResultado("Nota Mínima", "resultados/minimo.txt");
                }

                // Preguntar si desea continuar
                System.out.print("\n¿Deseas consultar otro estudiante? (S/N): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();
                continuar = respuesta.equals("S") || respuesta.equals("SI");

                if (!continuar) {
                    System.out.println("\nGracias por usar el programa. ¡Hasta pronto!");
                }

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                System.out.print("\n¿Deseas intentar de nuevo? (S/N): ");
                String respuesta = scanner.nextLine().trim().toUpperCase();
                continuar = respuesta.equals("S") || respuesta.equals("SI");
            }
        }
        scanner.close();
    }

    private static List<String> obtenerEstudiantes(File archivo) {
        List<String> estudiantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.contains(":")) {
                    String nombre = linea.split(":")[0].trim();
                    estudiantes.add(nombre);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo de notas: " + e.getMessage());
        }
        return estudiantes;
    }

    private static void mostrarResultado(String titulo, String fichero) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String valor = br.readLine();
            System.out.println(titulo + ": " + valor);
        } catch (IOException e) {
            System.err.println("Error leyendo " + fichero);
        }
    }

    private static void mostrarNotasIndividuales(File archivo, String estudiante) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(estudiante + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String[] notas = partes[1].trim().split(" ");
                        for (int i = 0; i < notas.length; i++) {
                            System.out.println("Evaluación " + (i + 1) + ": " + notas[i]);
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo las notas individuales: " + e.getMessage());
        }
    }
}
