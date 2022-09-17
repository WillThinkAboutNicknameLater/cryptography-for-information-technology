package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiffieHellmanEncryptionApp {
    private static final Logger logger = LogManager.getLogger(DiffieHellmanEncryptionApp.class);

    private static boolean checkEncryption() {
        Client alice = new Client("Alice");
        Client bob = new Client("Bob");

        int zAB = alice.getSharedSecretKey(bob.getPublicKey());
        int zBA = bob.getSharedSecretKey(alice.getPublicKey());

        return zAB == zBA;
    }

    public static void main(String[] args) {
        try {
            logger.info("Result of encryption: {}", checkEncryption());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
