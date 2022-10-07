package ru.nsu.fit;

public class MentalPokerApp {
    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        Player alice = new Player("Alice");
        Player bob = new Player("Bob");

        dealer.dealCards(alice, bob);
    }
}
