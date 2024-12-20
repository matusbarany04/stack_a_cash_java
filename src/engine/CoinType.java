package engine;

public enum CoinType {
    TENCENTS("gray-400", 40),
    FIFTYCENTS("gray-600", 60),
    EURO("gray-800", 80);

    private final String color;
    private final int radius;

    CoinType(String color, int radius){
        this.color = color;
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public int getRadius() {
        return radius;
    }
}
