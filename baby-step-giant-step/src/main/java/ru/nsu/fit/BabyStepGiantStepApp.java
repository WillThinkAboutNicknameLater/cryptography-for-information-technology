package ru.nsu.fit;

public class BabyStepGiantStepApp {
    public static void main(String[] args) {
        int base = 2;
        int number = 7;
        int modulo = 19;
        int x = MathHelper.calculateLogarithmModulo(number, base, modulo);
        System.out.println(x);
    }
}
