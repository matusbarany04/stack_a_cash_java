package engine;

public class Cell {
    private Position position;
    private int maxCoins;
    private Coin[] stack;
    private int coinCount;

    public Cell(Position position, int maxCoins){
        this.position = position;
        this.maxCoins = maxCoins;
        this.stack = new Coin[maxCoins];
        this.coinCount = 0;
    }


    public boolean hasCoin() {
        return this.coinCount > 0;
    }

    public boolean add(Coin coin) {
        if(coinCount < maxCoins){
            this.stack[coinCount] = coin;
            coinCount ++;
            return true;
        }
        return false;
    }

    public boolean remove() {
        if(coinCount > 0){
            this.stack[this.coinCount-1] = null;
            coinCount--;
            return true;
        }
        return false;
    }

    public Coin atTop() {
        if (this.hasCoin()) {
            return this.stack[this.coinCount - 1];
        }
        return null;
    }

    public Coin[] getStack() {
        return this.stack;
    }
}


