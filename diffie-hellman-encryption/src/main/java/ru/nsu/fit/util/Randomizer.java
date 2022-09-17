package ru.nsu.fit.util;

import java.util.Random;

public class Randomizer {
    private static final Random random = new Random();

    private Randomizer() {}

    public static int generateNumber(int from, int through) {
        if (from > through) {
            int oldFrom = from;
            from = through;
            through = oldFrom;
        }

        return from + random.nextInt(through - from + 1);
    }
}
