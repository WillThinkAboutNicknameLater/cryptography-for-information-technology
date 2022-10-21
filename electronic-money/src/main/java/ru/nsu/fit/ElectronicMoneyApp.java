package ru.nsu.fit;

import ru.nsu.fit.util.Randomizer;

public class ElectronicMoneyApp {
    public static void main(String[] args) {
        BankSigner bankSigner = new BankSigner();
        Client alice = new Client("Alice");

        int n = Randomizer.generateNumber(1, bankSigner.getModulo() - 1);
        SignedBanknote signedBanknote = alice.signBanknote(n, bankSigner);
        boolean resultOfChecking = bankSigner.checkAuthenticityOfBanknote(signedBanknote);
    }
}
