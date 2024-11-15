package es.dm2egrupo07.accesoadatos.programa02;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal para ejecutar el programa.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherDataReader reader = new WeatherDataReader();
        ReportGenerator reportGenerator = new ReportGenerator();

        try {
            Path inputPath = reader.preguntarUbicacionXML();
            if (inputPath == null) {
                System.out.println("El programa se cerrará.");
                return;
            }

            reader.leerFichero(inputPath);
            List<CityStatistics> cityStatistics = reader.getCitiesStatistics();

            System.out.println("¿Dónde desea guardar el informe?");
            String outputPath = scanner.nextLine();

            reportGenerator.generateReport(cityStatistics, outputPath);

        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}