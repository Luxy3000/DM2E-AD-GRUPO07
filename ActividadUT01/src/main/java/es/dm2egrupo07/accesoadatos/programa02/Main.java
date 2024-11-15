package es.dm2egrupo07.accesoadatos.programa02;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

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
            List<CityWeather> cities = reader.getCities();
            List<CityStatistics> statistics = WeatherStatistics.calculateStatistics(cities);

            String outputPath = reportGenerator.pedirUbicacionSalida();
            if (outputPath != null) {
                reportGenerator.generateReport(statistics, outputPath);
            }

        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}