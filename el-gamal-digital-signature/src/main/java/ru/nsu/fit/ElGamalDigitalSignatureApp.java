package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.digitalsignature.SignedMessage;

import java.util.Scanner;

public class ElGamalDigitalSignatureApp {
    private static final Logger logger = LogManager.getLogger(ElGamalDigitalSignatureApp.class);

    private static boolean checkDigitalSignature(String message) {
        Client alice = new Client("Alice");
        Client bob = new Client("Bob");

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
