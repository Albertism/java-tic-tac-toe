import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    }
}