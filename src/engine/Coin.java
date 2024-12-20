package engine;

public class Coin {
    private CoinSide side;
    private boolean selected;
    private final CoinType type;

    public Coin(CoinType type) {
        this.side = CoinSide.HEADS;
        this.selected = false;
        this.type = type;
    }

    public int getRadius() {
        return this.type.getRadius();
    }

    public String getColor() {
        return this.type.getColor();
    }

    public CoinSide getSide() {
        return side;
    }

    public void select() {
        this.selected = true;
    }

    public void deselect() {
        this.selected = false;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isTails() {
        return this.side == CoinSide.TAILS;
    }

    public boolean isHeads() {
        return this.side == CoinSide.HEADS;
    }

    public void setTails() {
        this.side = CoinSide.TAILS;
    }

    public void setHeads() {
        this.side = CoinSide.HEADS;
    }

    public void setSide(CoinSide side) {
        this.side = side;
    }

    public void flip() {
        if (this.side == CoinSide.TAILS) {
            this.side = CoinSide.HEADS;
        }else{
            this.side = CoinSide.TAILS;
        }
    }
    
    public boolean isSmallerThan(Coin coin){
        return this.getRadius() < coin.getRadius();
    }

    public boolean isBiggerOrEqualThan(Coin coin){
        return this.getRadius() >= coin.getRadius();
    }
}
