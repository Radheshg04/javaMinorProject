import java.util.Random;
import java.util.Scanner;

interface Skel {
    boolean isValidMove();
}

abstract class Game {
    public abstract String checkWinner();
}

public class Project extends RockPaperScissors implements Skel {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Menu:");
            System.out.println("1. Play Tic Tac Toe (NxN)");
            System.out.println("2. Play Rock, Paper, Scissors!");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            } catch (java.util.NoSuchElementException a) {
                break;
            }

            switch (choice) {
                case 1:
                    playTicTacToe();
                    break;
                case 2:
                    RockPaperScissorsGame();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
        sc.close();
    }

    private static void playTicTacToe() {
        System.out.println("Welcome to Tic Tac Toe!");
        System.out.print("Enter the size of the Tic Tac Toe board (3 or greater): ");
        Scanner sc = new Scanner(System.in);
        int boardSize;
        try {
            boardSize = sc.nextInt();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (boardSize < 3) {
            System.out.println("Play area too small for Tic Tac Toe.");
            return;
        } else if (boardSize > 9) {
            System.out.println("Play area too large for Tic Tac Toe.");
            return;
        }

        char[][] board = new char[boardSize][boardSize];
        initializeTicTacToe(board, boardSize);
        printTicTacToe(board, boardSize);

        char currentPlayer = 'X';
        boolean gameEnded = false;

        while (!gameEnded) {
            System.out.print("Player " + currentPlayer + ", enter row and column (e.g., 1 2): ");
            int row;
            int col;
            try {
                row = sc.nextInt() - 1;
                col = sc.nextInt() - 1;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter two numbers.");
                sc.nextLine();
                continue;
            }

            if (isValidMove(board, row, col)) {
                board[row][col] = currentPlayer;
                printTicTacToe(board, boardSize);

                if (isGameWon(board, currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    gameEnded = true;
                } else if (isBoardFull(board)) {
                    System.out.println("It's a draw!");
                    gameEnded = true;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private static void initializeTicTacToe(char[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Prints the Tic Tac Toe board to standard output using rows, columns and visual separators.
     *
     * @param board a two-dimensional char array representing the board cells; empty cells are expected to contain a space character
     * @param n     the board dimension (number of rows and columns) to render from the provided array
     */
    private static void printTicTacToe(char[][] board, int n) {
        System.out.println("Tic Tac Toe board:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]);
                if (j != n - 1) {
                    System.out.print(" | ");
                }
            } 
            System.out.println();
            if (i != n - 1) {
                for (int k = 0; k < n; k++) {
                    System.out.print("---");
                    if (k != n - 1) {
                        System.out.print("+");
                    }
                }
                System.out.print("\n");
            }
        }
    }

    private static boolean isValidMove(char[][] board, int row, int col) {
        return row >= 0 && row < board.length && col >= 0 && col < board.length && board[row][col] == ' ';
    }

    private static boolean isGameWon(char[][] board, char player) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            boolean rowWin = true;
            boolean colWin = true;
            for (int j = 0; j < n; j++) {
                if (board[i][j] != player) {
                    rowWin = false;
                }
                if (board[j][i] != player) {
                    colWin = false;
                }
            }
            if (rowWin || colWin) {
                return true;
            }
        }
        boolean diagonal1Win = true;
        boolean diagonal2Win = true;
        for (int i = 0; i < n; i++) {
            if (board[i][i] != player) {
                diagonal1Win = false;
            }
            if (board[i][n - 1 - i] != player) {
                diagonal2Win = false;
            }
        }

        return diagonal1Win || diagonal2Win;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isValidMove() {
        throw new UnsupportedOperationException("Unimplemented method 'isValidMove'");
    }
}

class RockPaperScissors extends Game {

    public static void RockPaperScissorsGame() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"r", "p", "s"};

        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Enter your choice (rock(r), paper(p), scissors(s), or quit to exit):");

        while (true) {
            System.out.print("Your choice: ");
            String playerChoice = sc.nextLine().toLowerCase();

            if (playerChoice.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (!playerChoice.equals("r") &&
                !playerChoice.equals("p") &&
                !playerChoice.equals("s")) {
                System.out.println("Invalid choice. Please choose rock, paper, scissors, or quit to exit.");
                continue;
            }

            int comp_i = random.nextInt(3);
            String comp_choice = choices[comp_i];

            System.out.println("Computer's choice: " + comp_choice);

            String result = checkWinner(playerChoice, comp_choice);
            System.out.println(result);
        }

        sc.close();
    }

    public static String checkWinner(String playerChoice, String comp_choice) {
        if (playerChoice.equals(comp_choice)) {
            return "It's a tie!";
        } else if (
            (playerChoice.equals("r") && comp_choice.equals("s")) ||
            (playerChoice.equals("s") && comp_choice.equals("p")) ||
            (playerChoice.equals("p") && comp_choice.equals("r"))
        ) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }

    @Override
    public String checkWinner() {
        throw new UnsupportedOperationException("Unimplemented method 'checkWinner'");
    }
}