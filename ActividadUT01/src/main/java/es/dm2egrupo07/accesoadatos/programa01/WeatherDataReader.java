package es.dm2egrupo07.accesoadatos.programa01;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Clase creada para leer y validar el archivo de entrada y calcular el promedio de temperatura
 */
public class WeatherDataReader {
  private static final Scanner scanner = new Scanner(System.in);

  /**
     * Método para preguntar al usuario la ruta del fichero a leer y si el fichero existe.
     * @return el camino al archivo
     */
    public Path preguntarUbicacionXML() {
        System.out.println("Introduzca la ruta del fichero que quiera leer: ");
        String ruta = scanner.nextLine();

        Path ubicacion = Path.of(ruta);

        if (ubicacion.toFile().exists()) {
            System.out.println("El fichero ha sido encontrado correctamente.");
            return ubicacion;
        } else {
            System.out.println("La ruta introducida no existe");
            return null;
        }
    }

  /**
     * Método para leer el fichero que se encuentre en la ubicación recogida en el método anterior.
     * @param ubicacion
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public void leerFichero(Path ubicacion) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document fichero = builder.parse(ubicacion.toFile());
        fichero.getDocumentElement().normalize();

        /**
         * Para comprobar que se puede recoger infomación del fichero, lo que a su vez nos demuestra que se ha leido
         * corrctamente.
         */
        /*
        NodeList nodos = fichero.getElementsByTagName("city");
        int numeroElementos = nodos.getLength();
        for (int i = 0; i < numeroElementos; i++) {
            Node nodo = nodos.item(i);
            System.out.println(nodo.getTextContent());
        }*/

        CityWeatherManager manager = CityWeatherManager.getInstance();

        NodeList cityNodes = fichero.getElementsByTagName("city");

        for (int i = 0; i < cityNodes.getLength(); i++) {
            Element cityElement = (Element) cityNodes.item(i);
            CityWeather cityWeather = getCityWeather(cityElement);

            manager.addCity(cityWeather);
        }

        System.out.println("Datos de las ciudades cargados correctamente.");
    }

    /**
     * Crea un objeto CityWeather a partir de un elemento XML que representa una ciudad.
     *
     * @param cityElement Elemento XML que contiene los datos de una ciudad.
     * @return Objeto CityWeather con los datos de la ciudad procesada.
     * @throws NumberFormatException Si algún dato numérico no puede ser parseado.
     */
    private static CityWeather getCityWeather(Element cityElement) {
        String cityId = cityElement.getAttribute("id");
        String cityName = cityElement.getAttribute("name");

        CityWeather cityWeather = new CityWeather(cityId, cityName);

        NodeList temperatureNodes = cityElement.getElementsByTagName("temperature");
        for (int j = 0; j < temperatureNodes.getLength(); j++) {
            Element tempElement = (Element) temperatureNodes.item(j);
            double maxTemp = Double.parseDouble(tempElement.getAttribute("max"));
            double minTemp = Double.parseDouble(tempElement.getAttribute("min"));
            cityWeather.addTemperature(maxTemp);
            cityWeather.addTemperature(minTemp);
        }

        NodeList humidityNodes = cityElement.getElementsByTagName("humidity");
        for (int j = 0; j < humidityNodes.getLength(); j++) {
            double humidity = Double.parseDouble(humidityNodes.item(j).getTextContent());
            cityWeather.addHumidity(humidity);
        }

        NodeList pressureNodes = cityElement.getElementsByTagName("pressure");
        for (int j = 0; j < pressureNodes.getLength(); j++) {
            double pressure = Double.parseDouble(pressureNodes.item(j).getTextContent());
            cityWeather.addPressure(pressure);
        }
        return cityWeather;
    }
}
