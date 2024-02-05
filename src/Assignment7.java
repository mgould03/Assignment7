import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Assignment7 {
    public static int[] createRandomArray(int arrayLength) {
        int[] randomArray = new int[arrayLength];
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = random.nextInt(101);
        }
        return randomArray;
    }
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int value : array) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static int[] readFileToArray(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                numbers.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void bubbleSort(int[] array) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array [i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter array length: ");
            int arrayLength = scanner.nextInt();
            int[] myArray = createRandomArray(arrayLength);
            System.out.print("Enter filename to write array: ");
            String filename = scanner.next();
            writeArrayToFile(myArray, filename);
            System.out.print("Enter filename to read array: ");
            filename = scanner.next();
            int[] readArray = readFileToArray(filename);
            System.out.println("Array before sorting: " + Arrays.toString(readArray));
            bubbleSort(readArray);
            System.out.println("Array after sorting: " + Arrays.toString(readArray));
        }
    }
}
