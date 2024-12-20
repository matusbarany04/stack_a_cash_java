package engine;

public class Board {

    private final int width;
    private final int height;
    private final Cell[][] cells;

    private final int MAX_COINS = 3;

    public Board(int width, int height){
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        this.init();
    }

    public Cell getCellAt(Position position){
        return this.cells[position.getY()][position.getX()];
    }


    private void init(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.cells[y][x] = new Cell(new Position(x,y), MAX_COINS);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void printBoard() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = this.cells[y][x];
                if(!cell.hasCoin()){
                    continue;
                }
                System.out.printf("engine.Cell[%d,%d]: ", x, y);
                if (cell.hasCoin()) {
                    Coin[] stack = cell.getStack();
                    for (Coin coin : stack) {
                        if(coin == null){
                            continue;
                        }
                         CoinSide side = coin.getSide();
                        String text = side ==  CoinSide.TAILS ? "T" : "H";
                        System.out.printf("%s%d ", text, coin.getRadius());
                    }
                } else {
                    System.out.print("No coin");
                }
                System.out.println();
            }
        }
        System.out.println();

    }

}
