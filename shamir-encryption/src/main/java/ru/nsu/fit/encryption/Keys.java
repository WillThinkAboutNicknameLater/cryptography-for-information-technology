package ru.nsu.fit.encryption;

public record Keys(int c, int d) {
    @Override
    public String toString() {
        return "(c = " + c + ", d = " + d + ")";
    }
}
