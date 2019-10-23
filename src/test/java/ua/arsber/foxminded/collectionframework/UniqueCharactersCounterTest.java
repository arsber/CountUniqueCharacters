package ua.arsber.foxminded.collectionframework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UniqueCharactersCounterTest {
    UniqueCharactersCounter counter = new UniqueCharactersCounter(2);
    
    @Test
    void makeCountShouldThrowExceptionWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> counter.count(null));
    }
    
    @Test
    void makeCountShouldReturnCorrectOutputWhenInputStringContainsLettersAndNonLetterSymbols() {
        String expected = "hello world!\n" +
                          "\"h\" - 1\n" +
                          "\"e\" - 1\n" +
                          "\"l\" - 3\n" +
                          "\"o\" - 2\n" +
                          "\" \" - 1\n" +
                          "\"w\" - 1\n" +
                          "\"r\" - 1\n" +
                          "\"d\" - 1\n" +
                          "\"!\" - 1\n";
        assertEquals(expected, counter.count("hello world!"));
    }
    
    @Test
    void makeCountShouldReturnEmptyStringWhenInputIsEmptyString() {
        assertEquals("", counter.count(""));
    }
    
    @Test
    void makeCountShouldReturnCorrectOutputWhenInputIsOnlyLowcaseLetters() {
        String expected = "hello\n" +
                          "\"h\" - 1\n" +
                          "\"e\" - 1\n" +
                          "\"l\" - 2\n" +
                          "\"o\" - 1\n";
        assertEquals(expected, counter.count("hello"));
    }
    
    @Test
    void makeCountShouldReturnCorrectOutputWhenInputIsOnlyCapitalLetters() {
        String expected = "HELLO\n" +
                          "\"H\" - 1\n" +
                          "\"E\" - 1\n" +
                          "\"L\" - 2\n" +
                          "\"O\" - 1\n";
        assertEquals(expected, counter.count("HELLO"));
    }
    
    @Test
    void makeCountShouldKeepCharactersEntryOrderWhenInputIsCapitalAndLowercaseLetters() {
        String expected = "AaAaA\n" +
                          "\"A\" - 3\n" +
                          "\"a\" - 2\n";
        assertEquals(expected, counter.count("AaAaA"));
    }
    
    @Test
    void makeCountShouldReturnCorrectOutputWhenInputIsOnlySymbols() {
        String expected = "122#$\n" +
                          "\"1\" - 1\n" +
                          "\"2\" - 2\n" +
                          "\"#\" - 1\n" +
                          "\"$\" - 1\n";
        assertEquals(expected, counter.count("122#$"));
    }
    
    @Test
    void makeCountShouldReturnCorrectOutputWhenInputIsOnlySpaces() {
        String expected = "   \n" +
                          "\" \" - 3\n";
        assertEquals(expected, counter.count("   "));
    }

    @Test
    void makeCountShouldGetResultFromCacheWhenCacheContainInputString() {
        assertSame(counter.count("hello"), counter.count("hello"));
    }
    
    @Test
    void makeCountShouldKeepTwoStringInCacheWhenCacheCapacityIsTwo() {
        String first = counter.count("string1");
        counter.count("string2");
        counter.count("string3");
        assertNotSame(first, counter.count("string1"));
    }
    
    @Test
    void makeCountShouldRemoveLRUStringFromCacheWhenPutStringInFullCache() {
        String first = counter.count("string1");
        counter.count("string2");
        counter.count("string1");
        counter.count("string3");
        assertSame(first, counter.count("string1"));
    }    
}
