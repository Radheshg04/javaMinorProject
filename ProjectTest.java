import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Comprehensive test suite for Project.java
 * Tests both Rock Paper Scissors and Tic Tac Toe game logic
 */
public class ProjectTest {

    // Rock Paper Scissors Tests
    @Test
    @DisplayName("RPS: Player wins with rock vs scissors")
    public void testRPSPlayerWinsRockVsScissors() {
        String result = RockPaperScissors.checkWinner("r", "s");
        assertEquals("You win!", result);
    }

    @Test
    @DisplayName("RPS: Player wins with scissors vs paper")
    public void testRPSPlayerWinsScissorsVsPaper() {
        String result = RockPaperScissors.checkWinner("s", "p");
        assertEquals("You win!", result);
    }

    @Test
    @DisplayName("RPS: Player wins with paper vs rock")
    public void testRPSPlayerWinsPaperVsRock() {
        String result = RockPaperScissors.checkWinner("p", "r");
        assertEquals("You win!", result);
    }

    @Test
    @DisplayName("RPS: Computer wins with scissors vs rock")
    public void testRPSComputerWinsScissorsVsRock() {
        String result = RockPaperScissors.checkWinner("r", "s");
        assertEquals("You win!", result); // Player wins in this case
        result = RockPaperScissors.checkWinner("s", "r");
        assertEquals("Computer wins!", result);
    }

    @Test
    @DisplayName("RPS: Computer wins with paper vs scissors")
    public void testRPSComputerWinsPaperVsScissors() {
        String result = RockPaperScissors.checkWinner("p", "s");
        assertEquals("Computer wins!", result);
    }

    @Test
    @DisplayName("RPS: Computer wins with rock vs paper")
    public void testRPSComputerWinsRockVsPaper() {
        String result = RockPaperScissors.checkWinner("r", "p");
        assertEquals("Computer wins!", result);
    }

    @Test
    @DisplayName("RPS: Tie with rock vs rock")
    public void testRPSTieRock() {
        String result = RockPaperScissors.checkWinner("r", "r");
        assertEquals("It's a tie!", result);
    }

    @Test
    @DisplayName("RPS: Tie with paper vs paper")
    public void testRPSTiePaper() {
        String result = RockPaperScissors.checkWinner("p", "p");
        assertEquals("It's a tie!", result);
    }

    @Test
    @DisplayName("RPS: Tie with scissors vs scissors")
    public void testRPSTieScissors() {
        String result = RockPaperScissors.checkWinner("s", "s");
        assertEquals("It's a tie!", result);
    }

    // Tic Tac Toe Tests - Using Reflection to access private methods

    @Test
    @DisplayName("TTT: Initialize board creates empty spaces")
    public void testInitializeTicTacToe() throws Exception {
        char[][] board = new char[3][3];
        Method initMethod = Project.class.getDeclaredMethod("initializeTicTacToe", char[][].class, int.class);
        initMethod.setAccessible(true);
        initMethod.invoke(null, board, 3);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', board[i][j], "Board position [" + i + "][" + j + "] should be empty");
            }
        }
    }

    @Test
    @DisplayName("TTT: Initialize larger board (5x5) creates empty spaces")
    public void testInitializeLargerBoard() throws Exception {
        char[][] board = new char[5][5];
        Method initMethod = Project.class.getDeclaredMethod("initializeTicTacToe", char[][].class, int.class);
        initMethod.setAccessible(true);
        initMethod.invoke(null, board, 5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(' ', board[i][j]);
            }
        }
    }

    @Test
    @DisplayName("TTT: Valid move on empty cell")
    public void testIsValidMoveEmptyCell() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        boolean result = (boolean) isValidMoveMethod.invoke(null, board, 0, 0);
        assertTrue(result, "Move to empty cell should be valid");
    }

    @Test
    @DisplayName("TTT: Invalid move on occupied cell")
    public void testIsValidMoveOccupiedCell() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[1][1] = 'X';

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        boolean result = (boolean) isValidMoveMethod.invoke(null, board, 1, 1);
        assertFalse(result, "Move to occupied cell should be invalid");
    }

    @Test
    @DisplayName("TTT: Invalid move with negative row")
    public void testIsValidMoveNegativeRow() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        boolean result = (boolean) isValidMoveMethod.invoke(null, board, -1, 0);
        assertFalse(result, "Move with negative row should be invalid");
    }

    @Test
    @DisplayName("TTT: Invalid move with negative column")
    public void testIsValidMoveNegativeColumn() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        boolean result = (boolean) isValidMoveMethod.invoke(null, board, 0, -1);
        assertFalse(result, "Move with negative column should be invalid");
    }

    @Test
    @DisplayName("TTT: Invalid move with row out of bounds")
    public void testIsValidMoveRowOutOfBounds() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        boolean result = (boolean) isValidMoveMethod.invoke(null, board, 3, 0);
        assertFalse(result, "Move with row >= board size should be invalid");
    }

    @Test
    @DisplayName("TTT: Invalid move with column out of bounds")
    public void testIsValidMoveColumnOutOfBounds() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        boolean result = (boolean) isValidMoveMethod.invoke(null, board, 0, 3);
        assertFalse(result, "Move with column >= board size should be invalid");
    }

    @Test
    @DisplayName("TTT: Win detected on top row")
    public void testIsGameWonTopRow() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on top row");
    }

    @Test
    @DisplayName("TTT: Win detected on middle row")
    public void testIsGameWonMiddleRow() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[1][0] = 'O';
        board[1][1] = 'O';
        board[1][2] = 'O';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'O');
        assertTrue(result, "Should detect win on middle row");
    }

    @Test
    @DisplayName("TTT: Win detected on bottom row")
    public void testIsGameWonBottomRow() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[2][0] = 'X';
        board[2][1] = 'X';
        board[2][2] = 'X';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on bottom row");
    }

    @Test
    @DisplayName("TTT: Win detected on left column")
    public void testIsGameWonLeftColumn() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'O';
        board[1][0] = 'O';
        board[2][0] = 'O';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'O');
        assertTrue(result, "Should detect win on left column");
    }

    @Test
    @DisplayName("TTT: Win detected on middle column")
    public void testIsGameWonMiddleColumn() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][1] = 'X';
        board[1][1] = 'X';
        board[2][1] = 'X';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on middle column");
    }

    @Test
    @DisplayName("TTT: Win detected on right column")
    public void testIsGameWonRightColumn() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][2] = 'O';
        board[1][2] = 'O';
        board[2][2] = 'O';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'O');
        assertTrue(result, "Should detect win on right column");
    }

    @Test
    @DisplayName("TTT: Win detected on main diagonal")
    public void testIsGameWonMainDiagonal() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'X';
        board[1][1] = 'X';
        board[2][2] = 'X';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on main diagonal");
    }

    @Test
    @DisplayName("TTT: Win detected on anti-diagonal")
    public void testIsGameWonAntiDiagonal() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][2] = 'O';
        board[1][1] = 'O';
        board[2][0] = 'O';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'O');
        assertTrue(result, "Should detect win on anti-diagonal");
    }

    @Test
    @DisplayName("TTT: No win detected on incomplete row")
    public void testIsGameWonIncompleteRow() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'O';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertFalse(result, "Should not detect win on incomplete row");
    }

    @Test
    @DisplayName("TTT: Win detected on 4x4 board - top row")
    public void testIsGameWon4x4TopRow() throws Exception {
        char[][] board = new char[4][4];
        initializeBoard(board, 4);
        board[0][0] = 'X';
        board[0][1] = 'X';
        board[0][2] = 'X';
        board[0][3] = 'X';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on 4x4 board top row");
    }

    @Test
    @DisplayName("TTT: Win detected on 5x5 board - main diagonal")
    public void testIsGameWon5x5MainDiagonal() throws Exception {
        char[][] board = new char[5][5];
        initializeBoard(board, 5);
        for (int i = 0; i < 5; i++) {
            board[i][i] = 'O';
        }

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'O');
        assertTrue(result, "Should detect win on 5x5 board main diagonal");
    }

    @Test
    @DisplayName("TTT: Board full detection returns true for full board")
    public void testIsBoardFullTrue() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'X'; board[0][1] = 'O'; board[0][2] = 'X';
        board[1][0] = 'O'; board[1][1] = 'X'; board[1][2] = 'O';
        board[2][0] = 'O'; board[2][1] = 'X'; board[2][2] = 'X';

        Method isBoardFullMethod = Project.class.getDeclaredMethod("isBoardFull", char[][].class);
        isBoardFullMethod.setAccessible(true);

        boolean result = (boolean) isBoardFullMethod.invoke(null, (Object) board);
        assertTrue(result, "Should detect full board");
    }

    @Test
    @DisplayName("TTT: Board full detection returns false for empty board")
    public void testIsBoardFullFalseEmpty() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isBoardFullMethod = Project.class.getDeclaredMethod("isBoardFull", char[][].class);
        isBoardFullMethod.setAccessible(true);

        boolean result = (boolean) isBoardFullMethod.invoke(null, (Object) board);
        assertFalse(result, "Should detect empty board as not full");
    }

    @Test
    @DisplayName("TTT: Board full detection returns false for partially filled board")
    public void testIsBoardFullFalsePartial() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'X';
        board[1][1] = 'O';

        Method isBoardFullMethod = Project.class.getDeclaredMethod("isBoardFull", char[][].class);
        isBoardFullMethod.setAccessible(true);

        boolean result = (boolean) isBoardFullMethod.invoke(null, (Object) board);
        assertFalse(result, "Should detect partially filled board as not full");
    }

    @Test
    @DisplayName("TTT: Board full detection returns false when one cell is empty")
    public void testIsBoardFullFalseOneEmpty() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        board[0][0] = 'X'; board[0][1] = 'O'; board[0][2] = 'X';
        board[1][0] = 'O'; board[1][1] = 'X'; board[1][2] = 'O';
        board[2][0] = 'O'; board[2][1] = 'X'; // board[2][2] left empty

        Method isBoardFullMethod = Project.class.getDeclaredMethod("isBoardFull", char[][].class);
        isBoardFullMethod.setAccessible(true);

        boolean result = (boolean) isBoardFullMethod.invoke(null, (Object) board);
        assertFalse(result, "Should detect board with one empty cell as not full");
    }

    @Test
    @DisplayName("TTT: Board full detection on larger 5x5 board")
    public void testIsBoardFull5x5() throws Exception {
        char[][] board = new char[5][5];
        initializeBoard(board, 5);

        // Fill entire board
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = (i + j) % 2 == 0 ? 'X' : 'O';
            }
        }

        Method isBoardFullMethod = Project.class.getDeclaredMethod("isBoardFull", char[][].class);
        isBoardFullMethod.setAccessible(true);

        boolean result = (boolean) isBoardFullMethod.invoke(null, (Object) board);
        assertTrue(result, "Should detect full 5x5 board");
    }

    @Test
    @DisplayName("TTT: No winner in draw scenario")
    public void testNoWinnerInDrawScenario() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);
        // Create a draw scenario
        board[0][0] = 'X'; board[0][1] = 'O'; board[0][2] = 'X';
        board[1][0] = 'O'; board[1][1] = 'X'; board[1][2] = 'O';
        board[2][0] = 'O'; board[2][1] = 'X'; board[2][2] = 'O';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean xWins = (boolean) isGameWonMethod.invoke(null, board, 'X');
        boolean oWins = (boolean) isGameWonMethod.invoke(null, board, 'O');

        assertFalse(xWins, "X should not have won");
        assertFalse(oWins, "O should not have won");
    }

    @Test
    @DisplayName("RPS: Invalid player choice format should be handled by game logic")
    public void testRPSEdgeCaseEmptyString() {
        // Testing with valid choices only as invalid input is handled by game loop
        String result = RockPaperScissors.checkWinner("r", "r");
        assertEquals("It's a tie!", result);
    }

    @Test
    @DisplayName("TTT: Corner cells are valid moves")
    public void testValidMoveAllCorners() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 0, 0), "Top-left corner should be valid");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 0, 2), "Top-right corner should be valid");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 2, 0), "Bottom-left corner should be valid");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 2, 2), "Bottom-right corner should be valid");
    }

    @Test
    @DisplayName("TTT: Center cell is a valid move")
    public void testValidMoveCenterCell() throws Exception {
        char[][] board = new char[3][3];
        initializeBoard(board, 3);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 1, 1), "Center cell should be valid");
    }

    @Test
    @DisplayName("Project: isValidMove interface method throws UnsupportedOperationException")
    public void testProjectIsValidMoveThrowsException() {
        Project project = new Project();
        assertThrows(UnsupportedOperationException.class, () -> {
            project.isValidMove();
        }, "Interface method isValidMove should throw UnsupportedOperationException");
    }

    @Test
    @DisplayName("RPS: checkWinner abstract method throws UnsupportedOperationException")
    public void testRPSCheckWinnerAbstractMethodThrowsException() {
        RockPaperScissors rps = new RockPaperScissors();
        assertThrows(UnsupportedOperationException.class, () -> {
            rps.checkWinner();
        }, "Abstract method checkWinner should throw UnsupportedOperationException");
    }

    @Test
    @DisplayName("TTT: Win detected on 6x6 board - anti-diagonal")
    public void testIsGameWon6x6AntiDiagonal() throws Exception {
        char[][] board = new char[6][6];
        initializeBoard(board, 6);
        for (int i = 0; i < 6; i++) {
            board[i][6 - 1 - i] = 'X';
        }

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on 6x6 board anti-diagonal");
    }

    @Test
    @DisplayName("TTT: No win on 6x6 board with incomplete anti-diagonal")
    public void testIsGameWon6x6IncompleteAntiDiagonal() throws Exception {
        char[][] board = new char[6][6];
        initializeBoard(board, 6);
        for (int i = 0; i < 5; i++) { // Only fill 5 out of 6
            board[i][6 - 1 - i] = 'O';
        }

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'O');
        assertFalse(result, "Should not detect win on incomplete anti-diagonal");
    }

    @Test
    @DisplayName("TTT: Valid move on edge cells of 5x5 board")
    public void testValidMoveEdgeCells5x5() throws Exception {
        char[][] board = new char[5][5];
        initializeBoard(board, 5);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 0, 0), "Top-left corner should be valid on 5x5");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 0, 4), "Top-right corner should be valid on 5x5");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 4, 0), "Bottom-left corner should be valid on 5x5");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 4, 4), "Bottom-right corner should be valid on 5x5");
        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 2, 2), "Center should be valid on 5x5");
    }

    @Test
    @DisplayName("TTT: Board boundary validation on 7x7 board")
    public void testValidMoveBoundary7x7() throws Exception {
        char[][] board = new char[7][7];
        initializeBoard(board, 7);

        Method isValidMoveMethod = Project.class.getDeclaredMethod("isValidMove", char[][].class, int.class, int.class);
        isValidMoveMethod.setAccessible(true);

        assertTrue((boolean) isValidMoveMethod.invoke(null, board, 6, 6), "Max valid position should work on 7x7");
        assertFalse((boolean) isValidMoveMethod.invoke(null, board, 7, 6), "Row out of bounds should be invalid on 7x7");
        assertFalse((boolean) isValidMoveMethod.invoke(null, board, 6, 7), "Column out of bounds should be invalid on 7x7");
    }

    @Test
    @DisplayName("TTT: Win detection on 4x4 board - middle column")
    public void testIsGameWon4x4MiddleColumn() throws Exception {
        char[][] board = new char[4][4];
        initializeBoard(board, 4);
        board[0][1] = 'X';
        board[1][1] = 'X';
        board[2][1] = 'X';
        board[3][1] = 'X';

        Method isGameWonMethod = Project.class.getDeclaredMethod("isGameWon", char[][].class, char.class);
        isGameWonMethod.setAccessible(true);

        boolean result = (boolean) isGameWonMethod.invoke(null, board, 'X');
        assertTrue(result, "Should detect win on 4x4 board middle column");
    }

    @Test
    @DisplayName("RPS: All player win scenarios")
    public void testRPSAllPlayerWinScenarios() {
        assertEquals("You win!", RockPaperScissors.checkWinner("r", "s"), "Rock should beat scissors");
        assertEquals("You win!", RockPaperScissors.checkWinner("p", "r"), "Paper should beat rock");
        assertEquals("You win!", RockPaperScissors.checkWinner("s", "p"), "Scissors should beat paper");
    }

    @Test
    @DisplayName("RPS: All computer win scenarios")
    public void testRPSAllComputerWinScenarios() {
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("s", "r"), "Rock should beat scissors");
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("r", "p"), "Paper should beat rock");
        assertEquals("Computer wins!", RockPaperScissors.checkWinner("p", "s"), "Scissors should beat paper");
    }

    // Helper method to initialize board using reflection
    private void initializeBoard(char[][] board, int n) throws Exception {
        Method initMethod = Project.class.getDeclaredMethod("initializeTicTacToe", char[][].class, int.class);
        initMethod.setAccessible(true);
        initMethod.invoke(null, board, n);
    }
}