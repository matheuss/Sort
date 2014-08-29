/**
 * Created by matheus on 28/08/14.
 */

package com.matheus.sort;

public abstract class Sort{
    private static int i;
    private static int j;
    private static int swap;
    private static long comparisons;
    private static long assignments;
    private static long arrayAccesses;

    public static void bubble(Integer[] array) {
        resetCounters();

        assignments++; // i = 0
        for (i = 0; i < array.length - 1; i++) {
            comparisons++; // i < array...
            assignments++; // i++

            assignments++; // j = 0;
            for (j = 0; j < array.length - i - 1; j++) {
                comparisons++; // j < array...
                assignments++; // j++

                comparisons++; // if
                arrayAccesses += 2; // if
                if (array[j] > array[j + 1]) {
                    arrayAccesses += 4;
                    assignments += 3; // next 3 lines
                    swap = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = swap;
                }
            }
        }
        System.out.println("Bubble sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    public static void selection(Integer[] array) {
        int position;

        resetCounters();

        assignments++; // i = 0;
        for(i = 0; i < array.length; i++) {
            comparisons++; // i < array...
            assignments++; // i++
            assignments++; // next line
            position = i;

            assignments++; // j = 0;
            for(j = i + 1; j < array.length; j++) {
                comparisons++; // j < array...
                assignments++; // j++

                comparisons++; // if
                arrayAccesses += 2; // if
                if(array[position] > array[j]) {
                    assignments++; // next line
                    position = j;
                }
            }
            comparisons++; // if
            if(position != i) {
                arrayAccesses += 4;
                assignments += 3; // next 3 lines
                swap = array[i];
                array[i] = array[position];
                array[position] = swap;

            }
        }
        System.out.println("Selection sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    public static void insertion(Integer[] array) {
        int k;

        resetCounters();

        assignments++; // i = 1;
        for(i = 1; i < array.length; i++) {
            comparisons++; // i < array...
            assignments++; // i++
            assignments++; // next line
            k = i;

            comparisons += 2;
            arrayAccesses += 2;
            while(k > 0 && array[k] < array[k - 1]) {
                arrayAccesses += 4;
                assignments += 3; // next 3 lines
                swap = array[k];
                array[k] = array[k - 1];
                array[k - 1] = swap;

                assignments++;
                k--;
             }
        }
        System.out.println("Insertion sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    private static void resetCounters() {
        comparisons = 0L;
        assignments = 0L;
        arrayAccesses = 0L;
    }
}
