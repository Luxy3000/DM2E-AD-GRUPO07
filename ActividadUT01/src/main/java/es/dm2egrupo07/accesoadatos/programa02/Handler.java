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
@Getter
public class Handler extends DefaultHandler {
    private final List<CityWeather> cities = new ArrayList<>();
    private CityWeather currentCity;
    private boolean enNodoHumedad = false;
    private boolean enNodoPresion = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                currentCity = new CityWeather(
                        attributes.getValue("id"),
                        attributes.getValue("name")
                );
                break;
            case "temperature":
                currentCity.addTemperature(Double.parseDouble(attributes.getValue("max")));
                currentCity.addTemperature(Double.parseDouble(attributes.getValue("min")));
                break;
            case "humidity":
                enNodoHumedad = true;
                break;
            case "pressure":
                enNodoPresion = true;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String content = new String(ch, start, length).trim();
        if (enNodoHumedad && !content.isEmpty()) {
            currentCity.addHumidity(Double.parseDouble(content));
        } else if (enNodoPresion && !content.isEmpty()) {
            currentCity.addPressure(Double.parseDouble(content));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                if (currentCity != null) {
                    cities.add(currentCity);
                }
                break;
            case "humidity":
                enNodoHumedad = false;
                break;
            case "pressure":
                enNodoPresion = false;
                break;
        }
    }

}
