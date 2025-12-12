package Ejerciciostema3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class EjercicioE17 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("Introduce el nombre del fichero");
        String nombreFichero = teclado.nextLine();

        File archivo = new File(nombreFichero);

        if (!archivo.exists()) {
            System.out.println("El fichero no existe.");
            teclado.close();
            return;
        }

        List<String> preguntas = new ArrayList<>();
        List<String> respuestas = new ArrayList<>();

        try (Scanner lector = new Scanner(archivo)) {
            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();
                if (linea.isEmpty()) continue; 
                String[] partes = linea.split("\\|");
                if (partes.length == 2) {
                    preguntas.add(partes[0].trim());
                    respuestas.add(partes[1].trim());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            teclado.close();
            return;
        }

        if (preguntas.isEmpty()) {
            System.out.println("No se encontraron preguntas en el archivo.");
            teclado.close();
            return;
        }

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < preguntas.size(); i++) {
            indices.add(i);
        }
        Collections.shuffle(indices); 

        int aciertos = 0;


        for (int idx : indices) {
            System.out.println(preguntas.get(idx));
            System.out.print("Tu respuesta: ");
            String respuestaAlumno = teclado.nextLine().trim();

            if (respuestaAlumno.equalsIgnoreCase(respuestas.get(idx))) {
                System.out.println("Correcto!\n");
                aciertos++;
            } else {
                System.out.println("Incorrecto. La respuesta era: " + respuestas.get(idx) + "\n");
            }
        }

        int total = preguntas.size();
        double nota = (double) aciertos / total * 10; 

        System.out.println("--- RESULTADOS ---");
        System.out.println("Preguntas totales: " + total);
        System.out.println("Aciertos: " + aciertos);
        System.out.printf("Nota final: %.2f sobre 10\n", nota);

        teclado.close();
    }
}
