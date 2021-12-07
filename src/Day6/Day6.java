package Day6;

import util.FileHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {

    public Day6() {
        FileHandler file = new FileHandler("./src/Day6/Day6Input.txt");
        long[] fish = new long[9];

        for(String init : file.getLine(0).split(",")) {
            fish[Integer.parseInt(init)]++;
        }

        for (int i = 0; i < 256; i++) {
            long f0 = fish[0];
            for (int j = 0; j < fish.length-1; j++) {
                fish[j] = fish[j+1];
            }
            fish[6] += f0;
            fish[8] = f0;
        }

        long count = 0;
        for (long c : fish) {
            count += c;
        }

        System.out.println(count);
    }

    public static void main(String[] args) {
        new Day6();
    }
}