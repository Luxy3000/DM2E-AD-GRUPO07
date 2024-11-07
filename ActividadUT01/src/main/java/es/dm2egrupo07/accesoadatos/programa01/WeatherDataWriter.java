package es.dm2egrupo07.accesoadatos.programa01;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * Clase creada para generar el archivo XML de salina y gestionar la ubicación para el archivo de salida
 */
public class WeatherDataWriter {

    public static final int INDENT = 5;

    public void generarXml() throws ParserConfigurationException, TransformerException {

        List<CityWeather> cities = getCityWeatherData();

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document xmlDocument = builder.newDocument();
        xmlDocument.setXmlVersion("1.0");
        xmlDocument.setXmlStandalone(true);

        Element citiesElement = xmlDocument.createElement("city-weather-summary");
        xmlDocument.appendChild(citiesElement);

        for (CityWeather cityWeather : cities) {

            double minTemperature = WeatherStatistics.calculateMin(cityWeather.getTemperatures());
            double maxTemperature = WeatherStatistics.calculateMax(cityWeather.getTemperatures());
            double avgTemperature = WeatherStatistics.calculateAverage(cityWeather.getTemperatures());
            double avgHumidity = WeatherStatistics.calculateAverage(cityWeather.getHumidities());
            double avgPressure = WeatherStatistics.calculateAverage(cityWeather.getPressures());

            Element cityElement = xmlDocument.createElement("city");
            cityElement.setAttribute("id", cityWeather.getId());
            cityElement.setAttribute("name", cityWeather.getName());

            Element avgTempElement = xmlDocument.createElement("average-temperature");

            Element avgElement = xmlDocument.createElement("avg");
            avgElement.appendChild(xmlDocument.createTextNode(String.format("%.1f", avgTemperature)));

            Element maxElement = xmlDocument.createElement("max");
            maxElement.appendChild(xmlDocument.createTextNode(String.format("%.1f", maxTemperature)));

            Element minElement = xmlDocument.createElement("min");
            minElement.appendChild(xmlDocument.createTextNode(String.format("%.1f", minTemperature)));

            avgTempElement.appendChild(avgElement);
            avgTempElement.appendChild(maxElement);
            avgTempElement.appendChild(minElement);

            Element humidityElement = xmlDocument.createElement("average-humidity");
            humidityElement.appendChild(xmlDocument.createTextNode(String.format("%.1f", avgHumidity)));

            Element pressureElement = xmlDocument.createElement("average-pressure");
            pressureElement.appendChild(xmlDocument.createTextNode(String.format("%.1f", avgPressure)));

            cityElement.appendChild(avgTempElement);
            cityElement.appendChild(humidityElement);
            cityElement.appendChild(pressureElement);

            citiesElement.appendChild(cityElement);
        }

        dumpToConsole(xmlDocument);

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


    private static void dumpToConsole(Document newDoc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute("indent-number", INDENT);
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Source src = new DOMSource(newDoc);
        Result dest = new StreamResult(System.out);
        transformer.transform(src, dest);
    }
}
