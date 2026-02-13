import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

/**
 * Comprehensive test suite for Project class (Tic Tac Toe functionality)
 * Tests cover main game logic, board operations, and edge cases
 */
public class ProjectTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // Helper method to invoke private static methods using reflection
    private Object invokePrivateMethod(String methodName, Class<?>[] paramTypes, Object... args) throws Exception {
        Method method = Project.class.getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(null, args);
    }

    // Tests for initializeTicTacToe
    @Test
    @DisplayName("Test initializeTicTacToe creates empty 3x3 board")
    public void testInitializeTicTacToe3x3() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', board[i][j], "Board position [" + i + "][" + j + "] should be empty");
            }
        }
    }

    @Test
    @DisplayName("Test initializeTicTacToe creates empty 5x5 board")
    public void testInitializeTicTacToe5x5() throws Exception {
        char[][] board = new char[5][5];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(' ', board[i][j], "Board position [" + i + "][" + j + "] should be empty");
            }
        }
    }

    @Test
    @DisplayName("Test initializeTicTacToe creates empty 9x9 board (maximum size)")
    public void testInitializeTicTacToe9x9() throws Exception {
        char[][] board = new char[9][9];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 9);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(' ', board[i][j], "Board position [" + i + "][" + j + "] should be empty");
            }
        }
    }

    // Tests for isValidMove
    @Test
    @DisplayName("Test isValidMove returns true for valid empty position")
    public void testIsValidMoveOnEmptyPosition() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 0, 0);

        assertTrue(result, "Valid move on empty position should return true");
    }

    @Test
    @DisplayName("Test isValidMove returns false for occupied position")
    public void testIsValidMoveOnOccupiedPosition() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[1][1] = 'X';

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 1, 1);

        assertFalse(result, "Valid move on occupied position should return false");
    }

    @Test
    @DisplayName("Test isValidMove returns false for negative row")
    public void testIsValidMoveNegativeRow() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, -1, 0);

        assertFalse(result, "Move with negative row should return false");
    }

    @Test
    @DisplayName("Test isValidMove returns false for negative column")
    public void testIsValidMoveNegativeColumn() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 0, -1);

        assertFalse(result, "Move with negative column should return false");
    }

    @Test
    @DisplayName("Test isValidMove returns false for row out of bounds")
    public void testIsValidMoveRowOutOfBounds() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 3, 0);

        assertFalse(result, "Move with row out of bounds should return false");
    }

    @Test
    @DisplayName("Test isValidMove returns false for column out of bounds")
    public void testIsValidMoveColumnOutOfBounds() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 0, 3);

        assertFalse(result, "Move with column out of bounds should return false");
    }

    @Test
    @DisplayName("Test isValidMove with both indices out of bounds")
    public void testIsValidMoveBothOutOfBounds() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 10, 10);

        assertFalse(result, "Move with both indices out of bounds should return false");
    }

    @Test
    @DisplayName("Test isValidMove on 5x5 board boundary")
    public void testIsValidMoveOn5x5Boundary() throws Exception {
        char[][] board = new char[5][5];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 5);

        boolean result = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 4, 4);

        assertTrue(result, "Move on valid boundary position should return true");
    }

    // Tests for isGameWon
    @Test
    @DisplayName("Test isGameWon detects horizontal win in first row")
    public void testIsGameWonHorizontalFirstRow() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect horizontal win in first row");
    }

    @Test
    @DisplayName("Test isGameWon detects horizontal win in middle row")
    public void testIsGameWonHorizontalMiddleRow() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[1][0] = 'O';
        board[1][1] = 'O';
        board[1][2] = 'O';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'O');

        assertTrue(result, "Should detect horizontal win in middle row");
    }

    @Test
    @DisplayName("Test isGameWon detects horizontal win in last row")
    public void testIsGameWonHorizontalLastRow() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[2][0] = 'X';
        board[2][1] = 'X';
        board[2][2] = 'X';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect horizontal win in last row");
    }

    @Test
    @DisplayName("Test isGameWon detects vertical win in first column")
    public void testIsGameWonVerticalFirstColumn() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[1][0] = 'X';
        board[2][0] = 'X';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect vertical win in first column");
    }

    @Test
    @DisplayName("Test isGameWon detects vertical win in middle column")
    public void testIsGameWonVerticalMiddleColumn() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][1] = 'O';
        board[1][1] = 'O';
        board[2][1] = 'O';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'O');

        assertTrue(result, "Should detect vertical win in middle column");
    }

    @Test
    @DisplayName("Test isGameWon detects vertical win in last column")
    public void testIsGameWonVerticalLastColumn() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][2] = 'X';
        board[1][2] = 'X';
        board[2][2] = 'X';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect vertical win in last column");
    }

    @Test
    @DisplayName("Test isGameWon detects diagonal win (top-left to bottom-right)")
    public void testIsGameWonDiagonalTopLeftToBottomRight() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[1][1] = 'X';
        board[2][2] = 'X';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect diagonal win from top-left to bottom-right");
    }

    @Test
    @DisplayName("Test isGameWon detects diagonal win (top-right to bottom-left)")
    public void testIsGameWonDiagonalTopRightToBottomLeft() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][2] = 'O';
        board[1][1] = 'O';
        board[2][0] = 'O';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'O');

        assertTrue(result, "Should detect diagonal win from top-right to bottom-left");
    }

    @Test
    @DisplayName("Test isGameWon returns false for empty board")
    public void testIsGameWonEmptyBoard() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertFalse(result, "Empty board should not have a winner");
    }

    @Test
    @DisplayName("Test isGameWon returns false when no complete line")
    public void testIsGameWonNoCompleteLine() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'X';
        board[1][0] = 'O';
        board[1][1] = 'X';
        board[1][2] = 'O';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertFalse(result, "Board with no complete line should not have a winner");
    }

    @Test
    @DisplayName("Test isGameWon on 5x5 board with horizontal win")
    public void testIsGameWonOn5x5BoardHorizontal() throws Exception {
        char[][] board = new char[5][5];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 5);
        for (int i = 0; i < 5; i++) {
            board[2][i] = 'X';
        }

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect horizontal win on 5x5 board");
    }

    @Test
    @DisplayName("Test isGameWon on 5x5 board with diagonal win")
    public void testIsGameWonOn5x5BoardDiagonal() throws Exception {
        char[][] board = new char[5][5];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 5);
        for (int i = 0; i < 5; i++) {
            board[i][i] = 'O';
        }

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'O');

        assertTrue(result, "Should detect diagonal win on 5x5 board");
    }

    @Test
    @DisplayName("Test isGameWon distinguishes between X and O")
    public void testIsGameWonDistinguishesPlayers() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';

        boolean resultX = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');
        boolean resultO = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'O');

        assertTrue(resultX, "Should detect X won");
        assertFalse(resultO, "Should not detect O won when X has winning line");
    }

    // Tests for isBoardFull
    @Test
    @DisplayName("Test isBoardFull returns false for empty board")
    public void testIsBoardFullEmptyBoard() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);

        boolean result = (boolean) invokePrivateMethod("isBoardFull",
            new Class<?>[]{char[][].class}, (Object) board);

        assertFalse(result, "Empty board should not be full");
    }

    @Test
    @DisplayName("Test isBoardFull returns false for partially filled board")
    public void testIsBoardFullPartiallyFilled() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[1][1] = 'X';

        boolean result = (boolean) invokePrivateMethod("isBoardFull",
            new Class<?>[]{char[][].class}, (Object) board);

        assertFalse(result, "Partially filled board should not be full");
    }

    @Test
    @DisplayName("Test isBoardFull returns true for completely filled board")
    public void testIsBoardFullCompletelyFilled() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'X';
        board[1][0] = 'O';
        board[1][1] = 'X';
        board[1][2] = 'O';
        board[2][0] = 'O';
        board[2][1] = 'X';
        board[2][2] = 'O';

        boolean result = (boolean) invokePrivateMethod("isBoardFull",
            new Class<?>[]{char[][].class}, (Object) board);

        assertTrue(result, "Completely filled board should be full");
    }

    @Test
    @DisplayName("Test isBoardFull returns false when one cell is empty")
    public void testIsBoardFullAlmostFull() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'X';
        board[1][0] = 'O';
        board[1][1] = 'X';
        board[1][2] = 'O';
        board[2][0] = 'O';
        board[2][1] = 'X';
        // board[2][2] is empty

        boolean result = (boolean) invokePrivateMethod("isBoardFull",
            new Class<?>[]{char[][].class}, (Object) board);

        assertFalse(result, "Board with one empty cell should not be full");
    }

    @Test
    @DisplayName("Test isBoardFull on 5x5 board completely filled")
    public void testIsBoardFullOn5x5Board() throws Exception {
        char[][] board = new char[5][5];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 5);

        // Fill the entire board
        char player = 'X';
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = player;
                player = (player == 'X') ? 'O' : 'X';
            }
        }

        boolean result = (boolean) invokePrivateMethod("isBoardFull",
            new Class<?>[]{char[][].class}, (Object) board);

        assertTrue(result, "Completely filled 5x5 board should be full");
    }

    // Test for interface method
    @Test
    @DisplayName("Test isValidMove interface method throws UnsupportedOperationException")
    public void testIsValidMoveInterfaceMethod() {
        Project project = new Project();
        assertThrows(UnsupportedOperationException.class, () -> {
            project.isValidMove();
        }, "isValidMove() interface method should throw UnsupportedOperationException");
    }

    // Edge case: Test with different board sizes
    @Test
    @DisplayName("Test game logic on 4x4 board")
    public void testGameLogicOn4x4Board() throws Exception {
        char[][] board = new char[4][4];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 4);

        // Test valid move
        boolean validMove = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 2, 2);
        assertTrue(validMove, "Should accept valid move on 4x4 board");

        // Test board not full
        boolean full = (boolean) invokePrivateMethod("isBoardFull",
            new Class<?>[]{char[][].class}, (Object) board);
        assertFalse(full, "Empty 4x4 board should not be full");

        // Test no winner
        boolean won = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');
        assertFalse(won, "Empty 4x4 board should have no winner");
    }

    // Regression test: Ensure corner cases work correctly
    @Test
    @DisplayName("Regression test: Ensure anti-diagonal win is detected correctly")
    public void testRegressionAntiDiagonalWin() throws Exception {
        char[][] board = new char[4][4];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 4);

        // Fill anti-diagonal: (0,3), (1,2), (2,1), (3,0)
        board[0][3] = 'X';
        board[1][2] = 'X';
        board[2][1] = 'X';
        board[3][0] = 'X';

        boolean result = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');

        assertTrue(result, "Should detect anti-diagonal win on 4x4 board");
    }

    // Negative test: Ensure mixed marks don't count as win
    @Test
    @DisplayName("Negative test: Mixed marks in a line should not win")
    public void testMixedMarksNoWin() throws Exception {
        char[][] board = new char[3][3];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 3);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'X';

        boolean resultX = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'X');
        boolean resultO = (boolean) invokePrivateMethod("isGameWon",
            new Class<?>[]{char[][].class, char.class}, board, 'O');

        assertFalse(resultX, "Mixed marks should not result in X winning");
        assertFalse(resultO, "Mixed marks should not result in O winning");
    }

    // Boundary test: Test with maximum allowed board size
    @Test
    @DisplayName("Boundary test: Test with 9x9 board (maximum size)")
    public void testMaximumBoardSize() throws Exception {
        char[][] board = new char[9][9];
        invokePrivateMethod("initializeTicTacToe", new Class<?>[]{char[][].class, int.class}, board, 9);

        // Test initialization
        assertEquals(' ', board[0][0], "First cell should be empty");
        assertEquals(' ', board[8][8], "Last cell should be empty");

        // Test valid move on boundaries
        boolean validMove = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 8, 8);
        assertTrue(validMove, "Should accept move at maximum boundary");

        // Test invalid move beyond boundary
        boolean invalidMove = (boolean) invokePrivateMethod("isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class}, board, 9, 9);
        assertFalse(invalidMove, "Should reject move beyond maximum boundary");
    }
}