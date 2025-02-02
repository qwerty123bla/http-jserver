package ru.otus.homework.http.server.processors;

import com.google.gson.Gson;
import ru.otus.homework.http.server.HttpRequest;
import ru.otus.homework.http.server.application.Product;
import ru.otus.homework.http.server.application.ProductsService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DeleteProductProcessor implements RequestProcessor {
    private ProductsService productsService;

    public DeleteProductProcessor(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public void execute(HttpRequest request, OutputStream output) throws IOException {
        String jsonResult = null;
        String response = "";
        Gson gson = new Gson();
        if (request.containsParameter("id")) {
            Long id = Long.parseLong(request.getParameter("id"));
            productsService.deleteProductById(id);
            List<Product> products = productsService.getAllProducts();
            //jsonResult = gson.toJson(products);

            response = "" +
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json\r\n" +
                    "\r\n" +
                    gson.toJson(products);
        } else {
            productsService.deleteAll();

            response = "" +
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "\r\n" +
                    "<html><body><h1>Все продукты удалены</h1></body></html>";
        }


        output.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
