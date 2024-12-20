package engine;

class CoinGenerator {

    public static Coin fromType(CoinType type,  CoinSide side) {
        Coin coin = new Coin(type);
        coin.setSide(side);
        return coin;
    }

}
