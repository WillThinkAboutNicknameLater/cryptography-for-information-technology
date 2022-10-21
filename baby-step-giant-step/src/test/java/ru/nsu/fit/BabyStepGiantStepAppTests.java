package ru.nsu.fit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BabyStepGiantStepAppTests {
    static Stream<Arguments> argumentsStream() {
        return Stream.of(
                Arguments.arguments(24322, 2, 30203),
                Arguments.arguments(21740, 2, 30323),
                Arguments.arguments(28620, 2, 30539),
                Arguments.arguments(16190, 2, 30803),
                Arguments.arguments(30994, 5, 31607)
        );
    }

    @ParameterizedTest
    @MethodSource("argumentsStream")
    void test(int number, int base, int modulo) {
        int x = MathHelper.calculateLogarithmModulo(number, base, modulo);
        int y = MathHelper.pow(base, x, modulo);

        assertEquals(number, y);
    }
}
