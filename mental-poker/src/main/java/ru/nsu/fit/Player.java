package ru.nsu.fit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.util.KeysGenerator;
import ru.nsu.fit.util.MathHelper;
import ru.nsu.fit.util.Randomizer;

import java.util.*;

public class Player {
    private final Logger logger;
    private final String username;
    private CardType card;
    private final int modulo;
    private final Keys keys;
    private Map<Integer, CardType> cardDictionary;

    public Player(String username) {
        this.logger = LogManager.getLogger(Player.class);
        this.username = username;
        this.card = null;
        this.modulo = 30803;
        this.keys = new KeysGenerator().generateKeys(modulo - 1);
        this.cardDictionary = new HashMap<>();
    }

    public int getPublicKey() {
        return keys.publicKey();
    }

    public void setCard(int cardId) {
        int u = encryptNumber(cardId);
        this.card = cardDictionary.get(u);

        logger.debug("{} received card {}", username, card);
    }

    public Map<Integer, CardType> getCardDictionary() {
        return cardDictionary;
    }

    public void setCardDictionary(Map<Integer, CardType> cardDictionary) {
        this.cardDictionary = cardDictionary;
    }

    public void fillCardDictionary() {
        for (CardType currentCard : CardType.values()) {
            int randomValue = 0;
            do {
                randomValue = Randomizer.generateNumber(1, modulo - 1);
            } while (cardDictionary.containsKey(randomValue));

            cardDictionary.put(randomValue, currentCard);
        }

        logger.debug("{} filled out the card dictionary {}", username, cardDictionary);
    }

    public List<Integer> encryptSequence(Collection<Integer> sequence) {
        logger.debug("{} starts to encrypt the sequence {}", username, sequence);

        List<Integer> encryptedSequence = new ArrayList<>();

        for (Integer value : sequence) {
            int u = MathHelper.pow(value, keys.privateKey(), modulo);
            encryptedSequence.add(u);
        }

        Collections.shuffle(encryptedSequence);

        logger.debug("{} encrypted and shuffled the sequence and got {}", username, encryptedSequence);

        return encryptedSequence;
    }

    public int encryptNumber(int number) {
        int encryptedNumber = MathHelper.pow(number, getPublicKey(), modulo);

        logger.debug("{} encrypted the number and got {}", username, encryptedNumber);

        return encryptedNumber;
    }

    public int removeRandomElement(List<Integer> sequence) {
        int pickedNumber = sequence.remove(Randomizer.generateNumber(0, sequence.size() - 1));

        logger.debug("{} picked the number {}", username, pickedNumber);

        return pickedNumber;
    }
}
