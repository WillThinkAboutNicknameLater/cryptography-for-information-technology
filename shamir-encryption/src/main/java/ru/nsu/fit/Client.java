package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.encryption.Keys;
import ru.nsu.fit.encryption.ShamirEncryptor;

public class Client {
    private final Logger logger;
    private final ShamirEncryptor encryptor;
    private final String username;
    private int p;
    private Keys keys;
    private int receivedMessage;

    public Client(String username) {
        this.logger = LogManager.getLogger(Client.class);
        this.encryptor = new ShamirEncryptor();
        this.username = username;
        this.p = 0;
        this.receivedMessage = 0;
    }

    public int getReceivedMessage() {
        return receivedMessage;
    }

    public void sendMessage(int message, Client companion) {
        logger.info("Sending message from {} to {}: {}", username, companion.username, message);

        p = 30803; // or generate randomly
        if (message < 0 || message >= p) {
            throw new IllegalArgumentException("Invalid message value. Message must be within [0, " + p + ")");
        }

        logger.info("Generating and sending p: {}", p);
        companion.p = p;

        keys = encryptor.generateKeys(p - 1);
        logger.info("Generating the keys for {}: {}", username, keys);

        companion.keys = encryptor.generateKeys(p - 1);
        logger.info("Generating the keys for {}: {}", companion.username, companion.keys);

        int x1 = encryptor.calculateX(message, keys.c(), p);
        logger.info("Calculating and sending X1 from {} to {}: {}", username, companion.username, x1);

        int x2 = encryptor.calculateX(x1, companion.keys.c(), p);
        logger.info("Calculating and sending X2 from {} to {}: {}", companion.username, username, x2);

        int x3 = encryptor.calculateX(x2, keys.d(), p);
        logger.info("Calculating and sending X3 from {} to {}: {}", username, companion.username, x3);

        companion.receivedMessage = encryptor.calculateX(x3, companion.keys.d(), p);
        logger.info("Calculating X4 from {}: {}", username, companion.receivedMessage);
    }
}
