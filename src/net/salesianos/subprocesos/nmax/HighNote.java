package net.salesianos.subprocesos.nmax;

import java.io.*;
import java.util.*;

public class HighNote {



    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java HighNote <fichero_notas> <nombre_estudiante>");
            return;
        }

        String fichero = args[0];
        String estudiante = args[1];
        double max = Double.MIN_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(estudiante + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String[] notas = partes[1].trim().split(" ");
                        for (String nota : notas) {
                            double valor = Double.parseDouble(nota);
                            if (valor > max) max = valor;
                        }
                    }
                    break;
                }
            }
            System.out.println(String.format("%.2f", max));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

