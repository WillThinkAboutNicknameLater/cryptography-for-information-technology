package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.digitalsignature.CipherKeys;
import ru.nsu.fit.digitalsignature.ElGamalDigitalSigner;
import ru.nsu.fit.digitalsignature.ElGamalKeysGenerator;
import ru.nsu.fit.digitalsignature.SignedMessage;

public class Client {
    private final Logger logger;
    private final ElGamalDigitalSigner digitalSigner;
    private final CipherKeys cipherKeys;
    private final String username;

    public Client(String username) {
        this.logger = LogManager.getLogger(Client.class);
        this.digitalSigner = new ElGamalDigitalSigner();
        this.cipherKeys = new ElGamalKeysGenerator().generateCipherKeys(digitalSigner.getP(), digitalSigner.getG());
        this.username = username;
    }

    public int getPublicKey() {
        return cipherKeys.publicKey();
    }

    public String getUsername() {
        return username;
    }

    public SignedMessage signMessage(String message) {
        logger.info("Signing the message from {}", username);

        return digitalSigner.signMessage(message, cipherKeys.privateKey());
    }

    public boolean isAuthenticMessage(SignedMessage signedMessage, Client sender) {
        logger.info("Checking the authenticity of the message from {}", sender.getUsername());

        boolean result = digitalSigner.isAuthenticMessage(signedMessage, sender.getPublicKey());

        logger.info("Result of authenticity: {}", result);

        return result;
    }
}
