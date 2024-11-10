package es.dm2egrupo07.accesoadatos.programa02;

import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Clase creada para leer y validar el archivo XML.
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
     * @throws SAXException
     * @throws IOException
     */
    public void leerFichero(Path ubicacion) throws ParserConfigurationException, SAXException, IOException {
        File ficheroXML = ubicacion.toFile();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        Handler handler = new Handler();

        parser.parse(ficheroXML, handler);

        // Para comprobar que el fichero se ha leido correctamente
        // String textoGenerado = handler.getText();
        // System.out.println(textoGenerado);
    }
}
