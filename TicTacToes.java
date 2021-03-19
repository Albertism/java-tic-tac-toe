import model.BoardGrid;
import model.Player;
import model.PlayerMarker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class TicTacToes {
    public static void main(String[] args) throws IOException {
        System.out.println("Input size of the board: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        while (n < 2 || n > 10) {
            System.out.println("Invalid input, make sure the size is bigger than 1 and smaller than 11");
            System.out.println("Input size of the board: ");
            n = Integer.parseInt(reader.readLine());
        }
        // Initialize players and board
        Player playerO = new Player(PlayerMarker.O);
        Player playerX = new Player(PlayerMarker.X);
        Player currentPlayer = getRandomNumber(0, 1) == 0 ? playerO : playerX;
        Player winner = null;
        // Holds game board grids for the game to place in
        BoardGrid[][] board = new BoardGrid[n][n];
        // Holds grids that represents each turn, it will later be shuffled to produce random order
        BoardGrid[] shuffledBoard = new BoardGrid[(int) Math.pow(n, 2)];
        int shuffleBoardIndex = 0;

        // initiate the board and shuffledBoard
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                BoardGrid grid = new BoardGrid(i, j);
                board[i][j] = grid;
                shuffledBoard[shuffleBoardIndex] = grid;
                shuffleBoardIndex++;
            }
        }

        // Shuffles board to imitate random turns
        Collections.shuffle(Arrays.asList(shuffledBoard));

        // Begin game session
        System.out.println("Player " + currentPlayer.getStringMarker() + " goes first");

        // Each shuffled board grid represents a random turn
        for (int i = 0; i < shuffledBoard.length; i++) {
            shuffledBoard[i].setOccupant(currentPlayer);
            // After player takes turn, see if the player has won
            printBoard(board);
            if (didPlayerWin(shuffledBoard[i], board, currentPlayer)) {
                winner = currentPlayer;
                break;
            }
            // Switch player and proceed
            currentPlayer = currentPlayer == playerX ? playerO : playerX;
        }

        // End of the game session, print board and the result
        printBoard(board);
        if (winner != null) {
            System.out.println(winner.getStringMarker() + " Won!");
        } else {
            System.out.println("It was a draw.");
        }
    }

    // Checks if the player won after the move
    public static boolean didPlayerWin(BoardGrid playedGrid, BoardGrid[][] board, Player currentPlayer) {
        int currentRow = playedGrid.getRow();
        int currentCol = playedGrid.getCol();

        return didPlayerWinVertically(board, currentCol, currentPlayer) ||
                didPlayerWinHorizontally(board, currentRow, currentPlayer) ||
                didPlayerWinDiagonalLeft(board, currentPlayer) ||
                didPlayerWinDiagonalRight(board, currentPlayer);
    }

    public static boolean didPlayerWinHorizontally(BoardGrid[][] board, int currentRow, Player currentPlayer) {
        int count = 0;
        int n = board.length;
        BoardGrid currentGrid = board[currentRow][0];
        // count player's marker towards right
        while (currentGrid.isOccupied() && currentGrid.getOccupant() == currentPlayer) {
            count++;
            if (currentGrid.getCol() + 1 == n) {
                break;
            }
            currentGrid = board[currentRow][currentGrid.getCol() + 1];
        }

        return count >= n;
    }

    public static boolean didPlayerWinVertically(BoardGrid[][] board, int currentCol, Player currentPlayer) {
        int count = 0;
        int n = board.length;
        BoardGrid currentGrid = board[0][currentCol];
        // count player's marker downwards
        while (currentGrid.isOccupied() && currentGrid.getOccupant() == currentPlayer) {
            count++;
            if (currentGrid.getRow() + 1 == n) {
                break;
            }
            currentGrid = board[currentGrid.getRow() + 1][currentCol];
        }

        return count >= n;
    }

    public static boolean didPlayerWinDiagonalRight(BoardGrid[][] board, Player currentPlayer) {
        int count = 0;
        int n = board.length;
        BoardGrid currentGrid = board[n - 1][0];
        // count player's marker upwards diagonal right
        while (currentGrid.isOccupied() && currentGrid.getOccupant() == currentPlayer) {
            count++;
            if (currentGrid.getCol() + 1 == n) {
                break;
            }
            currentGrid = board[currentGrid.getRow() - 1][currentGrid.getCol() + 1];
        }

        return count >= n;
    }

    public static boolean didPlayerWinDiagonalLeft(BoardGrid[][] board, Player currentPlayer) {
        int count = 0;
        int n = board.length;
        BoardGrid currentGrid = board[0][0];
        // count player's marker upwards diagonal left
        while (currentGrid.isOccupied() && currentGrid.getOccupant() == currentPlayer) {
            count++;
            if (currentGrid.getRow() + 1 == n) {
                break;
            }
            currentGrid = board[currentGrid.getRow() + 1][currentGrid.getCol() + 1];
        }

        return count >= n;
    }

    public static double getRandomNumber(int min, int max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void printBoard(BoardGrid[][] board) {
        String toPrint = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                toPrint += board[i][j].getOccupantMarker();
                toPrint += " ";
            }
            toPrint += "\n";
        }
        System.out.println(toPrint);
    }
}


