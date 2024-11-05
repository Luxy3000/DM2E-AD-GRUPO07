package es.dm2egrupo07.accesoadatos.programa01;


import java.io.File;
import java.util.Scanner;

/**
 * Clase creada para generar el archivo XML de salina y gestionar la ubicación para el archivo de salida
 */
public class WeatherDataWriter {

    public boolean ubicacionXmlSalida(String ruta) {
        File file = new File(ruta);
        Scanner scanner = new Scanner(System.in);

        if (file.exists()) {
            System.out.println("El archivo ya existe: " + ruta);
            System.out.print("¿Quieres sobrescribirlo? (si/no): ");
            String response = scanner.nextLine().toLowerCase();

            if (response.equals("si")) {
                return true;
            } else {
                System.out.println("El programa terminará.");
                return false;
            }
        } else {
            return true;
        }
    }
}
