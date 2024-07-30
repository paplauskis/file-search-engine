import java.util.*;

public class SearchEngine {

    private List<String> findPersonStrategyALL(String[] sw, List<String> foundPeople, List<String> peopleList) {
        for (String person : peopleList) {
            boolean containsAllWords = true;
            for (String word : sw) {
                if (!person.toLowerCase().contains(word)) {
                    containsAllWords = false;
                    break;
                }
            }
            if (containsAllWords) {
                foundPeople.add(person);
            }
        }
        return foundPeople;
    }

    private List<String> findPersonStrategyANY(
            String[] sw,
            List<String> foundPeople,
            List<String> peopleList,
            Map<String, List<Integer>> peopleMap) {
        peopleMap.forEach((key, value) -> {
            for (String word : sw) {
                if (key.contains(word.toLowerCase())) {
                    for (int num : value) {
                        foundPeople.add(peopleList.get(num));
                    }
                }
            }
        });
        return foundPeople;
    }

    private List<String> findPersonStrategyNONE(
            String[] sw,
            List<String> foundPeople,
            List<String> peopleList,
            Map<String, List<Integer>> peopleMap) {
        List<String> temp = findPersonStrategyANY(sw, foundPeople, peopleList, peopleMap);
        List<String> all = new ArrayList<>(peopleList);
        all.removeAll(temp);
        return all;
    }

    public List<String> findPerson(
            String searchWord,
            String searchOption,
            List<String> peopleList,
            Map<String, List<Integer>> peopleMap) {
        List<String> foundPeople = new ArrayList<>();
        String[] sw = searchWord.toLowerCase().split(" ");
        switch (searchOption) {
            case "ALL" -> foundPeople = findPersonStrategyALL(sw, foundPeople, peopleList);
            case "ANY" -> foundPeople = findPersonStrategyANY(sw, foundPeople, peopleList, peopleMap);
            case "NONE" -> foundPeople = findPersonStrategyNONE(sw, foundPeople, peopleList, peopleMap);
        }
        return foundPeople;
    }
}