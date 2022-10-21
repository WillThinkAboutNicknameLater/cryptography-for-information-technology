package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.MathHelper;

public class Client {
    private final Logger logger;
    private final String username;

    public Client(String username) {
        this.logger = LogManager.getLogger(Client.class);
        this.username = username;
    }

    public SignedBanknote signBanknote(int n, BankSigner bankSigner) {
        logger.debug("Signing the banknote for {}", username);

        int modulo = bankSigner.getModulo();

        int r = MathHelper.generateCoPrimeNumber(modulo, 2, modulo - 2);
        int inversedR = MathHelper.calculateInverseModulo(r, modulo);

        logger.debug("Encrypt n {} for {}", n, username);

        int encryptedN = n * MathHelper.pow(r, bankSigner.getPublicKey(), modulo) % modulo;

        logger.debug("Got the encrypted n {} for {}", encryptedN, username);

        int encryptedS = bankSigner.sign(encryptedN);

        logger.debug("Decrypt s {} for {}", encryptedS, username);

        int s = encryptedS * inversedR % modulo;

        logger.debug("Got the decrypted s {} for {}", s, username);

        SignedBanknote signedBanknote = new SignedBanknote(n, s);

        logger.debug("Got the signed banknote {} for {}", signedBanknote, username);

        return signedBanknote;
    }
}
