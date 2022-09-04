package ru.nsu.fit;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final Logger logger;
    private final ElGamalEncryptor encryptor;
    private final int privateKey;
    private final int publicKey;
    private final String username;

    public Client(String username) {
        this.logger = LogManager.getLogger(Client.class);
        this.encryptor = new ElGamalEncryptor();
        this.privateKey = encryptor.generatePrivateKey();
        this.publicKey = encryptor.generatePublicKey(privateKey);
        this.username = username;
    }

    public int getPublicKey() {
        return publicKey;
    }

    public Pair<Integer, Integer> encryptMessage(int message, int publicKeyOfCompanion) {
        logger.info("Encrypting the message from {}", username);

        return encryptor.encrypt(message, publicKeyOfCompanion);
    }

    public int decryptMessage(Pair<Integer, Integer> ciphertext) {
        logger.info("Decrypting the ciphertext for {}", username);

        return encryptor.decrypt(ciphertext, privateKey);
    }
}
