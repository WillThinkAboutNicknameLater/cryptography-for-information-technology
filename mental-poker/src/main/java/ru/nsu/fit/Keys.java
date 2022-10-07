package ru.nsu.fit;

public record Keys(int privateKey, int publicKey) {
    @Override
    public String toString() {
        return "(" + privateKey + ", " + publicKey + ')';
    }
}