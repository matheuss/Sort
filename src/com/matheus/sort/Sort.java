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

    public static void shell(Integer[] array) {
        int increment;
        int temp;

        assignments++;
        increment = array.length / 2;

        resetCounters();

        comparisons++; // increment > 0
        while (increment > 0) {
            comparisons++; // incremwent > 0

            assignments++; // i = increment
            for (i = increment; i < array.length; i++) {
                comparisons++; // i < array...
                assignments++; // i++
                assignments++; // j = i
                assignments++; // temp = ...
                arrayAccesses++; // array[i]

                j = i;
                temp = array[i];

                comparisons++; // j >= increment
                arrayAccesses++; // array[...]
                comparisons++; // array[...] > temp
                while (j >= increment && array[j - increment] > temp) {
                    comparisons++; // j >= increment
                    arrayAccesses++; // array[...]
                    comparisons++; // array[...] > temp
                    arrayAccesses += 2; // array[j] = array[...]
                    assignments++; // j -= increment
                    array[j] = array[j - increment];
                    j -= increment;
                }
                arrayAccesses++; // array[j]
                assignments++; // next line
                array[j] = temp;
            }
            comparisons++; // if
            assignments++; // if/else
            if (increment == 2) {
                increment = 1;
            }
            else {
                increment *= (5.0 / 11);
            }
        }
        System.out.println("Shell sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    public static void heap(Integer[] array) {
        int count;
        int end;
        int tmp;

        resetCounters();
        assignments += 2;
        count = array.length;
        end = count - 1;

        heapify(array, count);

        comparisons++; // end > 0
        while (end > 0) {
            comparisons++; // end > 0
            arrayAccesses += 3; // swap
            assignments += 3; // swap
            tmp = array[end];
            array[end] = array[0];
            array[0] = tmp;

            siftDown(array, 0, end - 1);

            assignments++; // end--
            end--;
        }

        System.out.println("Heap sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    private static void heapify(Integer[] array, int count) {
        int start;

        assignments++; // start = ...
        start = (count - 2) / 2;

        comparisons++; // start >= 0
        while (start >= 0) {
            comparisons++; // start >= 0
            siftDown(array, start, count - 1);

            assignments++; // start--
            start--;
        }
    }

    private static void siftDown(Integer[] array, int start, int end){
        int root;

        assignments++; // root = start
        root = start;

        comparisons++; // ... <= end
        while ((root * 2 + 1) <= end) {
            comparisons++; // ... <= end

            int child;

            assignments++; // child = ...
            child = root * 2 + 1;

            comparisons += 2; // if
            if (child + 1 <= end && array[child] < array[child + 1]) {
                assignments++; // child++
                child++;
            }

            comparisons++; // if
            if(array[root] < array[child]) {
                int tmp;

                arrayAccesses += 3; // swap
                assignments += 3; // swap
                tmp = array[root];
                array[root] = array[child];
                array[child] = tmp;
                root = child;
            } else {
                return;
            }
        }
    }

    public static void quick(Integer[] array) {
        resetCounters();
        quick(array, 0, array.length - 1);
        System.out.println("Quick sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    private static void quick(Integer[] array, int low, int high) {
        int middle;
        int pivot;
        int tmp;

        assignments += 4;
        arrayAccesses++; // array[middle]
        middle = low + (high - low) / 2;
        pivot = array[middle];
        i = low;
        j = high;

        comparisons++; // i <= j
        while (i <= j) {
            comparisons++; // i <= j
            comparisons++; //array[j] < pivot
            arrayAccesses++; // array[j]
            while (array[i] < pivot) {
                comparisons++; //array[j] < pivot
                arrayAccesses++; // array[j]
                assignments++; // i++;
                i++;
            }

            comparisons++; // array[j] > pivot
            while (array[j] > pivot) {
                comparisons++; // array[j] > pivot
                assignments++; // j--
                j--;
            }

            comparisons++; // i <= j
            if (i <= j) {
                comparisons++; // i <= j
                arrayAccesses += 3; // swap
                assignments += 3; // swap
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                assignments += 2; // next 2 lines
                i++;
                j--;
            }
        }
        comparisons += 2; // if's
        if (low < j) {
            assignments += 2; // function call;
            quick(array, low, j);
        }
        if (high > i) {
            assignments += 2; // function call;
            quick(array, i, high);
        }
    }

    public static void merge(Integer[] array) {
        resetCounters();
        merge(array, 0, array.length - 1);
        System.out.println("Merge sort\nComparisons: " + comparisons + " Assignments: " + assignments + " Array accesses: " + arrayAccesses);
    }

    private static void merge(Integer[] array, int low, int high) {
        int middle;
        comparisons++; // low < high
        if (low < high) {
            comparisons++; // low < high
            assignments++; // middle = ...
            middle = low + (high - low) / 2;

            assignments += 7; // next 3 calls
            merge(array, low, middle);
            merge(array, middle + 1, high);
            fusion(array, low, middle, high);
        }
    }

    private static void fusion(Integer[] array, int low, int middle, int high) {
        Integer[] tmp = new Integer[array.length];
        int k;

        assignments++; // int i = low
        comparisons++; // i <= high
        for (int i = low; i <= high; i++) {
            comparisons++; // i <= high
            assignments++; // i++
            assignments++; // tmp[i] = array[i]
            arrayAccesses += 2; // next line
            tmp[i] = array[i];
        }

        assignments += 3; // next 3 lines
        i = low;
        j = middle + 1;
        k = low;

        comparisons += 2; // next line
        while (i <= middle && j <= high) {
            comparisons += 3; // while and if
            arrayAccesses += 2; // if
            if (tmp[i] <= tmp[j]) {
                arrayAccesses += 2; // array[k] = tmp[i]
                assignments += 2; // array[k] = tmp[i] and i++;
                array[k] = tmp[i];
                i++;
            } else {
                arrayAccesses += 2; // array[k] = tmp[i]
                assignments += 2; // array[k] = tmp[i] and i++;
                array[k] = tmp[j];
                j++;
            }
            assignments++; // k++
            k++;
        }
        comparisons++; // i <= middle
        while (i <= middle) {
            comparisons++; // i <= middle
            arrayAccesses += 2; // array[k] = tmp[i]
            assignments += 3; // next 3 lines
            array[k] = tmp[i];
            k++;
            i++;
        }
    }

    private static void resetCounters() {
        comparisons = 0L;
        assignments = 0L;
        arrayAccesses = 0L;
    }
 }
