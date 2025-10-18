package net.salesianos.proceso;
import java.io.*;
import java.util.*;

public class Principal {

    public static void main(String[] args) {
        try {
            File input = new File("src/net/salesianos/ficherito/notas.txt");
            if (!input.exists()) {
                System.err.println("Error: No se encuentra el archivo de notas.");
                return;
            }

            // Mostrar todos los nombres de estudiantes
            System.out.println("=== Lista de Estudiantes ===");
            List<String> estudiantes = obtenerEstudiantes(input);
            for (String estudiante : estudiantes) {
                System.out.println("- " + estudiante);
            }

            // Solicitar nombre del estudiante
            String nombreEstudiante;
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.println("\nEscriba el nombre del estudiante para ver sus notas:");
                nombreEstudiante = scanner.nextLine();
            }

            if (!estudiantes.contains(nombreEstudiante)) {
                System.out.println("Error: Estudiante no encontrado.");
                return;
            }

            File dirResultados = new File("resultados");
            if (!dirResultados.exists()) dirResultados.mkdir();

            // Crear procesos para el estudiante específico
            ProcessBuilder pbMedia = new ProcessBuilder("java", "-cp", "bin", "net.salesianos.subprocesos.medias.CalculateMedia", input.getPath(), nombreEstudiante);
            pbMedia.redirectOutput(new File("resultados/media.txt"));

            ProcessBuilder pbMaximo = new ProcessBuilder("java", "-cp", "bin", "net.salesianos.subprocesos.nmax.HighNote", input.getPath(), nombreEstudiante);
            pbMaximo.redirectOutput(new File("resultados/maximo.txt"));

            ProcessBuilder pbMinimo = new ProcessBuilder("java", "-cp", "bin", "net.salesianos.nmin.MinNote", input.getPath(), nombreEstudiante);
            pbMinimo.redirectOutput(new File("resultados/minimo.txt"));

            // Ejecutar procesos
            Process p1 = pbMedia.start();
            Process p2 = pbMaximo.start();
            Process p3 = pbMinimo.start();

            // Esperar a que terminen
            p1.waitFor();
            p2.waitFor();
            p3.waitFor();

            // Mostrar resultados
            System.out.println("\n=== Resultados para " + nombreEstudiante + " ===");
            mostrarResultado("Nota Media", "resultados/media.txt");
            mostrarResultado("Nota Máxima", "resultados/maximo.txt");
            mostrarResultado("Nota Mínima", "resultados/minimo.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
