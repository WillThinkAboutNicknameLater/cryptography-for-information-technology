package ru.nsu.fit.rsa;

public record SignedMessage(String message, int sign) {
    @Override
    public String toString() {
        return "('" + message + "', " + sign + ')';
    }
}
