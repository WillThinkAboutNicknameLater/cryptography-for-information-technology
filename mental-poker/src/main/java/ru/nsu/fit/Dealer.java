package ru.nsu.fit;

import java.util.List;
import java.util.Map;

public class Dealer {
    public void dealCards(Player firstPlayer, Player secondPlayer) {
        firstPlayer.fillCardDictionary();
        Map<Integer, CardType> cardDictionary = firstPlayer.getCardDictionary();
        secondPlayer.setCardDictionary(cardDictionary);

        List<Integer> sequenceForSecondPlayer = firstPlayer.encryptSequence(cardDictionary.keySet());
        int pickedNumberForFirstPlayer = secondPlayer.removeRandomElement(sequenceForSecondPlayer);
        firstPlayer.setCard(pickedNumberForFirstPlayer);

        List<Integer> sequenceForFirstPlayer = secondPlayer.encryptSequence(sequenceForSecondPlayer);
        int pickedNumberForSecondPlayer = firstPlayer.removeRandomElement(sequenceForFirstPlayer);
        int encryptedNumberForSecondPlayer = firstPlayer.encryptNumber(pickedNumberForSecondPlayer);
        secondPlayer.setCard(encryptedNumberForSecondPlayer);
    }
}
