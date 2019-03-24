package utils;

class RandomGenerator {
    static int generateRandomNumber(int to, int from) {
        return ((int) (Math.random() * to) + from);
    }

}
