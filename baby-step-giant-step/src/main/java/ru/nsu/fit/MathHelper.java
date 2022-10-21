package ru.nsu.fit;

import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MathHelper {
    private MathHelper() {}

    /* Возведение в степень (справа налево) */
    public static int pow(int number, int degree, int modulo) {
        if (degree == 0) {
            return 1;
        }

        int t = (int) (Math.log(degree) / Math.log(2));

        int[] x = new int[t + 1];
        for (int i = 0; i <= t; ++i) {
            x[i] = degree % 2;
            degree /= 2;
        }

        long result = 1;
        long s = number;
        for (int i = 0; i <= t; ++i) {
            if (x[i] == 1) {
                result = (result * s) % modulo;
            }
            s = (s * s) % modulo;
        }

        return (int) result;
    }

    /* Шаг младенца, шаг великана */
    public static int calculateLogarithmModulo(int number, int base, int modulo) {
        int n = (int) Math.ceil(Math.sqrt(modulo));

        List<MutableTriple<Integer, Integer, Integer>> bothRows = new ArrayList<>();

        for (int index = 0; index < n; ++index) {
            bothRows.add(new MutableTriple<>(pow(base, index, modulo) * number % modulo, index, 1));
            bothRows.add(new MutableTriple<>(pow(base, (index + 1) * n, modulo) % modulo, index + 1, 2));
        }

        bothRows.sort(Comparator.comparing(MutableTriple::getLeft));

        int minResult = Integer.MAX_VALUE;
        for (int index = 0; index < 2 * n - 1; ++index) {
            Triple<Integer, Integer, Integer> first = bothRows.get(index);
            Triple<Integer, Integer, Integer> second = bothRows.get(index + 1);

            if (first.getLeft().equals(second.getLeft()) && !first.getRight().equals(second.getRight())) {
                int i = first.getRight().equals(1) ? second.getMiddle() : first.getMiddle();
                int j = first.getRight().equals(1) ? first.getMiddle() : second.getMiddle();
                int result = i * n - j;

                if (minResult > result) {
                    minResult = result;
                }
            }
        }

        return minResult;
    }
}
