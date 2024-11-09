package es.dm2egrupo07.accesoadatos.programa01;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final WeatherDataReader weatherDataReader = new WeatherDataReader();
    private static final WeatherDataWriter weatherDataWriter = new WeatherDataWriter();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException, SAXException {

        Path ubicacion = weatherDataReader.preguntarUbicacionXML();
        weatherDataReader.leerFichero(ubicacion);

       System.out.println("¿Donde te gustaría guardar el fichero XML?");
        String outputFile = scanner.nextLine();
        if (weatherDataWriter.ubicacionXmlSalida(outputFile)) {
            WeatherDataWriter weatherDataWriter = new WeatherDataWriter();
            weatherDataWriter.generarXml();
            System.out.println("Generando el fichero...");
        } else {
            System.out.println("El programa se ha terminado.");
        }
    }
}
