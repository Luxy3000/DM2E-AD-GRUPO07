package es.dm2egrupo07.accesoadatos.programa01;


import java.util.Scanner;

public class Main {

    private static final WeatherDataWriter weatherDataWriter = new WeatherDataWriter();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){


        System.out.println("¿Donde te gustaría guardar el fichero XML?");
        String outputFile = scanner.nextLine();
        if (weatherDataWriter.ubicacionXmlSalida(outputFile)) {
            System.out.println("Generando el fichero...");
        } else {
            System.out.println("El programa se ha terminado.");
        }
    }
}
