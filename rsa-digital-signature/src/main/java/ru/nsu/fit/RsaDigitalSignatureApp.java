package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.rsa.SignedMessage;

import java.util.Scanner;

public class RsaDigitalSignatureApp {
    private static final Logger logger = LogManager.getLogger(RsaDigitalSignatureApp.class);

    private static boolean checkDigitalSignature(String message) {
        Client alice = new Client("Alice", 131, 227);
        Client bob = new Client("Bob", 113, 281);

        SignedMessage signedMessage = alice.signMessage(message);

        return bob.isAuthenticMessage(signedMessage, alice);
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String message = scanner.nextLine();
            boolean result = checkDigitalSignature(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
