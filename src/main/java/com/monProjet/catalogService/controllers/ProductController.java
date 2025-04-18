package com.monProjet.catalogService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.monProjet.catalogService.Product;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final List<Product> products = new ArrayList<>();
    private Long idCounter = 1L;

    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        product.setId(idCounter++);
        products.add(product);
        return product;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produit non trouvé avec l'ID : " + id);
        }

        products.remove(product);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Produit supprimé avec succès");
    }

}
