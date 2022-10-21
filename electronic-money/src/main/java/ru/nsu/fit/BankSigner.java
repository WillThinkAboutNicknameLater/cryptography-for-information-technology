package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.rsa.CipherKeys;
import ru.nsu.fit.rsa.RsaKeysGenerator;
import ru.nsu.fit.util.MathHelper;

public class BankSigner {
    private final Logger logger;
    private final int modulo;
    private final CipherKeys cipherKeys;

    public BankSigner() {
        int p = 131;
        int q = 227;
        this.logger = LogManager.getLogger(BankSigner.class);
        this.modulo = p * q;
        this.cipherKeys = new RsaKeysGenerator().generateCipherKeys((p - 1) * (q - 1));
    }

    public int getModulo() {
        return modulo;
    }

    public int getPublicKey() {
        return cipherKeys.publicKey();
    }

    public int sign(int n) {
        logger.debug("Signing n: {}", n);

        int s = MathHelper.pow(n, cipherKeys.privateKey(), modulo);

        logger.debug("Got the signed s: {}", s);

        return s;
    }

    public boolean checkAuthenticityOfBanknote(SignedBanknote signedBanknote) {
        logger.debug("Checking authenticity of the banknote: {}", signedBanknote);

        boolean isAuthentic = signedBanknote.sign() == MathHelper.pow(signedBanknote.number(), cipherKeys.privateKey(), modulo);

        logger.debug("Result of checking: {}", isAuthentic);

        return isAuthentic;
    }
}
