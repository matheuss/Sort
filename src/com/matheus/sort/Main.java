package com.matheus.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by matheus on 28/08/14.
 */
public class Main {
    private static int SIZE = 10000;

    public static void main(String[] args) {
        Integer[] array = new Integer[SIZE];
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < SIZE; i++) {
            list.add(i);
        }

        Collections.shuffle(list);

        System.out.println("Array size: " + SIZE);

        array = list.toArray(array);
        Sort.bubble(array);

        array = list.toArray(array);
        Sort.selection(array);

        array = list.toArray(array);
        Sort.insertion(array);

        array = list.toArray(array);
        Sort.shell(array);

    }
}