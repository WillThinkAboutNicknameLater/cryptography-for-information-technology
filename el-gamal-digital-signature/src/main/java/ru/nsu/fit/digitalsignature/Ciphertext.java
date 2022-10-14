package ru.nsu.fit.digitalsignature;

public record Ciphertext(int r, int s) {
    @Override
    public String toString() {
        return "(" + r + ", " + s + ")";
    }
}
