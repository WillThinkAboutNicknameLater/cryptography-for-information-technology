package ru.nsu.fit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RsaEncryptionTests {
    private Client alice;
    private Client bob;

    @Before
    public void setUp() {
        this.alice = new Client("Alice", 131, 227);
        this.bob = new Client("Bob", 113, 281);
    }

    private void checkEncryption(int originalMessage) {
        int encryptedMessage = alice.encryptMessage(originalMessage, bob);

        /* Алиса отправляет Бобу зашифрованное сообщение. Боб получает данное сообщение */

        int decryptedMessage = bob.decryptMessage(encryptedMessage);

        Assert.assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    public void testZeroValue() {
        checkEncryption(0);
    }

    @Test
    public void testPositiveValue() {
        checkEncryption(bob.getModulo() - 1);
    }

    @Test
    public void testNegativeValue() {
        Assert.assertThrows(IllegalArgumentException.class, () -> checkEncryption(-1));
    }

    @Test
    public void testOverflowValue() {
        Assert.assertThrows(IllegalArgumentException.class, () -> checkEncryption(bob.getModulo()));
    }
}