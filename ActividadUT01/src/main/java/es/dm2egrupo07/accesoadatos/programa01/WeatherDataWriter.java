package es.dm2egrupo07.accesoadatos.programa01;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

/**
 * Clase creada para generar el archivo XML de salida con datos meteorológicos procesados
 * y gestionar la ubicación del archivo de salida.
 */
public class WeatherDataWriter {

    /** Constante para definir la cantidad de espacios usados en la indentación del XML. */
    public static final int INDENT = 5;

    /**
     * Genera un archivo XML con los datos promedio de cada ciudad.
     *
     * @param outputFilePath Ruta del archivo XML de salida.
     * @throws ParserConfigurationException Si hay un error al configurar el analizador.
     * @throws TransformerException         Si ocurre un error durante la transformación del documento.
     */
    public void generarXml(String outputFilePath) throws ParserConfigurationException, TransformerException {

        List<CityWeather> cities = CityWeatherManager.getInstance().getCities();

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
            avgElement.appendChild(xmlDocument.createTextNode(String.format("%.2f", avgTemperature)));

            Element maxElement = xmlDocument.createElement("max");
            maxElement.appendChild(xmlDocument.createTextNode(String.format("%.2f", maxTemperature)));

            Element minElement = xmlDocument.createElement("min");
            minElement.appendChild(xmlDocument.createTextNode(String.format("%.2f", minTemperature)));

            avgTempElement.appendChild(avgElement);
            avgTempElement.appendChild(maxElement);
            avgTempElement.appendChild(minElement);

            Element humidityElement = xmlDocument.createElement("average-humidity");
            humidityElement.appendChild(xmlDocument.createTextNode(String.format("%.2f", avgHumidity)));

            Element pressureElement = xmlDocument.createElement("average-pressure");
            pressureElement.appendChild(xmlDocument.createTextNode(String.format("%.2f", avgPressure)));

            cityElement.appendChild(avgTempElement);
            cityElement.appendChild(humidityElement);
            cityElement.appendChild(pressureElement);

            citiesElement.appendChild(cityElement);
        }

        dumpToConsole(xmlDocument);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(INDENT));
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        DOMSource source = new DOMSource(xmlDocument);
        StreamResult result = new StreamResult(new File(outputFilePath));
        transformer.transform(source, result);

        System.out.println("Archivo XML generado correctamente en: " + outputFilePath);
    }

    /**
     * Verifica si el archivo XML de salida ya existe y pide confirmación para sobrescribirlo.
     *
     * @param ruta Ruta del archivo XML de salida.
     * @return {@code true} si se puede sobrescribir o si el archivo no existe, {@code false} en caso contrario.
     */
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

    /**
     * Muestra el contenido del documento XML en consola con la estructura y formato configurados.
     *
     * @param newDoc Documento XML a volcar en consola.
     * @throws TransformerException Si ocurre un error durante la transformación.
     */
    public void dumpToConsole(Document newDoc) throws TransformerException {
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
