package ru.nsu.fit.util;

import java.util.Random;

public class Randomizer {
    private static final Random random = new Random();

    private Randomizer() {}

    public static int generateNumber(int from, int to) {
        if (from > to) {
            int oldFrom = from;
            from = to;
            to = oldFrom;
        }

        return from + random.nextInt(to - from + 1);
    }
}
