package ru.nsu.fit.util;

import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

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

    /* Обобщенный алгоритм Эвклида */
    public static EuclidAlgorithmResponse calculateEuclidAlgorithm(int a, int b) {
        if (a < b) {
            int oldA = a;
            a = b;
            b = oldA;
        }

        MutableTriple<Integer, Integer, Integer> u = new MutableTriple<>(a, 1, 0);
        MutableTriple<Integer, Integer, Integer> v = new MutableTriple<>(b, 0, 1);
        while (v.getLeft() != 0) {
            int q = u.getLeft() / v.getLeft();
            Triple<Integer, Integer, Integer> t = Triple.of(u.getLeft() % v.getLeft(),
                                                            u.getMiddle() - q * v.getMiddle(),
                                                            u.getRight() - q * v.getRight());

            u.setLeft(v.getLeft());
            u.setMiddle(v.getMiddle());
            u.setRight(v.getRight());

            v.setLeft(t.getLeft());
            v.setMiddle(t.getMiddle());
            v.setRight(t.getRight());
        }

        return new EuclidAlgorithmResponse(u.getLeft(), u.getMiddle(), u.getRight());
    }

    /* Инверсия по модулю */
    public static int calculateInverseModulo(int number, int modulo) {
        EuclidAlgorithmResponse euclidResponse = calculateEuclidAlgorithm(modulo, number);

        int inverseNumber = euclidResponse.y();
        if (inverseNumber < 0) {
            inverseNumber += modulo;
        }

        return inverseNumber;
    }
}
