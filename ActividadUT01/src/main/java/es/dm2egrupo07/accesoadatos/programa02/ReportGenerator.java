package es.dm2egrupo07.accesoadatos.programa02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Clase encargada de generar un informe en formato de texto.
 */
public class ReportGenerator {

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
                            "T min", "T max", "T media", "Humedad", "Presión"))
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