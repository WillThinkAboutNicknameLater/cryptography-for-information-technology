package ru.nsu.fit.encryption;

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

    private int generatePrivateKey() {
        int privateKey = Randomizer.generateNumber(2, p - 2);

        logger.debug("Generating the private key: {}", privateKey);

        return privateKey;
    }

    private int generatePublicKey(int privateKey) {
        int publicKey = MathHelper.pow(g, privateKey, p);

        logger.debug("Generating the public key: {}", publicKey);

        return publicKey;
    }

    public CipherKeys generateCipherKeys() {
        int privateKey = generatePrivateKey();
        int publicKey = generatePublicKey(privateKey);
        return new CipherKeys(privateKey, publicKey);
    }

    public Ciphertext encrypt(int message, int publicKeyOfCompanion) {
        if (message < 0 || message >= p) {
            throw new IllegalArgumentException("Invalid message value. Message must be within [0, " + p + ")");
        }

        logger.debug("Encrypting the original message: {}", message);

        int k = Randomizer.generateNumber(1, p - 2);
        int r = MathHelper.pow(g, k, p);
        int e = (message * MathHelper.pow(publicKeyOfCompanion, k, p)) % p;
        Ciphertext ciphertext = new Ciphertext(r, e);

        logger.debug("Got the ciphertext: {}", ciphertext);

        return ciphertext;
    }

    public int decrypt(Ciphertext ciphertext, int privateKey) {
        logger.debug("Decrypting the ciphertext: {}", ciphertext);

        int r = ciphertext.getR();
        int e = ciphertext.getE();
        int decryptedMessage = (e * MathHelper.pow(r, p - 1 - privateKey, p)) % p;

        logger.debug("Got the decrypted message: {}", decryptedMessage);

        return decryptedMessage;
    }
}
