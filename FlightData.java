// Flyweight-Factory design pattern
public class FlightData {
    private final String to;
    private final String from;
    private final int capacity;
    private final double durationTime;

    public FlightData(String to, String from, int capacity, double durationTime) {
        this.to = to;
        this.from = from;
        this.durationTime = durationTime;
        this.capacity = capacity;
    }

    public String getTo() {
        return this.to;
    }

    public String getFrom() {
        return this.from;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public double getDurationTime() {
        return this.durationTime;
    }

}

