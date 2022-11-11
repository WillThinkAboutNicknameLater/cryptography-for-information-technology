package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.encryption.Ciphertext;

import java.util.Scanner;

public class VernamEncryptionApp {
    private static final Logger logger = LogManager.getLogger(VernamEncryptionApp.class);

    private static boolean checkEncryption(String originalMessage) {
        Client alice = new Client("Alice");
        Client bob = new Client("Bob");

        Ciphertext ciphertext = alice.encryptMessage(originalMessage);

        /* The encrypted message is transmitted over an open channel, and the key is transmitted over a private channel */

        String decryptedMessage = bob.decryptMessage(ciphertext);

        return originalMessage.equals(decryptedMessage);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String message = scanner.nextLine();
            logger.info("Result of encryption: {}", checkEncryption(message));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
