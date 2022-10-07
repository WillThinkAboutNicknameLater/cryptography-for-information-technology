package ru.nsu.fit.rsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.EuclidAlgorithmResponse;
import ru.nsu.fit.util.MathHelper;
import ru.nsu.fit.util.Randomizer;

public class RsaKeysGenerator {
    private final Logger logger;

    public RsaKeysGenerator() {
        this.logger = LogManager.getLogger(RsaKeysGenerator.class);
    }

    public CipherKeys generateCipherKeys(int modulo) {
        int c = 0;
        boolean areRelativelyPrimeNumbers = false;

        while (!areRelativelyPrimeNumbers) {
            c = Randomizer.generateNumber(1, modulo - 1);
            EuclidAlgorithmResponse euclidResponse = MathHelper.calculateEuclidAlgorithm(c, modulo);
            if (euclidResponse.gcd() == 1) {
                areRelativelyPrimeNumbers = true;
            }
        }

        CipherKeys keys = new CipherKeys(c, MathHelper.calculateInverseModulo(c, modulo));

        logger.debug("Generating the keys: {}", keys);

        return keys;
    }
}
