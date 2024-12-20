import engine.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        runTests();

        StackACashEngine engine = new StackACashEngine();

        while (!engine.hasWinner()) {
            engine.printBoard();
            System.out.println("Player " + engine.getCurrentPlayer().name() + "'s turn!");

            // Ask the player for their action
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String action;

            while (true) {
                System.out.println("Choose an action: 'add' to add a new coin or 'move' to move a coin or 'flip' to flip a coin.");
                action = reader.readLine().trim().toLowerCase();
                if (action.equals("add") || action.equals("move") || action.equals("flip")) {
                    break;
                }
                System.out.println("Invalid input. Please type 'add' or 'move'.");
            }

            // Handle 'add' action
            if (action.equals("add")) {
                Tuple2<Position, CoinType> tuple = askForToAdd(engine.getCurrentPlayer());
                boolean result = engine.addCoin(tuple.getFirst(), tuple.getSecond());
                if (!result) {
                    System.out.println("ERROR: invalid move !");
                }
            }

            // Handle 'move' action
            if (action.equals("move")) {
                Position[] positions = askForMove(engine.getCurrentPlayer());
                boolean result = engine.moveCoin(positions[0], positions[1]);
                if (!result) {
                    System.out.println("ERROR: invalid move !");
                }
            }

            if (action.equals("flip")) {
                Position position = askForFlip(engine.getCurrentPlayer());
                boolean result = engine.flipCoin(position);
                if (!result) {
                    System.out.println("ERROR: invalid move !");
                }

            }
        }

        System.out.println("Winner is " + CoinSide.flip(engine.getCurrentPlayer()).name());
    }

    public static Position[] askForMove( CoinSide player) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Position[] positions = new Position[2];

        for (int i = 0; i < 2; i++) {
            System.out.printf("Player %s, enter the %s position in form: x,y %n",
                    player.name(), i == 0 ? "start" : "end");
            String[] input = reader.readLine().split(",");
            positions[i] = new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        return positions;
    }
    public static Position askForFlip( CoinSide player) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Position position;

        System.out.printf("Player %s, enter the position to flip in form: x,y", player.name());
        String[] input = reader.readLine().split(",");
        position = new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        return position;
    }


    public static Tuple2<Position, CoinType> askForToAdd( CoinSide player) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Ask for the position
        System.out.printf("Player %s, enter the position to place the new coin in form: x,y", player.name());
        String[] input = reader.readLine().split(",");
        Position position = new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]));

        // Ask for the engine.CoinType
        System.out.println("Enter the coin type (choose from: " + String.join(", ", Arrays.toString(CoinType.values())) + "):");
        CoinType coinType;
        while (true) {
            try {
                coinType = CoinType.valueOf(reader.readLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid coin type. Please enter a valid coin type.");
            }
        }

        // Return the new coin with the selected type and position
        return new Tuple2<>(position, coinType);
    }


    public static void assertFalse(String message, boolean condition) {
        if (condition) {
            throw new AssertionError("Test failed: " + message);
        }
    }
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            throw new AssertionError("Test failed: " + message);
        }
    }



    public static void testAddCoin_InvalidPlacement() {
        StackACashEngine engine = new StackACashEngine();

        engine.addCoin(new Position(0, 0), CoinType.FIFTYCENTS);  // Place coin at (0,0)
        boolean result = engine.addCoin(new Position(0, 0), CoinType.FIFTYCENTS);  // Trying to add same type or larger coin on top
        assertFalse("Cannot place same or larger coin on top", result);
    }

    public static void testMoveCoin_inValidMoveHotCoin() {
        StackACashEngine engine = new StackACashEngine();

        engine.addCoin(new Position(0, 0), CoinType.FIFTYCENTS);
        boolean moveResult = engine.moveCoin(new Position(0, 0), new Position(0, 1));  // Moving coin from (0,0) to (0,1)
        assertFalse("Coin should be unsuccessfully moved, it is hot", moveResult);
    }

    public static void testMoveCoin_InvalidMove() {
        StackACashEngine engine = new StackACashEngine();

        engine.addCoin(new Position(0, 0), CoinType.FIFTYCENTS);
        engine.addCoin(new Position(0, 1), CoinType.TENCENTS);  // Prevent placing smaller coin on top
        boolean moveResult = engine.moveCoin(new Position(0, 0), new Position(0, 1));
        assertFalse("Cannot move coin to occupied or invalid position", moveResult);
    }

    public static void testFlipCoin_Invalid() {
        StackACashEngine engine = new StackACashEngine();

        boolean flipResult = engine.flipCoin(new Position(0, 0));  // Trying to flip a coin in an empty position
        assertFalse("Cannot flip coin in empty position", flipResult);
    }

    public static void testVictoryColumn() {
        StackACashEngine engine = new StackACashEngine();

        // Simulate adding coins with Heads or Tails
        engine.addCoin(new Position(0, 0), CoinType.FIFTYCENTS); // TAILS

        engine.addCoin(new Position(0, 1), CoinType.FIFTYCENTS); // HEADS

        engine.addCoin(new Position(0, 2), CoinType.TENCENTS); // TAILS

        engine.addCoin(new Position(1, 2), CoinType.EURO);  // HEADS

        engine.addCoin(new Position(2, 2), CoinType.EURO);  // TAILS

        engine.flipCoin(new Position(0, 1));

        // Now, check for victory
        boolean isWinner = engine.hasWinner();
        assertTrue("Player with Heads should win", isWinner);
    }

    public static void runTests(){
        testFlipCoin_Invalid();
        testAddCoin_InvalidPlacement();
        testMoveCoin_InvalidMove();
        testMoveCoin_inValidMoveHotCoin();
        testVictoryColumn();
    }

}