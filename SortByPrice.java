import java.util.List;

public class SortByPrice implements SortStrategy{
    @Override
    public void sort(List<Flight> flightList) {
        flightList.sort((Flight f1, Flight f2) -> {
            if(f1.getTicketPrice() > f2.getTicketPrice()) return 1;
            else if(f1.getTicketPrice() < f2.getTicketPrice()) return -1;
            return 0;
        });
    }
}
