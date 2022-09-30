package ru.nsu.fit.encryption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.MathHelper;

public class RsaEncryptor {
    private final Logger logger;

    public RsaEncryptor() {
        this.logger = LogManager.getLogger(RsaEncryptor.class);
    }

    public CipherKeys generateCipherKeys(int modulo) {
        int publicKey = 3;
        int privateKey = MathHelper.calculateInverseModulo(publicKey, modulo);

        CipherKeys keys = new CipherKeys(publicKey, privateKey);

        logger.debug("Generating the keys: {}", keys);

        return keys;
    }

    public int encrypt(int message, int publicKeyOfCompanion, int moduloOfCompanion) {
        if (message < 0 || message >= moduloOfCompanion) {
            throw new IllegalArgumentException("Invalid message value. Message must be within [0, " + moduloOfCompanion + ")");
        }

        logger.debug("Encrypting the original message: {}", message);

        int encryptedMessage = MathHelper.pow(message, publicKeyOfCompanion, moduloOfCompanion);

        logger.debug("Got the encrypted message: {}", encryptedMessage);

        return encryptedMessage;
    }

    public int decrypt(int encryptedMessage, int privateKey, int modulo) {
        logger.debug("Decrypting the message: {}", encryptedMessage);

        int decryptedMessage = MathHelper.pow(encryptedMessage, privateKey, modulo);

        logger.debug("Got the decrypted message: {}", decryptedMessage);

        return decryptedMessage;
    }
}
