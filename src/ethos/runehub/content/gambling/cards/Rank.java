package ethos.runehub.content.gambling.cards;

public enum Rank {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE;

    public static int getValue(Rank rank) {
        if (rank == JACK || rank == QUEEN || rank == KING)
            return 10;
        else if (rank == ACE)
            return 11;
        return rank.ordinal()+2;
    }
}
