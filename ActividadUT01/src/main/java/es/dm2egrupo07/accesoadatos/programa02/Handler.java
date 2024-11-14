package es.dm2egrupo07.accesoadatos.programa02;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase para analizar el contenido del fichero XML, en concreto los nodos city, y almacenarlo en formato de cadena de
 * texto.
 */
public class Handler extends DefaultHandler {
    private StringBuilder resultado = new StringBuilder();
    private boolean enNodoCity = false;
    private boolean enNodoTemperatura = false;
    private boolean enNodoHumedad = false;
    private boolean enNodoPresion = false;

    private String cityId;
    private String cityName;
    private int max;
    private int min;
    private int humidity;
    private int pressure;

    /**
     * Método para empezar a procesar un elemento city del fichero XML.
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
                System.out.println();
                System.out.println();
                break;

            case "temperature":
                enNodoTemperatura = true;
                max = Integer.parseInt(attributes.getValue("max"));
                min = Integer.parseInt(attributes.getValue("min"));
                break;

            case "humidity":
                enNodoHumedad = true;
                humidity = Integer.parseInt(attributes.getValue("humidity"));
                break;

            case "pressure":
                enNodoPresion = true;
                pressure = Integer.parseInt(attributes.getValue("pressure"));
                break;
        }
    }

    /**
     * Método para procesar el contenido que se encuentre entre las etiquetas de un elemento city del fichero XML.
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
            //stringHumidity +=
        }

        if (enNodoPresion) {
            resultado.append(ch, start, length);
        }
    }

    /**
     * Método para terminar de procesar un elemento city del fichero XML.
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
                System.out.printf("Nombre: " + cityName);
                System.out.printf("Id: " + cityId);
                break;

            case "temperature":
                enNodoTemperatura = false;
                break;

            case "humidity":
                enNodoHumedad = false;
                break;

            case "pressure":
                enNodoPresion = false;
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
