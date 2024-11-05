package es.dm2egrupo07.accesoadatos.programa01;


import org.w3c.dom.Document;

import javax.xml.parsers.*;
import java.io.File;
import java.util.Scanner;

/**
 * Clase creada para generar el archivo XML de salina y gestionar la ubicación para el archivo de salida
 */
public class WeatherDataWriter {

    public void generarXml() throws ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document xmlDocument = builder.newDocument();

        xmlDocument.setXmlVersion("1.0");
        xmlDocument.setXmlStandalone(true);
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
