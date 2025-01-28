package ru.otus.homework.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int port = 8189;
        try (ServerSocket serverSocket = new ServerSocket((port))) {
            System.out.println("сервер запущен на порту " + port);

            Socket socket = serverSocket.accept();
            System.out.println("Подключился новый клиент");

            byte[] buffer = new byte[8192];
            int n = socket.getInputStream().read(buffer);
            String msg = new String(buffer, 0, n);
            System.out.println(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}