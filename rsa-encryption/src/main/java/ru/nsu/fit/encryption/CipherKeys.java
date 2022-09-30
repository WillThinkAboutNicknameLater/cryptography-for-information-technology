package ru.nsu.fit.encryption;

public record CipherKeys(int privateKey, int publicKey) {
    @Override
    public String toString() {
        return "(" + privateKey + ", " + publicKey + ')';
    }
}
