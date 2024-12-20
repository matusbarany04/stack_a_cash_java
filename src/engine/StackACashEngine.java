package engine;

public class StackACashEngine {

    private final Board board;
    private  CoinSide player;
    private Coin lastCoin;
    private final int MAX_COIN_COUNT = 2;
    private final int BOARD_SIZE = 3;
    private final int[] coinCounter;

    public StackACashEngine(){
        this.board = new Board(BOARD_SIZE, BOARD_SIZE);
        this.lastCoin = null;
        this.player =  CoinSide.TAILS;
        this.coinCounter = new int[CoinType.values().length];
    }

    public int getCoinCount(CoinType type){
        return coinCounter[type.ordinal()];
    }

    private void incrementCoin(CoinType type){
        coinCounter[type.ordinal()]++;
    }

    private void decrementCoin(CoinType type){
        coinCounter[type.ordinal()]--;
    }

    public boolean hasWinner() {
        Cell[][] cells = this.board.getCells();
        int height = cells.length;
        int width = cells[0].length;

        // Check columns
        for (int x = 0; x < width; x++) {;
            boolean columnWinner = true;
            if (!cells[0][x].hasCoin()) continue; // Skip empty columns
            Coin first = cells[0][x].atTop(); // Top coin in the first cell of the column
            for (int y = 1; y < height; y++) {
                Coin coin = cells[y][x].atTop();
                if (coin == null || coin.getSide() != first.getSide()) {
                    columnWinner = false;
                    break;
                }
            }
            if (columnWinner) return true;
        }

        // Check rows
        for (int y = 0; y < height; y++) {
            boolean rowWinner = true;
            if(!cells[y][0].hasCoin()) continue;
            Coin first = cells[y][0].atTop(); // Top coin in the first cell of the row
            for (int x = 1; x < width; x++) {
                Coin coin = cells[y][x].atTop();
                if (coin == null || coin.getSide() != first.getSide()) {
                    rowWinner = false;
                    break;
                }
            }
            if (rowWinner) return true;
        }

        // Check diagonals (top-left to bottom-right)
        Coin first = cells[0][0].atTop(); // Top coin in the top-left corner
        if (first != null) {
            boolean diagonalWinner = true;
            for (int i = 1; i < Math.min(width, height); i++) {
                Coin coin = cells[i][i].atTop();
                if (coin == null || coin.getSide() != first.getSide()) {
                    diagonalWinner = false;
                    break;
                }
            }
            if (diagonalWinner) return true;
        }

        // Check diagonals (top-right to bottom-left)
        first = cells[0][width - 1].atTop(); // Top coin in the top-right corner
        if (first != null) {
            boolean diagonalWinner = true;
            for (int i = 1; i < Math.min(width, height); i++) {
                Coin coin = cells[i][width - 1 - i].atTop();
                if (coin == null || coin.getSide() != first.getSide()) {
                    diagonalWinner = false;
                    break;
                }
            }
            if (diagonalWinner) return true;
        }

        return false; // No winner found
    }


    private void switchPlayer(){
        if (this.player.equals( CoinSide.TAILS)){
            this.player =  CoinSide.HEADS;
        } else{
            this.player =  CoinSide.TAILS;
        }
    }


    public  CoinSide getCurrentPlayer() {
        return player;
    }

    public boolean addCoin(Position position, CoinType type){
        if(this.getCoinCount(type) >= MAX_COIN_COUNT){
            System.out.println("Can't add new coin with type " +  type.name() + " to the board");
            return false;
        }

        Coin coin = new Coin(type);
        coin.setSide(player);
        Cell cell = this.board.getCellAt(position);

        if(cell == null || (cell.hasCoin() && coin.isBiggerOrEqualThan(cell.atTop()))){
            System.out.println("Can not add coin at " +  position.getX() + " " + position.getY());
            return false;
        }
        cell.add(coin);
        incrementCoin(type);
        this.lastCoin = coin;

        switchPlayer();
        return true;
    }


    public boolean flipCoin(Position position){
        Cell cell = this.board.getCellAt(position);

        if(cell == null || !cell.hasCoin()){
            return false;
        }

        Coin coin = cell.atTop();
        if(coin.equals(lastCoin)){
            System.out.println("Can not move coin that was just moved");
            return false;
        }

        coin.flip();
        switchPlayer();
        return true;
    }

    public boolean moveCoin(Position start, Position end) {

        Cell startCell = board.getCellAt(start);
        if(startCell == null || !startCell.hasCoin()){
            System.out.println("Picked empty cell");
            return false; // picked empty cell
        }
        Coin startCoin = startCell.atTop();

        if(startCoin.equals(lastCoin)){
            System.out.println("Can not move coin that was just moved");
            return false;
        }

        Cell endCell = board.getCellAt(end);
        if(endCell == null){
            System.out.println("Picked empty cell");
            return false; // picked empty cell
        }
        Coin endCoin = endCell.atTop();

        if(endCoin != null && !startCoin.isSmallerThan(endCoin)){
            System.out.println("Invalid movement");
            return false; // can't move coin there
        }

        startCell.remove();
        endCell.add(startCoin);

        this.lastCoin = startCoin;

        switchPlayer();
        return true;
    }

    public void printBoard() {
        this.board.printBoard();
    }
}
