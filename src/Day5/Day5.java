package Day5;

import util.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class Day5 {
    static int[][] oceanFloor = new int[1000][1000];

    public static void main(String[] args) {
        FileHandler file = new FileHandler("./src/Day5/Day5Input.txt");
        ArrayList<Line> lines = new ArrayList<>();
        for (int i = 0; i < file.getNumOfLines(); i++) {
            lines.add(new Line(file.getLine(i)));
        }

//        System.out.println(lines);

        for (Line line : lines) {
            iterateThroughPoints(line);
        }

        int dangZoneCounter = 0;

        for (int[] row : oceanFloor) {
//            System.out.println(Arrays.toString(row));
            for (int point : row) {
                if(point >= 2)
                    dangZoneCounter++;
            }
        }

        System.out.println(dangZoneCounter);
    }

    public static void iterateThroughPoints(Line line) {

        if (line.y1 == line.y2) {
            for (int i = Math.min(line.x1, line.x2); i <= Math.max(line.x1, line.x2); i++) {
                oceanFloor[line.y1][i]++;
            }
        }

        if(line.x1 == line.x2) {
            for (int i = Math.min(line.y1, line.y2); i <= Math.max(line.y1, line.y2); i++) {
                oceanFloor[i][line.x1]++;
            }
        }
    }
}

class Line {
    int x1;
    int y1;
    int x2;
    int y2;

    public Line(String input) {
        String[] points = input.split("->");
        String[] v1 = points[0].trim().split(",");
        String[] v2 = points[1].trim().split(",");
        x1 = Integer.parseInt(v1[0]);
        y1 = Integer.parseInt(v1[1]);
        x2 = Integer.parseInt(v2[0]);
        y2 = Integer.parseInt(v2[1]);
    }

    @Override
    public String toString() {
        return x1 + "," + y1 + " -> " + x2 + "," + y2;
    }
}
