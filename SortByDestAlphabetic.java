import java.util.List;

// Strategy design pattern
public class SortByDestAlphabetic implements SortStrategy{
    @Override
    public void sort(List<Flight> flightList) {
        flightList.sort((Flight f1, Flight f2) -> {
            return f1.getTo().compareTo(f2.getTo());
        });
    }
}
