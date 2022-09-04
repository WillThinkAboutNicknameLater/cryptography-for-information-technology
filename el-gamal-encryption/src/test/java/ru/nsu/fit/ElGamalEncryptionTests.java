package ru.nsu.fit;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ElGamalEncryptionTests {
    private Client alice;
    private Client bob;

    @Before
    public void setUp() {
        this.alice = new Client("Alice");
        this.bob = new Client("Bob");
    }

    public void checkEncryption(int originalMessage) {
        Pair<Integer, Integer> aliceCiphertext = alice.encryptMessage(originalMessage, bob.getPublicKey());

        /* Алиса отправляет Бобу шифртекст. Боб получает данную пару */

        Pair<Integer, Integer> bobCiphertext = Pair.of(aliceCiphertext);

        int decryptedMessage = bob.decryptMessage(bobCiphertext);

        Assert.assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    public void testZeroValue() {
        checkEncryption(0);
    }

    @Test
    public void testPositiveValue() {
        checkEncryption(30802);
    }

    @Test
    public void testNegativeValue() {
        Assert.assertThrows(IllegalArgumentException.class, () -> checkEncryption(-1));
    }

    @Test
    public void testOverflowValue() {
        Assert.assertThrows(IllegalArgumentException.class, () -> checkEncryption(30803));
    }
}
