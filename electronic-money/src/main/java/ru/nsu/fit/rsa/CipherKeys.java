package ru.nsu.fit.rsa;

public record CipherKeys(int privateKey, int publicKey) {
    @Override
    public String toString() {
        return "(" + privateKey + ", " + publicKey + ')';
    }
}
