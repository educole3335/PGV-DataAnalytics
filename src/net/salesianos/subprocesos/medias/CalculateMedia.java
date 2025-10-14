package net.salesianos.subprocesos.medias;
import java.io.*;
import java.util.*;


public class CalculateMedia {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java Media <fichero_notas>");
            return;
        }

        String fichero = args[0];
        double suma = 0;
        int count = 0;

        try (Scanner sc = new Scanner(new File(fichero))) {
            while (sc.hasNext()) {
                sc.next(); 
                if (sc.hasNextDouble()) {
                    suma += sc.nextDouble();
                    count++;
                }
            }
            double media = (count > 0) ? suma / count : 0;
            System.out.println(String.format("%.2f", media));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


