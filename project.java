package project;

import java.util.Random;
import java.util.Scanner;

public class project extends games{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice,n;

        do {
            System.out.println("Menu:");
            System.out.println("1. Play Sudoku (NxN)");
            System.out.println("2. Play Tic Tac Toe (NxN)");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter to be size of sudoku board (NxN): ");
                    n = scanner.nextInt();
                    playSudoku(n);
                    break;
                case 2:
                    System.out.println("Enter to be size of tic tac toe board (NxN): ");
                    n = scanner.nextInt();
                    playTicTacToe(n);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void playSudoku(int n) {
        System.out.println("Sudoku game is under construction.");
        int[][] board = new int[n][n]; 

        Random random = new Random();

        int rand1,rand2,filler_rand;


        for (int i = 0; i < n; i++) {
            rand1 = random.nextInt(1,n);
            rand2 = random.nextInt(1,n);
            filler_rand = random.nextInt(1,n);
            do {
                rand1 = random.nextInt(n);
                rand2 = random.nextInt(n);
            } while (board[rand1][rand2] != 0);
            board[rand1][rand2] = filler_rand;
        }
        
        System.out.println("-".repeat(4*n + 1));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(j==0)
                System.out.print("|");
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("-".repeat(4*n + 1));
        }
    }

    public static void displayTicTacToe(int[][] board,int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(" " + board[i][j]);
                if(j!=n-1)
                System.out.print(" |");
            }
            System.out.println();
            if(i!=n-1)
            System.out.println("-".repeat(4*n));
        }
        System.out.println();
    }

    public static int points(int[][] board, int n){
        int i,j,countP1=0,countP2=0;
        for(i=0;i<=n;i++){
            for(j=0;j<=n;j++){
                if(board[i][j]=='X')
                countP1+=1;
                else if(board[i][j]=='O')
                countP2+=1;
            }
            if(countP1==n){
                return 1;
            }
            else if(countP2==n){
                return 2;
            }
            else{
                countP1=0;
                countP2=0;
            }
        }
        for(j=0;j<=n;j++){
            for(i=0;i<=n;i++){
                if(board[i][j]=='X')
                countP1+=1;
                else if(board[i][j]=='O')
                countP2+=1;
            }
            if(countP1==n){
                return 1;
            }
            else if(countP2==n){
                return 2;
            }
            else{
                countP1=0;
                countP2=0;
            }
        }
        for(i=0;i<=n;i++){
            if(board[i][i]=='X'){
                countP1+=1;
            }
            else if(board[i][i]=='O'){
                countP2+=1;
            }
            else{
                countP1=0;
                countP2=0;
            }
        }
        for(i=n;i<=0;i--){
            if(board[i][i]=='X'){
                countP1+=1;
            }
            else if(board[i][i]=='O'){
                countP2+=1;
            }
            else{
                countP1=0;
                countP2=0;
            }
        }
        return 0;
    }

    public static void checkWinner(int[][] board,int n){
        
        
    }
    
    private static void playTicTacToe(int n) {
        if(n<=1){
            System.out.println("Play area too small. ");
            return;
        }
        System.out.println("Tic Tac Toe game is under construction.");
        System.out.println();
        int[][] board = new int[n][n]; 
        // System.out.println("-".repeat(4*n));
        displayTicTacToe(board,n);
        checkWinner(board,n);
    }
}

class RockPaperScissors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] choices = {"rock", "paper", "scissors"};

        System.out.println("Welcome to Rock, Paper, Scissors!");
        System.out.println("Enter your choice (rock, paper, scissors, or quit to exit):");

        while (true) {
            System.out.print("Your choice: ");
            String playerChoice = scanner.nextLine().toLowerCase();

            if (playerChoice.equals("quit")) {
                System.out.println("Thanks for playing! 👋");
                break;
            }

            if (!playerChoice.equals("rock") && !playerChoice.equals("paper") && !playerChoice.equals("scissors")) {
                System.out.println("Invalid choice. Please choose rock, paper, scissors, or quit to exit.");
                continue;
            }

            int computerIndex = random.nextInt(3);
            String computerChoice = choices[computerIndex];

            System.out.println("Computer's choice: " + computerChoice);

            String result = determineWinner(playerChoice, computerChoice);
            System.out.println(result);
        }

        scanner.close();
    }

    private static String determineWinner(String playerChoice, String computerChoice) {
        if (playerChoice.equals(computerChoice)) {
            return "It's a tie! 😐";
        } else if (
            (playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
            (playerChoice.equals("scissors") && computerChoice.equals("paper")) ||
            (playerChoice.equals("paper") && computerChoice.equals("rock"))
        ) {
            return "You win! 🎉";
        } else {
            return "Computer wins! 💻";
        }
    }
}



    
