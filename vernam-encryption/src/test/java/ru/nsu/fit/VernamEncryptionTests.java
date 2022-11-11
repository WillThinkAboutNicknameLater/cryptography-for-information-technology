package ru.nsu.fit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.fit.encryption.Ciphertext;

public class VernamEncryptionTests {
    private Client alice;

    private Client bob;

    @Before
    public void setUp() {
        this.alice = new Client("Alice");
        this.bob = new Client("Bob");
    }

    private void checkEncryption(String originalMessage) {
        Ciphertext ciphertext = alice.encryptMessage(originalMessage);

        String decryptedMessage = bob.decryptMessage(ciphertext);

        Assert.assertEquals(originalMessage, decryptedMessage);
    }

    @Test
    public void testEmptyMessage() {
        checkEncryption("");
    }

    @Test
    public void testNonEmptyMessage() {
        checkEncryption("Hello, World!");
    }
}
