package Day7;
import util.FileHandler;
import java.util.Arrays;

public class Day7 {

    public Day7() {
        FileHandler file = new FileHandler("./src/Day7/Day7Input.txt");
        String[] inputs =  file.getLine(0).split(",");
        int[] crabs = new int[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            crabs[i] = Integer.parseInt(inputs[i]);
        }
        Arrays.sort(crabs);

        int minFuel = Integer.MAX_VALUE;
        int minFuel2 = Integer.MAX_VALUE;

        for (int i = 0; i < crabs[crabs.length-1]; i++) {
            int fuel = 0;
            int fuel2 = 0;

            for (int crab : crabs) {
                int cost = Math.abs(crab - i);
                fuel += cost;
                fuel2 += ((cost * (cost + 1)) / 2);
            }

            minFuel = Math.min(minFuel, fuel);
            minFuel2 = Math.min(minFuel2, fuel2);
        }

        System.out.println(minFuel);
        System.out.println(minFuel2);
    }

    public static void main(String[] args) {
        new Day7();
    }
}
