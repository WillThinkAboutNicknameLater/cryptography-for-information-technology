package ru.nsu.fit.digitalsignature;

public record CipherKeys(int privateKey, int publicKey) {
    @Override
    public String toString() {
        return "(" + privateKey + ", " + publicKey + ')';
    }
}
