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

    // ==================== Additional Tests for Strengthened Coverage ====================

    @Test
    public void testIsGameWon_PlayerO_HorizontalWin() throws Exception {
        // Test that player O can also win (not just X)
        char[][] board = {
            {'X', 'X', ' '},
            {'O', 'O', 'O'},
            {'X', ' ', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertTrue("Player O horizontal win should return true", result);
    }

    @Test
    public void testIsGameWon_PlayerO_VerticalWin() throws Exception {
        char[][] board = {
            {'O', 'X', 'X'},
            {'O', 'X', ' '},
            {'O', ' ', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertTrue("Player O vertical win should return true", result);
    }

    @Test
    public void testIsGameWon_PlayerO_DiagonalWin() throws Exception {
        char[][] board = {
            {'O', 'X', 'X'},
            {'X', 'O', ' '},
            {' ', ' ', 'O'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertTrue("Player O diagonal win should return true", result);
    }

    @Test
    public void testIsValidMove_CenterCell_3x3() throws Exception {
        char[][] board = {
            {'X', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', 'O'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 1, 1
        );

        assertTrue("Center cell should be valid", result);
    }

    @Test
    public void testIsValidMove_CenterCell_Occupied() throws Exception {
        char[][] board = {
            {'X', ' ', ' '},
            {' ', 'O', ' '},
            {' ', ' ', 'X'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isValidMove",
            new Class<?>[]{char[][].class, int.class, int.class},
            board, 1, 1
        );

        assertFalse("Occupied center cell should be invalid", result);
    }

    @Test
    public void testIsValidMove_7x7Board_EdgePositions() throws Exception {
        char[][] board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }

        // Test various edge positions
        assertTrue("Position (0,3) should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 3
            ));

        assertTrue("Position (6,3) should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 6, 3
            ));

        assertTrue("Position (3,0) should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 3, 0
            ));

        assertTrue("Position (3,6) should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 3, 6
            ));
    }

    @Test
    public void testIsGameWon_7x7Board_AntiDiagonalWin() throws Exception {
        char[][] board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill anti-diagonal with X
        for (int i = 0; i < 7; i++) {
            board[i][6 - i] = 'X';
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Anti-diagonal win on 7x7 board should return true", result);
    }

    @Test
    public void testIsGameWon_8x8Board_VerticalWinLastColumn() throws Exception {
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill last column with O
        for (int i = 0; i < 8; i++) {
            board[i][7] = 'O';
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertTrue("Vertical win in last column of 8x8 board should return true", result);
    }

    @Test
    public void testIsBoardFull_7x7Board_AlmostFull() throws Exception {
        char[][] board = new char[7][7];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 'X';
            }
        }
        board[6][6] = ' '; // One empty cell

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("7x7 board with one empty cell should return false", result);
    }

    @Test
    public void testInitializeTicTacToe_7x7Board() throws Exception {
        char[][] board = new char[7][7];
        // Pre-fill with data
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 'Z';
            }
        }

        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 7
        );

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals("Cell should be initialized to space", ' ', board[i][j]);
            }
        }
    }

    @Test
    public void testInitializeTicTacToe_MaxBoardSize() throws Exception {
        char[][] board = new char[9][9];

        invokePrivateStaticMethod(
            "Project", "initializeTicTacToe",
            new Class<?>[]{char[][].class, int.class},
            board, 9
        );

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals("Cell in 9x9 board should be initialized to space", ' ', board[i][j]);
            }
        }
    }

    @Test
    public void testIsGameWon_ComplexBoardState_NoWinner() throws Exception {
        // Complex board where no one has won yet
        char[][] board = {
            {'X', 'O', 'X', 'O'},
            {'O', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'O'},
            {'O', 'X', ' ', ' '}
        };

        boolean resultX = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        boolean resultO = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'O'
        );

        assertFalse("Complex board with no winner for X should return false", resultX);
        assertFalse("Complex board with no winner for O should return false", resultO);
    }

    @Test
    public void testIsValidMove_SequentialMoves() throws Exception {
        // Test making sequential moves
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // First move should be valid
        assertTrue("First move should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 0
            ));

        // Make the move
        board[0][0] = 'X';

        // Same position should now be invalid
        assertFalse("Same position should be invalid after move",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 0
            ));

        // Adjacent position should still be valid
        assertTrue("Adjacent position should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 1
            ));
    }

    @Test
    public void testIsGameWon_PartialDiagonal_NoWin() throws Exception {
        // Diagonal is partially filled but not complete
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

        assertFalse("Partial diagonal should not win", result);
    }

    @Test
    public void testIsBoardFull_SingleRow() throws Exception {
        // Edge case: check board isn't prematurely considered full
        char[][] board = {
            {'X', 'O', 'X'},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertFalse("Board with only one row filled should return false", result);
    }

    @Test
    public void testCheckWinner_RegressionTest_AllCombinations() {
        // Regression test to ensure all 9 outcomes remain stable
        assertEquals("r vs r", "It's a tie!", RockPaperScissors.checkWinner("r", "r"));
        assertEquals("r vs p", "Computer wins!", RockPaperScissors.checkWinner("r", "p"));
        assertEquals("r vs s", "You win!", RockPaperScissors.checkWinner("r", "s"));
        assertEquals("p vs r", "You win!", RockPaperScissors.checkWinner("p", "r"));
        assertEquals("p vs p", "It's a tie!", RockPaperScissors.checkWinner("p", "p"));
        assertEquals("p vs s", "Computer wins!", RockPaperScissors.checkWinner("p", "s"));
        assertEquals("s vs r", "Computer wins!", RockPaperScissors.checkWinner("s", "r"));
        assertEquals("s vs p", "You win!", RockPaperScissors.checkWinner("s", "p"));
        assertEquals("s vs s", "It's a tie!", RockPaperScissors.checkWinner("s", "s"));
    }

    @Test
    public void testIsValidMove_MaxBoardSize_OutOfBounds() throws Exception {
        char[][] board = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = ' ';
            }
        }

        // Test boundary at index 9 (should be out of bounds)
        assertFalse("Index 9 on 9x9 board should be out of bounds",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 9, 0
            ));

        assertFalse("Index 9 on 9x9 board should be out of bounds",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, 9
            ));

        // Test valid boundary at index 8 (should be valid)
        assertTrue("Index 8 on 9x9 board should be valid",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 8, 8
            ));
    }

    @Test
    public void testIsGameWon_6x6Board_HorizontalLastRow() throws Exception {
        char[][] board = new char[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = ' ';
            }
        }
        // Fill last row with X
        for (int j = 0; j < 6; j++) {
            board[5][j] = 'X';
        }

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isGameWon",
            new Class<?>[]{char[][].class, char.class},
            board, 'X'
        );

        assertTrue("Horizontal win in last row of 6x6 board should return true", result);
    }

    @Test
    public void testIsBoardFull_MixedBoard() throws Exception {
        // Board with mixed X and O, completely filled
        char[][] board = {
            {'X', 'O', 'X'},
            {'O', 'O', 'X'},
            {'X', 'X', 'O'}
        };

        boolean result = (boolean) invokePrivateStaticMethod(
            "Project", "isBoardFull",
            new Class<?>[]{char[][].class},
            (Object) board
        );

        assertTrue("Mixed full board should return true", result);
    }

    @Test
    public void testIsValidMove_ExtremeNegativeIndices() throws Exception {
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };

        assertFalse("Large negative row should return false",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, -100, 0
            ));

        assertFalse("Large negative column should return false",
            (boolean) invokePrivateStaticMethod(
                "Project", "isValidMove",
                new Class<?>[]{char[][].class, int.class, int.class},
                board, 0, -100
            ));
    }

    @Test
    public void testIsGameWon_OnlyOneCell_NoWin() throws Exception {
        // Single cell filled should not result in a win
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

        assertFalse("Single cell should not result in win", result);
    }
}