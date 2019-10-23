package ua.arsber.foxminded.collectionframework;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.map.LRUMap;

public class UniqueCharactersCounter {
    Cache cache;

    public UniqueCharactersCounter(int cacheCapacity) {
        this.cache = new Cache(cacheCapacity);
    }

    public String count(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Argument should not be null");
        }

        if (cache.containsKey(input)) {
            return cache.get(input);
        }
        Map<Character, Integer> uniqueCharacters = countUniqueCharacters(input);
        String viewResult = buildViewResult(input, uniqueCharacters);
        cache.put(input, viewResult);
        return viewResult;
    }

    private Map<Character, Integer> countUniqueCharacters(String input) {
        Character[] inputCharacters = input.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Map<Character, Integer> uniqueCharacters = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < inputCharacters.length; i++) {
            uniqueCharacters.merge(inputCharacters[i], 1, (a, b) -> a + 1);
        }
        return uniqueCharacters;
    }

    private String buildViewResult(String input, Map<Character, Integer> uniqueCharacters) {
        if (input.isEmpty()) {
            return input;
        }
        StringBuilder result = new StringBuilder();
        result.append(input).append("\n");
        for (Map.Entry<Character, Integer> entry : uniqueCharacters.entrySet()) {
            String line = ("\"" + entry.getKey() + "\" - " + entry.getValue() + "\n");
            result.append(line);
        }
        return result.toString();
    }

    private class Cache {
        private final LRUMap<String, String> storage;

        Cache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity should be more than 0");
            }
            this.storage = new LRUMap<String, String>(capacity);
        }

        void put(String key, String value) {
            storage.put(key, value);
        }

        String get(String key) {
            return storage.get(key);
        }

        boolean containsKey(String key) {
            return storage.containsKey(key);
        }
    }
}
