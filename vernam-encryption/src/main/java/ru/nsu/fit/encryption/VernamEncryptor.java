package ru.nsu.fit.encryption;

import ru.nsu.fit.util.Randomizer;

public class VernamEncryptor {
    private String generateKey(int length) {
        StringBuilder keyBuilder = new StringBuilder();

        for (int i = 0; i < length; ++i) {
            char character = (char) Randomizer.generateNumber(Character.MIN_VALUE, Character.MAX_VALUE);
            keyBuilder.append(character);
        }

        return keyBuilder.toString();
    }

    public Ciphertext encryptMessage(String message) {
        String key = generateKey(message.length());
        StringBuilder encryptedMessageBuilder = new StringBuilder();

        for (int i = 0; i < message.length(); ++i) {
            char character = (char) (message.charAt(i) ^ key.charAt(i));
            encryptedMessageBuilder.append(character);
        }

        return new Ciphertext(encryptedMessageBuilder.toString(), key);
    }

    public String decryptMessage(Ciphertext ciphertext) {
        String encryptedMessage = ciphertext.encryptedMessage();
        String key = ciphertext.key();
        StringBuilder messageBuilder = new StringBuilder();

        for (int i = 0; i < encryptedMessage.length(); ++i) {
            char character = (char) (encryptedMessage.charAt(i) ^ key.charAt(i));
            messageBuilder.append(character);
        }

        return messageBuilder.toString();
    }
}
