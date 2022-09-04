package ru.nsu.fit.util;

public class MathHelper {
    private MathHelper() {}

    /* Возведение в степень (справа налево) */
    public static int pow(int number, int degree, int modulo) {
        int t = (int) (Math.log(degree) / Math.log(2));

        int[] x = new int[t + 1];
        for (int i = 0; i <= t; ++i) {
            x[i] = degree % 2;
            degree /= 2;
        }

        int result = 1;
        int s = number;
        for (int i = 0; i <= t; ++i) {
            if (x[i] == 1) {
                result = (result * s) % modulo;
            }
            s = (s * s) % modulo;
        }

        return result;
    }
}
