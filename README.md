# Flight and Company Management System

This repository implements a **Flight and Company Management System** designed to manage flights, sort them dynamically, organize companies hierarchically, and notify users of relevant updates. The system utilizes advanced design patterns to ensure modularity, scalability, and ease of maintenance.


## Features
- **Flight Management**: Sort flights dynamically by price, date, or destination.
- **Company Management**: Organize companies hierarchically using a composite structure.
- **User Notifications**: Notify users of flight or company updates (e.g., delays, cancellations, or announcements).
- **Simple User Interaction**: A clean interface for sorting and retrieving information.


## Design Patterns Used

### 1. **Strategy**
- **Purpose**: Allows dynamic selection of sorting algorithms for flights.
- **Example**: The system provides sorting strategies (`SortByPrice`, `SortByDate`, `SortByDestAlphabetic`) that can be switched at runtime.
- **Implementation**: 
    ```java
    public interface SortStrategy {
        void sort(List<Flight> flightList);
    }

    public class SortByPrice implements SortStrategy {
        @Override
        public void sort(List<Flight> flightList) {
            flightList.sort(Comparator.comparingDouble(Flight::getTicketPrice));
        }
    }
    ```
- **Advantages**:
  - Flexible and easily extendable to new sorting criteria.
  - Promotes encapsulation and separation of concerns.


### 2. **Composite**
- **Purpose**: Facilitates hierarchical organization and uniform handling of companies and sub-companies.
- **Example**: Each company can act as a parent (composite) or child (leaf), enabling operations across the entire hierarchy.
- **Implementation**:
    ```java
    public interface Company {
        void setChildCompany(Company company);
        void removeAllOccurrenceChildCompany(Company subCompany);
        Set<Company> getChildrenCompany();
    }

    public class ConcreteCompany implements Company {
        private Set<Company> subCompanies = new HashSet<>();
        @Override
        public void setChildCompany(Company company) { subCompanies.add(company); }
        @Override
        public void removeAllOccurrenceChildCompany(Company subCompany) { subCompanies.remove(subCompany); }
        @Override
        public Set<Company> getChildrenCompany() { return subCompanies; }
    }
    ```
- **Advantages**:
  - Simplifies operations on hierarchies.
  - Reduces complexity in managing multiple layers of companies.


### 3. **Observer**
- **Purpose**: Manages notifications between subjects (e.g., flights or companies) and observers (e.g., users).
- **Example**: Users subscribe to updates and receive notifications when flights are delayed or company announcements are made.
- **Implementation**:
    ```java
    public abstract class NotificationService {
        private Set<User> subscribers = new HashSet<>();
        public void addSubscriber(User user) { subscribers.add(user); }
        public void updateAll(String message) {
            for (User user : subscribers) { user.update(message); }
        }
    }

    public class CompanyNotifier extends NotificationService {
        public void notifyCompanySubscribers(String companyName, String info) {
            String message = companyName + ": " + info;
            updateAll(message);
        }
    }
    ```
- **Advantages**:
  - Decouples subjects from their observers.
  - Supports dynamic subscription and unsubscription.


## Code Structure

- **`SortStrategy`**:
  - Provides interfaces and concrete classes for sorting flights.
  - Example strategies: `SortByPrice`, `SortByDate`, `SortByDestAlphabetic`.

- **`Company` and `ConcreteCompany`**:
  - Implements the composite pattern for hierarchical management of companies.

- **`NotificationService` and `CompanyNotifier`**:
  - Facilitates the Observer pattern for sending updates to users.

- **`FlightManagement`**:
  - Centralizes operations for flight data, sorting, and notifications.


## Example Usage

#### Sorting Flights:
```java
private static void sortFlights() {
    System.out.println("Choose sorting strategy:");
    System.out.println("1. Sort by Price");
    System.out.println("2. Sort by Date");
    System.out.println("3. Sort by Destination");
    int choice = scanner.nextInt();
    switch (choice) {
        case 1 -> flightManagement.sortAndPrintByStrategy(new SortByPrice());
        case 2 -> flightManagement.sortAndPrintByStrategy(new SortByDate());
        case 3 -> flightManagement.sortAndPrintByStrategy(new SortByDestAlphabetic());
    }
}
```

#### Managing Company Hierarchy:
```java
ConcreteCompany parentCompany = new ConcreteCompany("1", "ParentCompany");
ConcreteCompany childCompany = new ConcreteCompany("2", "ChildCompany");
parentCompany.setChildCompany(childCompany);
```

#### Notifying Users:
```java
CompanyNotifier notifier = new CompanyNotifier();
notifier.addSubscriber(new User("JohnDoe"));
notifier.notifyCompanySubscribers("Airways", "Flight 101 delayed.");
```

## Running the System

1. Clone the repository:
   ```bash
   git clone [<repository-url>](https://github.com/AmitGini/Airport-Network.git)
   cd Airport-Network
   ```

2. Compile and run:
   ```bash
   javac *.java
   java Main
   ```

3. Interact with the terminal prompts to explore features like flight sorting and company management.


## Acknowledgments

This project demonstrates the use of design patterns to build a robust, scalable, and maintainable system. It serves as a model for implementing modular software solutions in real-world scenarios.


## Contributing

Contributions are welcome! Feel free to submit pull requests or open issues for discussions.


## License

[MIT](https://choosealicense.com/licenses/mit/)
