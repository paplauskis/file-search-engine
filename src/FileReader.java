import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {
    private final Map<String, List<Integer>> peopleMap;
    private final List<String> peopleList;
    private final String fileName;

    public FileReader(String fileName) {
        this.peopleMap = new HashMap<>();
        this.peopleList = new ArrayList<>();
        this.fileName = fileName;
        getPeopleFromFile();
    }

    public List<String> getPeopleList() {
        return peopleList;
    }

    public Map<String, List<Integer>> getPeopleMap() {
        return peopleMap;
    }

    private void addPersonToList(String person) {
        peopleList.add(person);
    }

    private void addPersonToMap(String person, int index) {
        String[] splittedPerson = person.toLowerCase().split(" ");
        for (String slice : splittedPerson) {
            if (!peopleMap.containsKey(slice)) {
                peopleMap.put(slice, new ArrayList<>());
            }
            peopleMap.get(slice).add(index);
        }
    }

    private void getPeopleFromFile() {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int index = 0;
            while (scanner.hasNextLine()) {
                String person = scanner.nextLine();
                addPersonToList(person);
                addPersonToMap(person, index);
                index++;
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("ERROR: file not found.");
        }
    }

}