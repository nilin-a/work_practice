package com.company.task68;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        // отправка на сервер текста
        try {
            Socket client = new Socket();
            client.connect(new InetSocketAddress("localhost", 1024));
            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
            printWriter.println("Hello");
            printWriter.flush();
            Scanner scanner = new Scanner(client.getInputStream());
            System.out.println(scanner.nextLine());
        } catch (IOException e) {
            System.out.println();
        }

        // отправка на сервер байтов
        try {
            Socket client = new Socket();
            client.connect(new InetSocketAddress("localhost", 1024));
            DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());
            dataOutputStream.writeDouble(14.22);
            DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
            System.out.println(dataInputStream.readInt());
            dataOutputStream.close();
            dataInputStream.close();
        } catch (IOException e) {
            System.out.println();
        }


        // отправка на сервер объекта
        try {
            Socket client = new Socket();
            client.connect(new InetSocketAddress("localhost", 1024));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            MyArray myArray = new MyArray(10);
            objectOutputStream.writeObject(myArray);
            objectOutputStream.flush();
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(client.getInputStream());
            MyArray newArray = (MyArray) objectInputStream.readObject();
            MyArray.printArray(newArray.getArray());

            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка приведения типов!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
}
