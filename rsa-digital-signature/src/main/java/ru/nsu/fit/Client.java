package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.rsa.CipherKeys;
import ru.nsu.fit.rsa.RsaDigitalSigner;
import ru.nsu.fit.rsa.RsaKeysGenerator;
import ru.nsu.fit.rsa.SignedMessage;

public class Client {
    private final Logger logger;
    private final RsaDigitalSigner digitalSigner;
    private final int modulo;
    private final CipherKeys cipherKeys;
    private final String username;

    public Client(String username, int p, int q) {
        this.logger = LogManager.getLogger(Client.class);
        this.digitalSigner = new RsaDigitalSigner();
        this.modulo = p * q;
        this.cipherKeys = new RsaKeysGenerator().generateCipherKeys((p - 1) * (q - 1));
        this.username = username;
    }

    public int getModulo() {
        return modulo;
    }

    public int getPublicKey() {
        return cipherKeys.publicKey();
    }

    public String getUsername() {
        return username;
    }

    public SignedMessage signMessage(String message) {
        logger.info("Signing the message from {}", username);

        return digitalSigner.signMessage(message, cipherKeys.privateKey(), modulo);
    }

    public boolean isAuthenticMessage(SignedMessage signedMessage, Client sender) {
        logger.info("Checking the authenticity of the message from {}", sender.getUsername());

        boolean result = digitalSigner.isAuthenticMessage(signedMessage, sender.getPublicKey(), sender.getModulo());

        logger.info("Result of authenticity: {}", result);

        return result;
    }
}
