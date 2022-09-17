package ru.nsu.fit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiffieHellmanEncryptionTests {
    private Client alice;
    private Client bob;

    @Before
    public void setUp() {
        this.alice = new Client("Alice");
        this.bob = new Client("Bob");
    }

    @Test
    public void testEncryption() {
        int zAB = alice.getSharedSecretKey(bob.getPublicKey());
        int zBA = bob.getSharedSecretKey(alice.getPublicKey());

        Assert.assertEquals(zAB, zBA);
    }

    @Test
    public void testNegativeKey() {
        Assert.assertThrows(IllegalArgumentException.class, () -> alice.getSharedSecretKey(-1));
    }

    @Test
    public void testOverflowKey() {
        Assert.assertThrows(IllegalArgumentException.class, () -> alice.getSharedSecretKey(30803));
    }
}
