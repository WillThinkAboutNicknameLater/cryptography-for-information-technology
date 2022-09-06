package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.encryption.Ciphertext;

import java.util.Scanner;

public class ElGamalEncryptionApp {
    private static final Logger logger = LogManager.getLogger(ElGamalEncryptionApp.class);

    private static boolean checkEncryption(int originalMessage) {
        Client alice = new Client("Alice");
        Client bob = new Client("Bob");

        Ciphertext aliceCiphertext = alice.encryptMessage(originalMessage, bob.getPublicKey());

        /* Алиса отправляет Бобу шифртекст. Боб получает данную пару */

        Ciphertext bobCiphertext = new Ciphertext(aliceCiphertext);

        int decryptedMessage = bob.decryptMessage(bobCiphertext);

        return originalMessage == decryptedMessage;
    }

    public static void main(String[] args) {
        /* Тесты см. в src.test.java.ru.nsu.fit.ElGamalEncryptionTests */

        Scanner scanner = new Scanner(System.in);
        int message = scanner.nextInt();
        try {
            logger.info("Result of encryption: {}", checkEncryption(message));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
