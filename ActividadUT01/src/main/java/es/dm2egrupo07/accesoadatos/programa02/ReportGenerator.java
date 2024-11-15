package es.dm2egrupo07.accesoadatos.programa02;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportGenerator {

    /**
     * Genera un informe de valores medios en formato texto para una lista de objetos CityWeather.
     *
     * @param cityStatisticsList Lista de objetos CityWeather con los datos de las ciudades.
     * @param outputFilePath  Ruta del archivo de salida donde se guardará el informe.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public void generateReport(List<CityStatistics> cityStatisticsList, String outputFilePath) throws IOException {
        StringBuilder reportContent = new StringBuilder();

        for (CityStatistics cityStats : cityStatisticsList) {
            reportContent.append(cityStats.getCityName()).append("\n");
            reportContent.append("------------------------------------------------------------------------\n");
            reportContent.append(String.format("%-10s %-10s %-10s %-15s %-15s\n",
                    "T mínima", "T máxima", "T media", "Humedad media", "Presión media"));
            reportContent.append(String.format("%-10.2f %-10.2f %-10.2f %-15.2f %-15.2f\n",
                    cityStats.getMinTemperature(),
                    cityStats.getMaxTemperature(),
                    cityStats.getAvgTemperature(),
                    cityStats.getAvgHumidity(),
                    cityStats.getAvgPressure()));
            reportContent.append("\n");
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            writer.print(reportContent.toString());
        }

        System.out.println("Informe generado correctamente en: " + outputFilePath);
    }

}