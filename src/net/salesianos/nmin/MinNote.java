package net.salesianos.nmin;
import java.io.*;
import java.util.*;
public class MinNote {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java MinNote <fichero_notas> <nombre_estudiante>");
            return;
        }

        String fichero = args[0];
        String estudiante = args[1];
        double min = Double.MAX_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(estudiante + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String[] notas = partes[1].trim().split(" ");
                        for (String nota : notas) {
                            double valor = Double.parseDouble(nota);
                            if (valor < min) min = valor;
                        }
                    }
                    break;
                }
            }
            System.out.println(String.format("%.2f", min));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}


