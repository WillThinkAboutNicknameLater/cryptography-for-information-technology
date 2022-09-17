package ru.nsu.fit.encryption;

public record CipherKeys(int privateKey, int publicKey) {
    public int getPrivateKey() {
        return privateKey;
    }

    public int getPublicKey() {
        return publicKey;
    }
}
