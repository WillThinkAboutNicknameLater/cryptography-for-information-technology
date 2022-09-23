package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ShamirEncryptionApp {
    private static final Logger logger = LogManager.getLogger(ShamirEncryptionApp.class);

    private static boolean checkEncryption(int originalMessage) {
        Client alice = new Client("Alice");
        Client bob = new Client("Bob");

        alice.sendMessage(originalMessage, bob);

        return originalMessage == bob.getReceivedMessage();
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
