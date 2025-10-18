package net.salesianos.proceso;
import java.io.*;

public class Principal {

    public static void main(String[] args) {
        try {
            
            File input = new File("notas.txt");
            File dirResultados = new File("resultados");
            if (!dirResultados.exists()) dirResultados.mkdir();

            
            ProcessBuilder pbMedia = new ProcessBuilder("java", "Media", input.getPath());
            pbMedia.redirectOutput(new File("resultados/media.txt"));

            ProcessBuilder pbMaximo = new ProcessBuilder("java", "Maximo", input.getPath());
            pbMaximo.redirectOutput(new File("resultados/maximo.txt"));

            ProcessBuilder pbMinimo = new ProcessBuilder("java", "Minimo", input.getPath());
            pbMinimo.redirectOutput(new File("resultados/minimo.txt"));

            
            Process p1 = pbMedia.start();
            Process p2 = pbMaximo.start();
            Process p3 = pbMinimo.start();

            
            p1.waitFor();
            p2.waitFor();
            p3.waitFor();

            
            System.out.println("=== Resultados del análisis de notas ===");
            mostrarResultado("Media", "resultados/media.txt");
            mostrarResultado("Máxima", "resultados/maximo.txt");
            mostrarResultado("Mínima", "resultados/minimo.txt");

        } catch (Exception e) {
            e.printStackTrace();
        }
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
