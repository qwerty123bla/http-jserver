package ru.otus.homework.http.server.processors;

import com.google.gson.Gson;
import ru.otus.homework.http.server.HttpRequest;
import ru.otus.homework.http.server.application.Product;
import ru.otus.homework.http.server.application.ProductsService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CreateProductProcessor implements RequestProcessor {
    private ProductsService productsService;

    public CreateProductProcessor(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        Gson gson = new Gson();
        Product newProduct = gson.fromJson(request.getBody(), Product.class);
        productsService.createNewProduct(newProduct);

        String response = "" +
                "HTTP/1.1 201 Created\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n";
        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
