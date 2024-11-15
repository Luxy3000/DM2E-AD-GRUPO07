package es.dm2egrupo07.accesoadatos.programa02;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase creada para leer y validar el archivo XML.
 */
public class WeatherDataReader {

    private static final Scanner scanner = new Scanner(System.in);
    private Handler handler;

    /**
     * Pregunta al usuario la ruta del archivo XML y valida si el archivo existe.
     *
     * @return Path del archivo si existe, o null si no se encuentra.
     */
    public Path preguntarUbicacionXML() {
        System.out.println("Introduzca la ruta del fichero XML a procesar: ");
        String ruta = scanner.nextLine();

        Path ubicacion = Path.of(ruta);

        if (ubicacion.toFile().exists()) {
            System.out.println("El fichero se ha encontrado correctamente.");
            return ubicacion;
        } else {
            System.out.println("La ruta especificada no existe. Inténtelo de nuevo.");
            return null;
        }
    }

    /**
     * Lee y procesa el archivo XML desde la ubicación especificada.
     *
     * @param ubicacion Path del archivo XML.
     * @throws ParserConfigurationException Si hay un error en la configuración del parser.
     * @throws SAXException Si ocurre un error al analizar el XML.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public void leerFichero(Path ubicacion) throws ParserConfigurationException, SAXException, IOException {
        if (ubicacion == null) {
            throw new IllegalArgumentException("La ubicación del archivo no puede ser nula.");
        }

        File ficheroXML = ubicacion.toFile();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        handler = new Handler();
        parser.parse(ficheroXML, handler);

        System.out.println("El archivo XML se ha procesado correctamente.");

        // Para comprobar que el fichero se ha leido correctamente
        // String textoGenerado = handler.getText();
        // System.out.println(textoGenerado);
    }

    /**
     * Obtiene las estadísticas meteorológicas de las ciudades procesadas.
     *
     * @return Lista de objetos CityStatistics con estadísticas calculadas.
     */
    public List<CityStatistics> getCitiesStatistics() {
        if (handler == null || handler.getCities().isEmpty()) {
            throw new IllegalStateException("El archivo XML no ha sido procesado correctamente.");
        }

        List<CityWeather> cities = handler.getCities();
        return WeatherStatistics.calculateStatistics(cities);
    }
}
