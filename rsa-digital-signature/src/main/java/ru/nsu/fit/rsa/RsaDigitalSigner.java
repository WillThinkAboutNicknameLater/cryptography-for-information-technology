package ru.nsu.fit.rsa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.HashGenerator;
import ru.nsu.fit.util.MathHelper;

public class RsaDigitalSigner {
    private final Logger logger;

    public RsaDigitalSigner() {
        this.logger = LogManager.getLogger(RsaDigitalSigner.class);
    }

    public SignedMessage signMessage(String message, int ownPrivateKey, int ownModulo) {
        logger.debug("Signing the message: {}", message);

        int hash = HashGenerator.generateHash(message);
        int sign = MathHelper.pow(hash, ownPrivateKey, ownModulo);
        SignedMessage signedMessage = new SignedMessage(message, sign);

        logger.debug("Got the signed message: {}", signedMessage);

        return signedMessage;
    }

    public boolean isAuthenticMessage(SignedMessage signedMessage, int publicKeyOfSender, int moduloOfSender) {
        logger.debug("Checking the authenticity of the message {}", signedMessage);

        int sentHash = MathHelper.pow(signedMessage.sign(), publicKeyOfSender, moduloOfSender);
        int currentMessageHash = HashGenerator.generateHash(signedMessage.message());
        return sentHash == currentMessageHash % moduloOfSender;
    }
}
