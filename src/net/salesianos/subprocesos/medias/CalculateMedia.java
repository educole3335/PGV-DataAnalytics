package net.salesianos.subprocesos.medias;
import java.io.*;
import java.util.*;


public class CalculateMedia {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java CalculateMedia <fichero_notas> <nombre_estudiante>");
            return;
        }

        String fichero = args[0];
        String estudiante = args[1];
        double suma = 0;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(estudiante + ":")) {
                    String[] partes = linea.split(":");
                    if (partes.length == 2) {
                        String[] notas = partes[1].trim().split(" ");
                        for (String nota : notas) {
                            suma += Double.parseDouble(nota);
                            count++;
                        }
                    }
                    break;
                }
            }
            double media = (count > 0) ? suma / count : 0;
            System.out.println(String.format("%.2f", media));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


