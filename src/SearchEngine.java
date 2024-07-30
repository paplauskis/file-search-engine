import java.util.*;

public class SearchEngine {

    private List<String> findPersonStrategyALL(String searchWord, List<String> foundPeople, List<String> peopleList) {
        String[] sw = searchWord.toLowerCase().split(" ");
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
            String searchWord,
            List<String> foundPeople,
            List<String> peopleList,
            Map<String, List<Integer>> peopleMap) {
        String[] sw = searchWord.toLowerCase().split(" ");
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
            String searchWord,
            List<String> foundPeople,
            List<String> peopleList,
            Map<String, List<Integer>> peopleMap) {
        List<String> temp = findPersonStrategyANY(searchWord, foundPeople, peopleList, peopleMap);
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
        switch (searchOption) {
            case "ALL" -> foundPeople = findPersonStrategyALL(searchWord, foundPeople, peopleList);
            case "ANY" -> foundPeople = findPersonStrategyANY(searchWord, foundPeople, peopleList, peopleMap);
            case "NONE" -> foundPeople = findPersonStrategyNONE(searchWord, foundPeople, peopleList, peopleMap);
        }
        return foundPeople;
    }
}