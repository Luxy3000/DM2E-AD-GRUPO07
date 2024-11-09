package es.dm2egrupo07.accesoadatos.programa01;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Singleton para gestionar la lista de objetos CityWeather.
 * Proporciona una única instancia para acceder y manipular las ciudades registradas.
 */
public class CityWeatherManager {

    /**
     * Instancia única de la clase CityWeatherManager.
     */
    private static CityWeatherManager instance;

    /**
     * Lista que contiene los objetos CityWeather.
     */
    private final List<CityWeather> cities;

    /**
     * Constructor privado para evitar la creación de instancias fuera de la clase.
     * Inicializa la lista de ciudades.
     */
    private CityWeatherManager() {
        this.cities = new ArrayList<>();
    }

    /**
     * Método estático que devuelve la instancia única de CityWeatherManager.
     * Si no existe una instancia, la crea.
     *
     * @return La instancia única de CityWeatherManager.
     */
    public static synchronized CityWeatherManager getInstance() {
        if (instance == null) {
            instance = new CityWeatherManager();
        }
        return instance;
    }

    /**
     * Obtiene la lista de objetos CityWeather.
     *
     * @return Lista de objetos CityWeather.
     */
    public List<CityWeather> getCities() {
        return cities;
    }

    /**
     * Añade un nuevo objeto CityWeather a la lista de ciudades.
     *
     * @param city El objeto CityWeather que se añadirá.
     */
    public void addCity(CityWeather city) {
        cities.add(city);
    }
}