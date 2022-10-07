package ru.nsu.fit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.nsu.fit.rsa.SignedMessage;

public class RsaDigitalSignatureTests {
    private Client alice;
    private Client bob;

    @Before
    public void setUp() {
        this.alice = new Client("Alice", 131, 227);
        this.bob = new Client("Bob", 113, 281);
    }

    @Test
    public void testWithoutChangingMessage() {
        SignedMessage signedMessage = alice.signMessage("Hello, Bob!");

        Assert.assertTrue(bob.isAuthenticMessage(signedMessage, alice));
    }

    @Test
    public void testWithChangingMessage() {
        SignedMessage signedMessage = alice.signMessage("Hello, Bob!");
        SignedMessage changedSignedMessage = new SignedMessage("Bye, Bob!", signedMessage.sign());

        Assert.assertFalse(bob.isAuthenticMessage(changedSignedMessage, alice));
    }
}
