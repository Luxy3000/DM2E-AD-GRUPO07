package es.dm2egrupo07.accesoadatos.programa02;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase para analizar el contenido del fichero XML, en concreto los nodos city, temperature, humidity y pressure, y
 * almacenarlos en listas para poder procesarlas luego y en formato de cadena de texto.
 */
public class Handler extends DefaultHandler {
    private StringBuilder resultado = new StringBuilder();
    private boolean enNodoCity = false;
    private boolean enNodoTemperatura = false;
    private boolean enNodoHumedad = false;
    private boolean enNodoPresion = false;

    private String cityId;
    private String cityName;
    private List<Double> temperatures = new ArrayList<>();
    private List<Double> humidities = new ArrayList<>();
    private List<Double> pressures = new ArrayList<>();

    @Getter
    private List<String> listaId = new ArrayList<>();
    @Getter
    private List<String> listaName = new ArrayList<>();
    @Getter
    private List<List<Double>> listaTemperatures = new ArrayList<>();
    @Getter
    private List<List<Double>> listaHumidities = new ArrayList<>();
    @Getter
    private List<List<Double>> listaPressures = new ArrayList<>();

    /**
     * Método para empezar a procesar un elemento city del fichero XML y guardar los atributos correspondientes.
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                enNodoCity = true;
                cityId = attributes.getValue("id");
                cityName = attributes.getValue("name");
                listaId.add(cityId);
                listaName.add(cityName);
                break;

            case "temperature":
                enNodoTemperatura = true;
                temperatures.add(Double.parseDouble(attributes.getValue("max")));
                temperatures.add(Double.parseDouble(attributes.getValue("min")));
                break;

            case "humidity":
                enNodoHumedad = true;
                break;

            case "pressure":
                enNodoPresion = true;
                break;
        }
    }

    /**
     * Método para procesar y guardar el contenido que se encuentre entre las etiquetas de un elemento de city del
     * fichero XML.
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (enNodoHumedad) {
            resultado.append(ch, start, length);
            humidities.add(Double.parseDouble(resultado.toString()));
        }

        if (enNodoPresion) {
            resultado.append(ch, start, length);
            pressures.add(Double.parseDouble(resultado.toString()));
        }
    }

    /**
     * Método para terminar de procesar un elemento de city del fichero XML y guardar los valores en su array
     * correspondiente.
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                enNodoCity = false;
                System.out.println("Ciudad finalizada");
                resultado.append("\n");
                break;

            case "temperature":
                enNodoTemperatura = false;
                listaTemperatures.add(temperatures);
                resultado.append("\n");
                break;

            case "humidity":
                enNodoHumedad = false;
                listaHumidities.add(humidities);
                resultado.append("\n");
                break;

            case "pressure":
                enNodoPresion = false;
                listaPressures.add(pressures);
                resultado.append("\n");
                break;
        }
    }

    /**
     * Método para devolver todos los nodos del archivo XML almacenados en los métodos anteriores.
     * @return el contenido de los nodos en formato de cadena de texto
     */
    public String getText() {
        return resultado.toString();
    }
}
