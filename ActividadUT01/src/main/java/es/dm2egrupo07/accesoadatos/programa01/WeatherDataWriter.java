package es.dm2egrupo07.accesoadatos.programa01;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import java.io.File;
import java.util.*;

/**
 * Clase creada para generar el archivo XML de salina y gestionar la ubicación para el archivo de salida
 */
public class WeatherDataWriter {

    public void generarXml() throws ParserConfigurationException {

        List<CityWeather> cities = getCityWeatherData();
        List<CityStatistics> statisticsList = WeatherStatistics.calculateStatistics(cities);

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document xmlDocument = builder.newDocument();
        xmlDocument.setXmlVersion("1.0");
        xmlDocument.setXmlStandalone(true);

        Element citiesElement = xmlDocument.createElement("city-weather-summary");

        for (CityWeather cityWeather : cities) {
            Element cityNameElement = xmlDocument.createElement("city");
            cityNameElement.setAttribute("id", cityWeather.getId());
            cityNameElement.setAttribute("name", cityWeather.getName());

            Element cityAvgTemp = xmlDocument.createElement("average-temperature");
            Element avgElement = xmlDocument.createElement("avg");

            citiesElement.appendChild(cityNameElement);
        }

    }

    private static List<CityWeather> getCityWeatherData() {
        List<CityWeather> cities = new ArrayList<>();

        CityWeather madridWeather = new CityWeather("c001", "Madrid");
        madridWeather.addTemperature(18);
        madridWeather.addTemperature(22);
        madridWeather.addTemperature(16);
        madridWeather.addHumidity(80);
        madridWeather.addHumidity(60);
        madridWeather.addHumidity(85);
        madridWeather.addPressure(1015);
        madridWeather.addPressure(1013);
        madridWeather.addPressure(1016);

        cities.add(madridWeather);

        return cities;
    }

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
