package ua.arsber.foxminded.collectionframework;

public class Application {
    public static void main(String[] args) {
        int cacheCapacity = 2;
        String input1 = "hello world!";
        String input2 = "world hello!";
        String input3 = "hello world!";
        UniqueCharactersCounter builder = new UniqueCharactersCounter(cacheCapacity);
        System.out.println(builder.count(input1));
        System.out.println(builder.count(input2));
        System.out.println(builder.count(input3));
    }
}
