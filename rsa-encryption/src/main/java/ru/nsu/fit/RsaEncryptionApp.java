package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class RsaEncryptionApp {
    private static final Logger logger = LogManager.getLogger(RsaEncryptionApp.class);

    private static boolean checkEncryption(int originalMessage) {
        Client alice = new Client("Alice", 131, 227);
        Client bob = new Client("Bob", 113, 281);

        int encryptedMessage = alice.encryptMessage(originalMessage, bob);

        /* Алиса отправляет Бобу зашифрованное сообщение. Боб получает данное сообщение */

        int decryptedMessage = bob.decryptMessage(encryptedMessage);

        return originalMessage == decryptedMessage;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int message = scanner.nextInt();
            logger.info("Result of encryption: {}", checkEncryption(message));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
