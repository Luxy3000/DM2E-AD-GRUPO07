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
        if (qName.equalsIgnoreCase("city")) {
            enNodoCity = true;
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
        if (enNodoCity) {
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
        if (qName.equalsIgnoreCase("city") && enNodoCity) {
            resultado.append("\n");
            enNodoCity = false;
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
