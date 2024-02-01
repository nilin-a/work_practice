package com.company.task68;

import java.io.Serializable;
import java.util.Random;

public class MyArray implements Serializable {
    private int[] array;
    private int size;

    public MyArray(int size){
        this.size = size;
        this.array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(-10, 10);
        }
    }

    public int getSize() {
        return this.size;
    }

    public int getElement(int index) {
        return array[index];
    }

    public void setElement(int index, int value) {
        array[index] = value;
    }

    public int[] getArray() {
        return this.array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public static void printArray(int[] array){
        System.out.println("Ваш массив: ");
        for (int i = 0; i < array.length; i++){
            System.out.println("[" + i + "]: " + array[i] + " ");
        }
    }
}
