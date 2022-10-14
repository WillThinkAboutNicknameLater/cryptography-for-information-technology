package ru.nsu.fit.digitalsignature;

public record SignedMessage(String message, Ciphertext sign) {
    @Override
    public String toString() {
        return "('" + message + "', " + sign.r() + ", " + sign.s() + ")";
    }
}
