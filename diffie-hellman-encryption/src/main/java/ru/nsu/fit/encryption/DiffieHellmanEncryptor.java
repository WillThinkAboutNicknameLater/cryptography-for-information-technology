package ru.nsu.fit.encryption;

import ru.nsu.fit.util.MathHelper;
import ru.nsu.fit.util.Randomizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiffieHellmanEncryptor {
    private final Logger logger;
    private final int p;
    private final int g;

    public DiffieHellmanEncryptor() {
        this.logger = LogManager.getLogger(DiffieHellmanEncryptor.class);
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

    public int calculateSharedSecretKey(int ownPrivateKey, int publicKeyOfCompanion) {
        if (ownPrivateKey < 0 || ownPrivateKey >= p || publicKeyOfCompanion < 0 || publicKeyOfCompanion >= p) {
            throw new IllegalArgumentException("Invalid key value. Key must be within [0, " + p + ")");
        }

        logger.debug("Calculating the shared secret key with own private key: {} and public key of companion: {}", ownPrivateKey, publicKeyOfCompanion);

        int sharedSecretKey = MathHelper.pow(publicKeyOfCompanion, ownPrivateKey, p);

        logger.debug("Got the shared secret key: {}", sharedSecretKey);

        return sharedSecretKey;
    }
}
