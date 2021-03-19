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
        Player playerO = new Player(PlayerMarker.O);
        Player playerX = new Player(PlayerMarker.X);
        Player[] playerArray = {playerO, playerX};
        Player currentPlayer = getRandomNumber(0, 1) == 0 ? playerO : playerX;
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
        System.out.println("Player " + currentPlayer.getStringMarker() + " goes first");

        for (int i = 0; i < shuffledBoard.length; i++) {
            System.out.println("Player " + currentPlayer.getStringMarker() + "'s turn");
            shuffledBoard[i].setOccupant(currentPlayer);
            printBoard(board);
            currentPlayer = currentPlayer == playerX ? playerO : playerX;
        }

        System.out.println("game over");
        printBoard(board);
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


