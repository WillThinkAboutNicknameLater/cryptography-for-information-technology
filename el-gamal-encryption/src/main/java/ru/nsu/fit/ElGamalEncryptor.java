package ru.nsu.fit;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.MathHelper;
import ru.nsu.fit.util.Randomizer;

public class ElGamalEncryptor {
    private final Logger logger;
    private final int p;
    private final int g;

    public ElGamalEncryptor() {
        this.logger = LogManager.getLogger(ElGamalEncryptor.class);
        this.p = 30803;
        this.g = 2;
    }

    public int generatePrivateKey() {
        int privateKey = Randomizer.generateNumber(2, p - 2);

        logger.debug("Generating the private key: {}", privateKey);

        return privateKey;
    }

    public int generatePublicKey(int privateKey) {
        int publicKey = MathHelper.pow(g, privateKey, p);

        logger.debug("Generating the public key: {}", publicKey);

        return publicKey;
    }

    public Pair<Integer, Integer> encrypt(int message, int publicKeyOfCompanion) {
        if (message < 0 || message >= p) {
            throw new IllegalArgumentException("Invalid message value. Message must be within [0, " + p + ")");
        }

        logger.debug("Encrypting the original message: {}", message);

        int k = Randomizer.generateNumber(1, p - 2);
        Integer r = MathHelper.pow(g, k, p);
        Integer e = (message * MathHelper.pow(publicKeyOfCompanion, k, p)) % p;
        Pair<Integer, Integer> ciphertext = Pair.of(r, e);

        logger.debug("Got the ciphertext: {}", ciphertext);

        return ciphertext;
    }

    public int decrypt(Pair<Integer, Integer> ciphertext, int privateKey) {
        logger.debug("Decrypting the ciphertext: {}", ciphertext);

        int r = ciphertext.getLeft();
        int e = ciphertext.getRight();
        int decryptedMessage = (e * MathHelper.pow(r, p - 1 - privateKey, p)) % p;

        logger.debug("Got the decrypted message: {}", decryptedMessage);

        return decryptedMessage;
    }
}
