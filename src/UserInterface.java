import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private final SearchEngine searchEngine;
    private final FileReader fileReader;
    private final Scanner scanner;

    public UserInterface(String fileName) {
        this.searchEngine = new SearchEngine();
        this.fileReader = new FileReader(fileName);
        this.scanner  = new Scanner(System.in);
    }

    private void presentOptions() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    private int readUserInputNum() {
        while (true) {
            int userInput = Integer.parseInt(scanner.nextLine());
            if (userInput == 0 || userInput == 1 || userInput == 2) {
                return userInput;
            } else {
                System.out.println("Incorrect option, try again.");
            }
        }

    }

    private String getStrategySearchOption() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        while (true) {
            String option = scanner.nextLine().toUpperCase();
            if (option.equals("ALL") || option.equals("ANY") || option.equals("NONE")) {
                return option;
            } else {
                System.out.println("Invalid strategy search option: " + option);
            }
        }
    }

    public void start() {
        while (true) {
            presentOptions();
            String choice = getMenuChoice(readUserInputNum());
            if (choice.equals("final")) {
                break;
            }
        }
    }

    private String getMenuChoice(int choice) {
        switch (choice) {
            case 0:
                System.out.println("Bye!");
                return "final";
            case 1:
                findPeople(getStrategySearchOption());
                return "not final";
            case 2:
                printAllPeople(fileReader.getPeopleList());
                return "not final";
            default:
                System.out.println("Incorrect option, try again.");
                return "not final";
        }
    }

    private void printAllPeople(List<String> peopleList) {
        System.out.println("=== List of people ===");
        for (String person : peopleList) {
            System.out.println(person);
        }
    }

    private void findPeople(String searchOption) {
        System.out.println("Enter a name or email to search all suitable people.");
        List<String> foundPeople = searchEngine.findPerson(
                scanner.nextLine().toLowerCase(),
                searchOption,
                fileReader.getPeopleList(),
                fileReader.getPeopleMap());
        if (foundPeople.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(foundPeople.size() + " persons found:");
            for (String person : foundPeople) {
                System.out.println(person);
            }
        }
    }
}