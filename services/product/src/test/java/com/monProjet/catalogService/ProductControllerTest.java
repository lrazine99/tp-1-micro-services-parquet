package com.monprojet.catalog_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldAddProduct() throws Exception {
		Product product = new Product();
		product.setName("Chaise");
		product.setPrice(49.99);

		mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").value("Chaise"))
				.andExpect(jsonPath("$.price").value(49.99));
	}

	@Test
	void shouldReturnAllProducts() throws Exception {
		// Tu peux aussi d'abord en ajouter un si la liste est vide au départ

		mockMvc.perform(get("/products"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void shouldReturnProductById() throws Exception {
		// Ajoute un produit d'abord
		Product product = new Product();
		product.setName("Table");
		product.setPrice(79.99);

		String response = mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andReturn()
				.getResponse()
				.getContentAsString();

		Product created = objectMapper.readValue(response, Product.class);

		// Puis récupère-le par son ID
		mockMvc.perform(get("/products/" + created.getId()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Table"))
				.andExpect(jsonPath("$.price").value(79.99));
	}

	@Test
	void shouldDeleteProduct() throws Exception {
		// Ajoute un produit
		Product product = new Product();
		product.setName("Lampe");
		product.setPrice(29.99);

		String response = mockMvc.perform(post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andReturn()
				.getResponse()
				.getContentAsString();

		Product created = objectMapper.readValue(response, Product.class);

		// Supprime-le
		mockMvc.perform(delete("/products/" + created.getId()))
				.andExpect(status().isOk());

		// Vérifie qu’il est bien supprimé
		mockMvc.perform(get("/products/" + created.getId()))
				.andExpect(status().isInternalServerError()); // car on lève une RuntimeException
	}
}
