package es.dm2egrupo07.accesoadatos.programa02;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

/**
 * Clase encargada de generar un informe en formato de texto.
 */
public class ReportGenerator {

    /**
     * Pregunta al usuario si desea sobrescribir el archivo si ya existe, o finaliza si no es un archivo válido.
     *
     * @param outputFilePath Ruta del archivo de salida.
     * @return {@code true} si el archivo puede sobrescribirse o no existe, {@code false} si el programa debe finalizar.
     */
    public boolean validarUbicacionArchivo(String outputFilePath) {
        File file = new File(outputFilePath);
        Scanner scanner = new Scanner(System.in);

        if (file.isDirectory()) {
            System.out.println("La ruta especificada es un directorio. El programa finalizará.");
            return false;
        } else if (!file.exists()) {
            System.out.println("El archivo especificado no existe. El programa finalizará.");
            return false;
        }else {
            System.out.println("El archivo ya existe: " + outputFilePath);
            System.out.print("¿Quieres sobrescribirlo? (si/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("si")) {
                System.out.println("El programa se ha terminado.");
                return false;
            }
        }
        return true;
    }

    /**
     * Genera un informe basado en las estadísticas de las ciudades.
     *
     * @param cityStatisticsList Lista de objetos CityStatistics.
     * @param outputFilePath     Ruta del archivo de salida.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void generateReport(List<CityStatistics> cityStatisticsList, String outputFilePath) throws IOException {
        StringBuilder reportContent = new StringBuilder();

        for (CityStatistics cityStats : cityStatisticsList) {
            reportContent.append(cityStats.getCityName()).append("\n")
                    .append("------------------------------------------------------------------------\n")
                    .append(String.format("%-10s %-10s %-10s %-15s %-15s\n",
                            "T mínima", "T máxima", "T media", "Humedad media", "Presión media"))
                    .append(String.format("%-10.2f %-10.2f %-10.2f %-15.2f %-15.2f\n",
                            cityStats.getMinTemperature(),
                            cityStats.getMaxTemperature(),
                            cityStats.getAvgTemperature(),
                            cityStats.getAvgHumidity(),
                            cityStats.getAvgPressure()))
                    .append("\n");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            writer.print(reportContent.toString());
        }

        System.out.println("Informe generado correctamente: " + outputFilePath);
    }
}