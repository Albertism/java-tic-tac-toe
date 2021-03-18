import model.BoardGrid;
import model.Player;
import model.PlayerMarker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        BoardGrid[][] board = new BoardGrid[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                board[i][j].setRow(i);
                board[i][j].setCol(j);
            }
        }
    }

    public static double getRandomNumber(int min, int max) {
        return Math.random() * (max - min + 1) + min;
    }
}


