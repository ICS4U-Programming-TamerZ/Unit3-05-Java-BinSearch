package com.example;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RecBinSearch {
    /**
     * Performs binary search recursively.
     *
     * This program reads input from a file named input.txt,
     * where each line contains a sorted array of integers separated by spaces
     * followed by a space and the target element to search for.
     * It then performs binary search for each line using the recursive function "binarySearch",
     * and writes the results to a file named output.txt.
     *
     * @author Tamer
     * @version 1.0
     * @since 2024-05-06
     */

    // Recursive function to perform binary search
    public static int binarySearch(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1; // Element not found
        }
        
        int mid = low + (high - low) / 2;
        
        if (arr[mid] == target) {
            return mid; // Element found at mid index
        } else if (arr[mid] < target) {
            return binarySearch(arr, target, mid + 1, high); // Search in the right half
        } else {
            return binarySearch(arr, target, low, mid - 1); // Search in the left half
        }
    }

    public static void main(String[] args) {
        try {
            // Open input file for reading
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);

            // Open output file for writing
            File outputFile = new File("output.txt");
            FileWriter writer = new FileWriter(outputFile);

            // Read input file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int[] arr = new int[parts.length - 1];
                try {
                    // Convert string array elements to integers
                    for (int i = 0; i < parts.length - 1; i++) {
                        arr[i] = Integer.parseInt(parts[i]);
                    }
                    int target = Integer.parseInt(parts[parts.length - 1]);

                    // Performing binary search using the recursive function
                    int index = binarySearch(arr, target, 0, arr.length - 1);

                    // Writing the result to output.txt
                    if (index != -1) {
                        writer.write("Target " + target + " found at index " + index + " in the array.\n");
                    } else {
                        writer.write("Target " + target + " not found in the array.\n");
                    }
                } catch (NumberFormatException e) {
                    writer.write("Error: Non-integer value found in the input.\n");
                }
            }

            // Close scanner and writer
            scanner.close();
            writer.close();
            System.out.println("Results written to output.txt");

        } catch (IOException e) {
            // Handle IOException
            System.err.println("Error: " + e.getMessage());
        }
    }
}
