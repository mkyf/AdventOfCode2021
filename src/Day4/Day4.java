package Day4;

import util.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;
public class Day4 {

    private static ArrayList<String> bingoNumbers;
    private static ArrayList<Integer> scores;

    public static void main(String[] args) {
        bingoNumbers = new ArrayList<>();
        scores = new ArrayList<>();
        ArrayList<BingoBoard> bingoBoards = new ArrayList<>();

        FileHandler file = new FileHandler("./src/Day4/Day4Input.txt");
        bingoNumbers.addAll(Arrays.asList(file.getLine(0).split(",")));

        for (int i = 2; i < file.getNumOfLines(); i+=6) {
            String[] board = {file.getLine(i), file.getLine(i+1), file.getLine(i+2), file.getLine(i+3), file.getLine(i+4)};
            bingoBoards.add(new BingoBoard(board));
        }

        for (BingoBoard bingoBoard : bingoBoards) {
            int i = 0;
            while(!hasBingo(bingoBoard, i)) {
                i++;
            }

            System.out.println("i: " + (i-1) + " boardNum = " + bingoBoards.indexOf(bingoBoard));
        }

        System.out.println(scores);

        int max = 0;
        for (int score : scores) {
            if(score >= max)
                max = score;
        }

        System.out.println(max);
    }

    public static boolean hasBingo(BingoBoard bingoBoard, int numPos) {
        ArrayList<String> tempNums = new ArrayList<>();
        boolean[][] boolBoard = new boolean[5][5];

        for (int i = 0; i < numPos; i++) {
            tempNums.add(bingoNumbers.get(i));
        }

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                for (int i = 0; i < tempNums.size(); i++) {
                    if(bingoBoard.board[row][col].equals(tempNums.get(i))) {
                        boolBoard[row][col] = true;
                        tempNums.remove(i);
                        break;
                    }
                }
            }
        }

        for (int row = 0; row < 5; row++) {
            if(boolBoard[row][0] && boolBoard[row][1] && boolBoard[row][2] && boolBoard[row][3] && boolBoard[row][4]) {
                scores.add(calcScore(boolBoard, bingoBoard, numPos));
                return true;
            }
        }

        for (int col = 0; col < 5; col++) {
            if(boolBoard[0][col] && boolBoard[1][col] && boolBoard[2][col] && boolBoard[3][col] && boolBoard[4][col]) {
                scores.add(calcScore(boolBoard, bingoBoard, numPos));
                return true;
            }
        }

        return false;
    }

    public static int calcScore(boolean[][] boolBoard, BingoBoard bingoBoard, int winningNum) {
        int sum = 0;

//        for (boolean[] row : boolBoard) {
//            System.out.println(Arrays.toString(row));
//        }
//        System.out.println();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if(!boolBoard[row][col]) {
                    sum += Integer.parseInt(bingoBoard.board[row][col]);
                }
            }
        }

        return sum * Integer.parseInt(bingoNumbers.get(winningNum-1));
    }
}
//        78 21 33 57 72
//              85 73 16
//           60 87 39 63
//        40 15 77    56
//           62 99 50  3

//        78 21 33 57 72
//        10 69 85 73 16
//        92 60 87 39 63
//        40 15 77 80 56
//        6 62 99 50  3

class BingoBoard {
    public String[][] board = new String[5][5];

    public BingoBoard(String[] input) {
        for (int row = 0; row < input.length; row++) {
            String[] column = input[row].trim().replace("  ", ",").replace(" ", ",").split(",");
            System.arraycopy(column, 0, board[row], 0, column.length);
        }
    }
}
