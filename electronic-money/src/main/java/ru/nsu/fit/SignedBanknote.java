package ru.nsu.fit;

public record SignedBanknote(int number, int sign) {
    @Override
    public String toString() {
        return "(" + number + ", " + sign + ")";
    }
}
