import java.util.List;

public class SortByDate implements SortStrategy{
    @Override
    public void sort(List<Flight> flightList) {
        flightList.sort((Flight f1, Flight f2) -> {
            if(f1.getDateTime().isAfter(f2.getDateTime())) return 1;
            else if(f1.getDateTime().isBefore(f2.getDateTime())) return -1;
            return 0;
        });
    }
}
