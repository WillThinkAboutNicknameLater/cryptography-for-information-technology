package ru.nsu.fit.util;

public class HashGenerator {
    private HashGenerator() {}

    public static <T> int generateHash(T value) {
        return Math.abs(value.hashCode());
    }
}
