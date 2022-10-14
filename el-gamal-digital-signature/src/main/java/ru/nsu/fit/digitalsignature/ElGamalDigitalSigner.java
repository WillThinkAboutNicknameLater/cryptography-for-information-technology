package ru.nsu.fit.digitalsignature;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.HashGenerator;
import ru.nsu.fit.util.MathHelper;

public class ElGamalDigitalSigner {
    private final Logger logger;
    private final int p;
    private final int g;

    public ElGamalDigitalSigner() {
        this.logger = LogManager.getLogger(ElGamalDigitalSigner.class);
        this.p = 31259;
        this.g = 2;
    }

    public int getP() {
        return p;
    }

    public int getG() {
        return g;
    }

    public SignedMessage signMessage(String message, int ownPrivateKey) {
        logger.debug("Signing the message: {}", message);

        int hash = hashToCorrectView(HashGenerator.generateHash(message));
        int k = MathHelper.generateCoPrimeNumber(p - 1, 2, p - 2);
        int r = MathHelper.pow(g, k, p);
        int u = (hash - ownPrivateKey * r) % (p - 1);
        u = u < 0 ? u + p - 1 : u;
        int s = (MathHelper.calculateInverseModulo(k, p - 1) * u) % (p - 1);
        Ciphertext sign = new Ciphertext(r, s);
        SignedMessage signedMessage = new SignedMessage(message, sign);

        logger.debug("Got the signed message: {}", signedMessage);

        return signedMessage;
    }

    public boolean isAuthenticMessage(SignedMessage signedMessage, int publicKeyOfSender) {
        logger.debug("Checking the authenticity of the message {}", signedMessage);

        int currentMessageHash = hashToCorrectView(HashGenerator.generateHash(signedMessage.message()));
        int r = signedMessage.sign().r();
        int s = signedMessage.sign().s();

        return (MathHelper.pow(publicKeyOfSender, r, p) * MathHelper.pow(r, s, p)) % p == MathHelper.pow(g, currentMessageHash, p);
    }

    private int hashToCorrectView(int hash) {
        hash %= p;
        if (hash < 2) {
            hash += 2;
        }

        return hash;
    }
}