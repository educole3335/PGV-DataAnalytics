package net.salesianos.subprocesos.nmax;

import java.io.*;
import java.util.*;

public class HighNote {



    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java Maximo <fichero_notas>");
            return;
        }

        String fichero = args[0];
        double max = Double.MIN_VALUE;

        try (Scanner sc = new Scanner(new File(fichero))) {
            while (sc.hasNext()) {
                sc.next(); // nombre
                if (sc.hasNextDouble()) {
                    double nota = sc.nextDouble();
                    if (nota > max) max = nota;
                }
            }
            System.out.println(max);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

