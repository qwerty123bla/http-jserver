package ru.otus.homework.http.server;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dispatcher {
    private Map<String, RequestProcessor> router;
    private RequestProcessor welcomeProcessor;
    private Default404Processor default404Processor;
    public Dispatcher() {
        this.welcomeProcessor = new WelcomeProcessor();
        this.router = new HashMap<>();
        this.router.put("/welcome", new WelcomeProcessor());
        this.default404Processor = new Default404Processor();
    }

    public void execute(HttpRequest request, OutputStream output) throws IOException {
        router.getOrDefault(request.getUri(), default404Processor).execute(request, output);
    }
}
