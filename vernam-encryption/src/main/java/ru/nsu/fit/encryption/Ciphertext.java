package ru.nsu.fit.encryption;

public record Ciphertext(String encryptedMessage, String key) {
    public Ciphertext {
        if (encryptedMessage.length() != key.length()) {
            throw new IllegalArgumentException("Arguments must be the same length");
        }
    }
}
