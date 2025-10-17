package net.salesianos.nmin;
import java.io.*;
import java.util.*;
public class MinNote {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Uso: java Minimo <fichero_notas>");
            return;
        }

        String fichero = args[0];
        double min = Double.MAX_VALUE;

        try (Scanner sc = new Scanner(new File(fichero))) {
            while (sc.hasNext()) {
                sc.next(); // nombre
                if (sc.hasNextDouble()) {
                    double nota = sc.nextDouble();
                    if (nota < min) min = nota;
                }
            }
            System.out.println(min);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


