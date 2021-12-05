package Day3;

import util.FileHandler;

import java.util.ArrayList;

public class Day3 {

    static int[] zeroCounts = new int[12];
    static int[] oneCounts = new int[12];
    static  FileHandler file;

    public static void main(String[] args) {
        file = new FileHandler("./src/Day3/Day3Input.txt");

        for (int i = 0; i < file.getNumOfLines(); i++) {
            iterateThroughLine(file.getLine(i));
        }

        System.out.println("Power Consumption: " + (calcPowerValues(true) * calcPowerValues(false)));
        System.out.println("Life support: " + (calcLifeSupportValues(true) * calcLifeSupportValues(false)));
    }

    public static void iterateThroughLine(String line) {
        for (int i = 0; i < line.length(); i++) {
            if(line.charAt(i) == '0')
                zeroCounts[i]++;
            else
                oneCounts[i]++;
        }
    }

    public static int calcPowerValues(boolean max) {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            if(max)
                value.append(Math.max(zeroCounts[i], oneCounts[i]) == zeroCounts[i] ? 0 : 1);
            else
                value.append(Math.min(zeroCounts[i], oneCounts[i]) == zeroCounts[i] ? 0 : 1);
        }

        return Integer.parseInt(value.toString(), 2);
    }

    public static int calcLifeSupportValues(boolean max) {
        StringBuilder value = new StringBuilder();

        ArrayList<String> arrayListOfFile = new ArrayList<>();

        for (int k = 0; k < file.getNumOfLines(); k++) {
            arrayListOfFile.add(file.getLine(k));
        }

        ArrayList<String> tempArray = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            value.append(max ? (Math.max(zeroCounts[i], oneCounts[i]) == oneCounts[i] ? 1 : 0) : (Math.min(zeroCounts[i], oneCounts[i]) == zeroCounts[i] ? 0 : 1));

            zeroCounts = new int[12];
            oneCounts = new int[12];

            for (String item : arrayListOfFile) {
                if (item.startsWith(value.toString())) {
                    tempArray.add(item);
                    iterateThroughLine(item);
                }
            }

            arrayListOfFile = (ArrayList<String>) tempArray.clone();
            tempArray.clear();

            if(arrayListOfFile.size() == 1) {
                value = new StringBuilder(arrayListOfFile.get(0));
                break;
            }
        }

        return Integer.parseInt(value.toString(), 2);
    }
}