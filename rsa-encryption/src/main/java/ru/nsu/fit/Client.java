package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.encryption.CipherKeys;
import ru.nsu.fit.encryption.RsaEncryptor;

public class Client {
    private final Logger logger;
    private final RsaEncryptor encryptor;
    private final int modulo;
    private final CipherKeys cipherKeys;
    private final String username;

    public Client(String username, int p, int q) {
        this.logger = LogManager.getLogger(Client.class);
        this.encryptor = new RsaEncryptor();
        this.modulo = p * q;
        this.cipherKeys = encryptor.generateCipherKeys((p - 1) * (q - 1));
        this.username = username;
    }

    public int getModulo() {
        return modulo;
    }

    public int getPublicKey() {
        return cipherKeys.publicKey();
    }

    public int encryptMessage(int message, Client companion) {
        logger.info("Encrypting the message from {}", username);

        return encryptor.encrypt(message, companion.getPublicKey(), companion.getModulo());
    }

    public int decryptMessage(int encryptedMessage) {
        logger.info("Decrypting the ciphertext for {}", username);

        return encryptor.decrypt(encryptedMessage, cipherKeys.privateKey(), modulo);
    }
}
