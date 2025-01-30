package ru.otus.homework.http.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private int port;
    private Dispatcher dispatcher;

    public HttpServer(int port) {
        this.port = port;
        this.dispatcher = new Dispatcher();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket((port))) {
            System.out.println("сервер запущен на порту " + port);
            ExecutorService serv = Executors.newFixedThreadPool(4);

            while(true) {
               // try() {
                Socket socket = serverSocket.accept();
                serv.execute(() -> {
                    System.out.println("Подключился новый клиент");

                    byte[] buffer = new byte[8192];
                    int n = 0;
                    try {
                        n = socket.getInputStream().read(buffer);
                        HttpRequest request = new HttpRequest( new String(buffer, 0, n));
                        request.info(true);
                        dispatcher.execute(request, socket.getOutputStream());

                        socket.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
                //}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
