import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Comprehensive unit tests for Project.java
 * Tests TicTacToe game logic and RockPaperScissors game logic
 */
public class ProjectTest {

    // Helper method to access private static methods via reflection
    private Object invokePrivateStaticMethod(String className, String methodName,
                                            Class<?>[] paramTypes, Object... args)
            throws Exception {
        Class<?> clazz = Class.forName(className);
        Method method = clazz.getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(null, args);
    }

    // ==================== TicTacToe: isValidMove Tests ====================

    @Test
    public void testIsValidMove_ValidEmptyCell() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 0, 0
        );

        assertTrue("Valid move on empty cell should return true", result);
    }

    @Test
    public void testIsValidMove_OccupiedCell() throws Exception {
        char[][] board = {
            {'X', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 0, 0
        );

        assertFalse("Move on occupied cell should return false", result);
    }

    @Test
    public void testIsValidMove_NegativeRow() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, -1, 0
        );

        assertFalse("Negative row should return false", result);
    }

    @Test
    public void testIsValidMove_NegativeColumn() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 0, -1
        );

        assertFalse("Negative column should return false", result);
    }

    @Test
    public void testIsValidMove_RowOutOfBounds() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 3, 0
        );

        assertFalse("Row out of bounds should return false", result);
    }

    @Test
    public void testIsValidMove_ColumnOutOfBounds() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 0, 3
        );

        assertFalse("Column out of bounds should return false", result);
    }

    @Test
    public void testIsValidMove_LargerBoard() throws Exception {
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = ' ';
            }
        }
        board[2][2] = 'O';

        boolean result1 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 4, 4
        );
        assertTrue("Valid move on 5x5 board should return true", result1);

        boolean result2 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 2, 2
        );
        assertFalse("Move on occupied cell in 5x5 board should return false", result2);
    }

    // ==================== TicTacToe: isGameWon Tests ====================

    @Test
    public void testIsGameWon_HorizontalWin_TopRow() throws Exception {
        char[][] board = {
            {'X', 'X', 'X'},
            {'O', 'O', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Horizontal win in top row should return true", result);
    }

    @Test
    public void testIsGameWon_HorizontalWin_MiddleRow() throws Exception {
        char[][] board = {
            {'O', 'O', ' '},
            {'X', 'X', 'X'},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Horizontal win in middle row should return true", result);
    }

    @Test
    public void testIsGameWon_HorizontalWin_BottomRow() throws Exception {
        char[][] board = {
            {'O', 'O', ' '},
            {' ', ' ', ' '},
            {'X', 'X', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Horizontal win in bottom row should return true", result);
    }

    @Test
    public void testIsGameWon_VerticalWin_LeftColumn() throws Exception {
        char[][] board = {
            {'X', 'O', ' '},
            {'X', 'O', ' '},
            {'X', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Vertical win in left column should return true", result);
    }

    @Test
    public void testIsGameWon_VerticalWin_MiddleColumn() throws Exception {
        char[][] board = {
            {'O', 'X', ' '},
            {' ', 'X', ' '},
            {'O', 'X', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Vertical win in middle column should return true", result);
    }

    @Test
    public void testIsGameWon_VerticalWin_RightColumn() throws Exception {
        char[][] board = {
            {'O', ' ', 'X'},
            {' ', ' ', 'X'},
            {'O', ' ', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Vertical win in right column should return true", result);
    }

    @Test
    public void testIsGameWon_DiagonalWin_TopLeftToBottomRight() throws Exception {
        char[][] board = {
            {'X', 'O', ' '},
            {'O', 'X', ' '},
            {' ', ' ', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Diagonal win (top-left to bottom-right) should return true", result);
    }

    @Test
    public void testIsGameWon_DiagonalWin_TopRightToBottomLeft() throws Exception {
        char[][] board = {
            {' ', 'O', 'X'},
            {'O', 'X', ' '},
            {'X', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Diagonal win (top-right to bottom-left) should return true", result);
    }

    @Test
    public void testIsGameWon_NoWin() throws Exception {
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {'O', 'X', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertFalse("No win condition should return false", result);
    }

    @Test
    public void testIsGameWon_LargerBoard_HorizontalWin() throws Exception {
        char[][] board = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill first row with X
        for (int j = 0; j < 4; j++) {
            board[0][j] = 'X';
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Horizontal win on 4x4 board should return true", result);
    }

    @Test
    public void testIsGameWon_LargerBoard_DiagonalWin() throws Exception {
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill main diagonal with O
        for (int i = 0; i < 5; i++) {
            board[i][i] = 'O';
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertTrue("Diagonal win on 5x5 board should return true", result);
    }

    // ==================== TicTacToe: isBoardFull Tests ====================

    @Test
    public void testIsBoardFull_EmptyBoard() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("Empty board should return false", result);
    }

    @Test
    public void testIsBoardFull_PartiallyFilledBoard() throws Exception {
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {'O', 'X', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("Partially filled board should return false", result);
    }

    @Test
    public void testIsBoardFull_FullBoard() throws Exception {
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {'O', 'X', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertTrue("Full board should return true", result);
    }

    @Test
    public void testIsBoardFull_OneEmptyCell() throws Exception {
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {'O', ' ', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("Board with one empty cell should return false", result);
    }

    @Test
    public void testIsBoardFull_LargerBoard() throws Exception {
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = 'X';
            }
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertTrue("Full 5x5 board should return true", result);
    }

    // ==================== TicTacToe: initializeTicTacToe Tests ====================

    @Test
    public void testInitializeTicTacToe_3x3Board() throws Exception {
        char[][] board = new char[3][3];

        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 3
        );

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals("Cell should be initialized to space", ' ', board[i][j]);
            }
        }
    }

    @Test
    public void testInitializeTicTacToe_5x5Board() throws Exception {
        char[][] board = new char[5][5];

        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 5
        );

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals("Cell should be initialized to space", ' ', board[i][j]);
            }
        }
    }

    @Test
    public void testInitializeTicTacToe_OverwriteExistingData() throws Exception {
        char[][] board = new char[3][3];
        // Fill with data
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 'X';
            }
        }

        // Initialize should overwrite
        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 3
        );

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals("Cell should be cleared to space", ' ', board[i][j]);
            }
        }
    }

    // ==================== RockPaperScissors: checkWinner Tests ====================

    @Test
    public void testCheckWinner_PlayerRock_ComputerScissors() {
        String result = RockPaperScissors.checkWinner("r", "s");
        assertEquals("Rock beats scissors", "You win!", result);
    }

    @Test
    public void testCheckWinner_PlayerScissors_ComputerPaper() {
        String result = RockPaperScissors.checkWinner("s", "p");
        assertEquals("Scissors beats paper", "You win!", result);
    }

    @Test
    public void testCheckWinner_PlayerPaper_ComputerRock() {
        String result = RockPaperScissors.checkWinner("p", "r");
        assertEquals("Paper beats rock", "You win!", result);
    }

    @Test
    public void testCheckWinner_PlayerRock_ComputerPaper() {
        String result = RockPaperScissors.checkWinner("r", "p");
        assertEquals("Paper beats rock", "Computer wins!", result);
    }

    @Test
    public void testCheckWinner_PlayerScissors_ComputerRock() {
        String result = RockPaperScissors.checkWinner("s", "r");
        assertEquals("Rock beats scissors", "Computer wins!", result);
    }

    @Test
    public void testCheckWinner_PlayerPaper_ComputerScissors() {
        String result = RockPaperScissors.checkWinner("p", "s");
        assertEquals("Scissors beats paper", "Computer wins!", result);
    }

    @Test
    public void testCheckWinner_TieRock() {
        String result = RockPaperScissors.checkWinner("r", "r");
        assertEquals("Same choice should tie", "It's a tie!", result);
    }

    @Test
    public void testCheckWinner_TiePaper() {
        String result = RockPaperScissors.checkWinner("p", "p");
        assertEquals("Same choice should tie", "It's a tie!", result);
    }

    @Test
    public void testCheckWinner_TieScissors() {
        String result = RockPaperScissors.checkWinner("s", "s");
        assertEquals("Same choice should tie", "It's a tie!", result);
    }

    // ==================== Edge Cases and Additional Tests ====================

    @Test
    public void testIsGameWon_EmptyBoard() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertFalse("Empty board should not have a winner", result);
    }

    @Test
    public void testIsGameWon_MinimumBoardSize() throws Exception {
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', 'X', 'O'},
            {'X', 'O', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("X should win with both diagonals on 3x3", result);
    }

    @Test
    public void testIsValidMove_BoundaryCheck_AllCorners() throws Exception {
        char[][] board = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = ' ';
            }
        }

        // Test all four corners
        assertTrue("Top-left corner should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 0
            ));

        assertTrue("Top-right corner should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 3
            ));

        assertTrue("Bottom-left corner should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 3, 0
            ));

        assertTrue("Bottom-right corner should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 3, 3
            ));
    }

    @Test
    public void testRockPaperScissors_AllPossibleOutcomes() {
        // Test all 9 combinations to ensure complete coverage
        String[][] combinations = {
            {"r", "r", "It's a tie!"},
            {"r", "p", "Computer wins!"},
            {"r", "s", "You win!"},
            {"p", "r", "You win!"},
            {"p", "p", "It's a tie!"},
            {"p", "s", "Computer wins!"},
            {"s", "r", "Computer wins!"},
            {"s", "p", "You win!"},
            {"s", "s", "It's a tie!"}
        };

        for (String[] combo : combinations) {
            String result = RockPaperScissors.checkWinner(combo[0], combo[1]);
            assertEquals("Player: " + combo[0] + " vs Computer: " + combo[1],
                        combo[2], result);
        }
    }

    @Test
    public void testIsBoardFull_MaxBoardSize() throws Exception {
        // Test with maximum board size (9x9)
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 'X';
            }
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertTrue("Full 9x9 board should return true", result);
    }

    @Test
    public void testIsGameWon_NearWinScenario() throws Exception {
        // Test a scenario where player almost wins but doesn't
        char[][] board = {
            {'X', 'X', 'O'},
            {'O', 'X', ' '},
            {' ', 'O', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertFalse("Near win (diagonal incomplete) should return false", result);
    }

    @Test
    public void testIsGameWon_MultipleWinConditions() throws Exception {
        // Scenario where player has won in multiple ways
        char[][] board = {
            {'X', 'X', 'X'},
            {'X', 'O', 'O'},
            {'X', 'O', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Player with multiple win conditions should return true", result);
    }

    // ==================== Additional Edge Cases and Regression Tests ====================

    @Test
    public void testIsGameWon_AntiDiagonalWin_LargerBoard() throws Exception {
        // Test anti-diagonal win on a 4x4 board
        char[][] board = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill anti-diagonal with O
        for (int i = 0; i < 4; i++) {
            board[i][3 - i] = 'O';
        }
        board[0][0] = 'X'; // Add noise

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertTrue("Anti-diagonal win on 4x4 board should return true", result);
    }

    @Test
    public void testIsValidMove_ExtremeNegativeValues() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result1 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, -100, -100
        );

        assertFalse("Extreme negative values should return false", result1);

        boolean result2 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, Integer.MIN_VALUE, Integer.MIN_VALUE
        );

        assertFalse("MIN_VALUE should return false", result2);
    }

    @Test
    public void testIsValidMove_ExtremePositiveValues() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result1 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 100, 100
        );

        assertFalse("Extreme positive values should return false", result1);

        boolean result2 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, Integer.MAX_VALUE, Integer.MAX_VALUE
        );

        assertFalse("MAX_VALUE should return false", result2);
    }

    @Test
    public void testIsBoardFull_MixedCharacters() throws Exception {
        // Test with characters other than X and O
        char[][] board = {
            {'X', 'O', 'A'},
            {'B', 'X', 'C'},
            {'O', 'D', 'E'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertTrue("Board with no spaces should return true regardless of characters", result);
    }

    @Test
    public void testInitializeTicTacToe_MinimumBoardSize() throws Exception {
        // Test with minimum allowed board size (3)
        char[][] board = new char[3][3];
        // Pre-fill with non-space characters
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 'Z';
            }
        }

        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 3
        );

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals("Minimum size board should be initialized correctly", ' ', board[i][j]);
            }
        }
    }

    @Test
    public void testIsGameWon_OnlyOneCellFilled() throws Exception {
        // Test with only one cell filled
        char[][] board = {
            {'X', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertFalse("Board with only one cell filled should not win", result);
    }

    @Test
    public void testIsGameWon_WrongPlayerCheck() throws Exception {
        // Test checking for wrong player when another player has won
        char[][] board = {
            {'X', 'X', 'X'},
            {'O', 'O', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertFalse("Checking wrong player when X has won should return false", result);
    }

    @Test
    public void testIsValidMove_CenterCell() throws Exception {
        // Test specifically the center cell on odd-sized boards
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 1, 1
        );

        assertTrue("Center cell should be valid on empty board", result);

        board[1][1] = 'X';
        boolean result2 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 1, 1
        );

        assertFalse("Center cell should be invalid when occupied", result2);
    }

    @Test
    public void testIsBoardFull_LastCellInCorner() throws Exception {
        // Test board with only corner cell empty
        char[][] board = {
            {' ', 'X', 'O'},
            {'X', 'O', 'X'},
            {'O', 'X', 'O'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("Board with corner cell empty should return false", result);
    }

    @Test
    public void testIsGameWon_VerticalWin_LargerBoard() throws Exception {
        // Test vertical win on 6x6 board
        char[][] board = new char[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill middle column with X
        for (int i = 0; i < 6; i++) {
            board[i][2] = 'X';
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Vertical win on 6x6 board should return true", result);
    }

    @Test
    public void testCheckWinner_CaseSensitivity() {
        // Ensure the method works correctly with lowercase inputs
        String result1 = RockPaperScissors.checkWinner("r", "s");
        assertEquals("Lowercase rock vs scissors", "You win!", result1);

        String result2 = RockPaperScissors.checkWinner("p", "r");
        assertEquals("Lowercase paper vs rock", "You win!", result2);

        String result3 = RockPaperScissors.checkWinner("s", "p");
        assertEquals("Lowercase scissors vs paper", "You win!", result3);
    }

    @Test
    public void testIsGameWon_AlmostDiagonalWin() throws Exception {
        // Test scenario where diagonal is almost complete but not quite
        char[][] board = {
            {'X', 'O', ' '},
            {'O', 'X', ' '},
            {' ', ' ', 'O'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertFalse("Incomplete diagonal should return false", result);
    }

    @Test
    public void testIsBoardFull_AllSameCharacter() throws Exception {
        // Test board filled with all same character
        char[][] board = {
            {'X', 'X', 'X'},
            {'X', 'X', 'X'},
            {'X', 'X', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertTrue("Board with all same character should be full", result);
    }

    @Test
    public void testInitializeTicTacToe_LargestBoardSize() throws Exception {
        // Test with largest allowed board size (9)
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 'Q';
            }
        }

        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 9
        );

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals("9x9 board should be initialized correctly", ' ', board[i][j]);
            }
        }
    }

    @Test
    public void testIsValidMove_JustOutOfBounds() throws Exception {
        // Test coordinates just outside the valid range
        char[][] board = new char[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = ' ';
            }
        }

        // Test row just out of bounds
        boolean result1 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 5, 2
        );
        assertFalse("Row index 5 on 5x5 board should be invalid", result1);

        // Test column just out of bounds
        boolean result2 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 2, 5
        );
        assertFalse("Column index 5 on 5x5 board should be invalid", result2);

        // Test valid boundary
        boolean result3 = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 4, 4
        );
        assertTrue("Index 4,4 on 5x5 board should be valid", result3);
    }

    @Test
    public void testIsGameWon_CheckBothPlayers() throws Exception {
        // Ensure isGameWon correctly distinguishes between players
        char[][] board = {
            {'O', 'O', 'O'},
            {'X', 'X', ' '},
            {' ', ' ', ' '}
        };

        boolean resultO = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );
        assertTrue("O should have won with top row", resultO);

        boolean resultX = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );
        assertFalse("X should not have won", resultX);
    }

    @Test
    public void testRockPaperScissors_SymmetricOutcomes() {
        // Verify that swapping player and computer gives opposite result
        // If player wins with (r, s), computer should win with (s, r)
        String playerWins = RockPaperScissors.checkWinner("r", "s");
        String computerWins = RockPaperScissors.checkWinner("s", "r");

        assertEquals("Rock beats scissors - player wins", "You win!", playerWins);
        assertEquals("Scissors loses to rock - computer wins", "Computer wins!", computerWins);
    }

    @Test
    public void testIsBoardFull_SingleEmptyInCenter() throws Exception {
        // Regression test: center cell empty
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', ' ', 'X'},
            {'X', 'O', 'O'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("Board with center cell empty should return false", result);
    }
}