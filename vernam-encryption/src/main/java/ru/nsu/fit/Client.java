package ru.nsu.fit;

import ru.nsu.fit.encryption.Ciphertext;
import ru.nsu.fit.encryption.VernamEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final Logger logger;
    private final VernamEncryptor encryptor;
    private final String username;

    public Client(String username) {
        this.logger = LogManager.getLogger(Client.class);
        this.encryptor = new VernamEncryptor();
        this.username = username;
    }

    public Ciphertext encryptMessage(String message) {
        logger.debug("{} encrypts the message '{}'", username, message);

        Ciphertext ciphertext = encryptor.encryptMessage(message);

        logger.debug("Got the encrypted message '{}' with key '{}'", ciphertext.encryptedMessage(), ciphertext.key());

        return ciphertext;
    }

    public String decryptMessage(Ciphertext ciphertext) {
        logger.debug("{} decrypts the message '{}' with key '{}'", username, ciphertext.encryptedMessage(), ciphertext.key());

        String decryptedMessage = encryptor.decryptMessage(ciphertext);

        logger.debug("Got the decrypted message '{}'", decryptedMessage);

        return decryptedMessage;
    }
}
