import java.util.HashMap;
import java.util.Map;

// Flyweight-Factory design pattern
public class FlightDataFactory {
    private static Map<String, FlightData> flightData = new HashMap<>();

    public static FlightData getFlightData(String to, String from, int capacity, double durationTime) {
        String key = to + from + durationTime;
        if (!flightData.containsKey(key)) {
            flightData.put(key, new FlightData(to, from, capacity, durationTime));
        }
        return flightData.get(key);
    }

}