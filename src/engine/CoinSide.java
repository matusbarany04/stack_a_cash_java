package engine;

public enum CoinSide {
    TAILS,
    HEADS;


    public static CoinSide flip(CoinSide side) {
        if (side == CoinSide.TAILS) return CoinSide.HEADS;
        else return CoinSide.TAILS;
    }
}
