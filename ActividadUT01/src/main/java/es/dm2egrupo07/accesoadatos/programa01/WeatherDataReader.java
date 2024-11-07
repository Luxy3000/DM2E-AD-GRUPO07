package es.dm2egrupo07.accesoadatos.programa01;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

    public void leerFichero(Path ubicacion) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document fichero = builder.parse(ubicacion.toFile());
        fichero.getDocumentElement().normalize();

        NodeList nodos = fichero.getElementsByTagName("city");
        int numeroElementos = nodos.getLength();
        for (int i = 0; i < numeroElementos; i++) {
            Node nodo = nodos.item(i);
            System.out.println(nodo.getTextContent());
        }
    }
}
