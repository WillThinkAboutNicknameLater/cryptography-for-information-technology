package ru.nsu.fit;

import ru.nsu.fit.encryption.CipherKeys;
import ru.nsu.fit.encryption.DiffieHellmanEncryptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private final Logger logger;
    private final DiffieHellmanEncryptor encryptor;
    private final CipherKeys cipherKeys;
    private final String username;

    public Client(String username) {
        this.logger = LogManager.getLogger(Client.class);
        this.encryptor = new DiffieHellmanEncryptor();
        this.cipherKeys = encryptor.generateCipherKeys();
        this.username = username;
    }

    public int getPublicKey() {
        return cipherKeys.getPublicKey();
    }

    public int getSharedSecretKey(int publicKeyOfCompanion) {
        int ownPrivateKey = cipherKeys.privateKey();

        logger.info("Getting the shared secret key with own private key: {} and public key of companion: {}", ownPrivateKey, publicKeyOfCompanion);

        return encryptor.calculateSharedSecretKey(ownPrivateKey, publicKeyOfCompanion);
    }
}
