package es.dm2egrupo07.accesoadatos.programa01;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Clase principal que coordina la lectura de datos meteorológicos desde un archivo XML
 * y la generación de un nuevo archivo XML con estadísticas.
 */
public class Main {

    /** Instancia para leer los datos desde un archivo XML. */
    private static final WeatherDataReader weatherDataReader = new WeatherDataReader();

    /** Instancia para escribir los datos procesados en un archivo XML. */
    private static final WeatherDataWriter weatherDataWriter = new WeatherDataWriter();

    /** Scanner para la entrada del usuario en consola. */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Método principal que inicia la aplicación.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en este programa).
     * @throws ParserConfigurationException Si hay un error en la configuración del analizador XML.
     * @throws TransformerException Si ocurre un error durante la transformación del documento XML.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws SAXException Si se produce un error al analizar el archivo XML.
     */
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {

        Path ubicacion = weatherDataReader.preguntarUbicacionXML();
        if (ubicacion == null) {
            System.out.println("No se pudo encontrar el archivo. El programa se cerrará.");
            return;
        }

        weatherDataReader.leerFichero(ubicacion);

        System.out.println("¿Dónde te gustaría guardar el fichero XML?");
        String outputFile = scanner.nextLine();

        if (outputFile.endsWith("\\") || outputFile.endsWith("/")) {
            outputFile += "output.xml";
        }

        if (weatherDataWriter.ubicacionXmlSalida(outputFile)) {
            System.out.println("Generando el fichero...");
            weatherDataWriter.generarXml(outputFile);
            System.out.println("Fichero generado correctamente en: " + outputFile);
        } else {
            System.out.println("El programa se ha terminado.");
        }
    }
}