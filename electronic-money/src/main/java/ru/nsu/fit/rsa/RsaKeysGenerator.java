package ru.nsu.fit.rsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.MathHelper;

public class RsaKeysGenerator {
    private final Logger logger;

    public RsaKeysGenerator() {
        this.logger = LogManager.getLogger(RsaKeysGenerator.class);
    }

    public CipherKeys generateCipherKeys(int modulo) {
        int c = MathHelper.generateCoPrimeNumber(modulo, 1, modulo - 1);

        CipherKeys keys = new CipherKeys(c, MathHelper.calculateInverseModulo(c, modulo));

        logger.debug("Generating the keys: {}", keys);

        return keys;
    }
}
