package ru.nsu.fit.digitalsignature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.MathHelper;
import ru.nsu.fit.util.Randomizer;

public class ElGamalKeysGenerator {
    private final Logger logger;

    public ElGamalKeysGenerator() {
        this.logger = LogManager.getLogger(ElGamalKeysGenerator.class);
    }

    private int generatePrivateKey(int p) {
        return Randomizer.generateNumber(2, p - 2);
    }

    private int generatePublicKey(int p, int g, int privateKey) {
        return MathHelper.pow(g, privateKey, p);
    }

    public CipherKeys generateCipherKeys(int p, int g) {
        int privateKey = generatePrivateKey(p);
        int publicKey = generatePublicKey(p, g, privateKey);
        CipherKeys keys = new CipherKeys(privateKey, publicKey);

        logger.debug("Generating the keys: {}", keys);

        return keys;
    }
}
