package ru.nsu.fit.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.Keys;

public class KeysGenerator {
    private final Logger logger;

    public KeysGenerator() {
        this.logger = LogManager.getLogger(KeysGenerator.class);
    }

    public Keys generateKeys(int modulo) {
        int c = 0;
        boolean areRelativelyPrimeNumbers = false;

        while (!areRelativelyPrimeNumbers) {
            c = Randomizer.generateNumber(1, modulo - 1);
            EuclidAlgorithmResponse euclidResponse = MathHelper.calculateEuclidAlgorithm(c, modulo);
            if (euclidResponse.gcd() == 1) {
                areRelativelyPrimeNumbers = true;
            }
        }

        Keys keys = new Keys(c, MathHelper.calculateInverseModulo(c, modulo));

        logger.debug("Generating the keys: {}", keys);

        return keys;
    }
}
