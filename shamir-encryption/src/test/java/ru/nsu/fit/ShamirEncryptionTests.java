package ru.nsu.fit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShamirEncryptionTests {
    private Client alice;
    private Client bob;

    @Before
    public void setUp() {
        this.alice = new Client("Alice");
        this.bob = new Client("Bob");
    }

    private void checkEncryption(int originalMessage) {
        alice.sendMessage(originalMessage, bob);

        int decryptedMessage = bob.getReceivedMessage();

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
