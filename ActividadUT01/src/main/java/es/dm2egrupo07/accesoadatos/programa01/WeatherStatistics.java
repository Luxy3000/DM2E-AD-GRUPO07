package es.dm2egrupo07.accesoadatos.programa01;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase creada para calcular temperaturas mínimas, máximas, humedad y presión promedio de las ciudades
 */
public class WeatherStatistics {

    public static List<CityStatistics> calculateStatistics(List<CityWeather> cities) {
        List<CityStatistics> statisticsList = new ArrayList<>();
        for(CityWeather c : cities) {
            double minTemperature = calculateMin(c.getTemperatures());
            double maxTemperature = calculateMax(c.getTemperatures());
            double avgTemperature = calculateAverage(c.getTemperatures());
            double avgHumidity = calculateAverage(c.getHumidities());
            double avgPressure = calculateAverage(c.getPressures());

            CityStatistics stats = new CityStatistics(c.getName(), avgTemperature, minTemperature, maxTemperature, avgHumidity, avgPressure);

            statisticsList.add(stats);
        }
        return statisticsList;
    }

    public static double calculateMin(List<Double> values){
        double min = Double.MAX_VALUE;
        for(int i = 0; i < values.size(); i++){
            double value = values.get(i);
            if(value < min){
                min = value;
            }
        }
        return min;
    }

    public static double calculateMax(List<Double> values){
        double max = Double.MIN_VALUE;
        for(int i = 0; i < values.size(); i++){
            double value = values.get(i);
            if(value > max){
                max = value;
            }
        }
        return max;
    }

    public static double calculateAverage(List<Double> values){
        double sum = 0;
        for(int i = 0; i < values.size(); i++){
            double value = values.get(i);
            sum+= value;
        }
        return sum / values.size();
    }
}
