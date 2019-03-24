package utils;

public class RandomGenerator {
    public static int generateRandomNumber(int to, int from) {
        return ((int) (Math.random() * to) + from);
    }

}
