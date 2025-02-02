package ru.otus.homework.http.server.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductsService {
    private List<Product> products;

    public ProductsService() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "Milk"),
                new Product(2L, "Bread"),
                new Product(3L, "Cheese")
        ));
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void deleteProductById(Long id) {
        this.products.remove(products.stream().filter(p -> p.getId().equals(id)).findFirst().get());
    }

    public void deleteAll() {
        this.products.clear();
    }

    public void updateTitle(Long id, String title) {
        products.stream().filter(p -> p.getId().equals(id)).findFirst().get().setTitle(title);
    }

    public void createNewProduct(Product product) {
        Long newId = products.stream().mapToLong(Product::getId).max().getAsLong() + 1;
        products.add(new Product(newId, product.getTitle()));
    }
}
