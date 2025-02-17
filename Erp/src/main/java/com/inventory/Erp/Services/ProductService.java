package com.inventory.Erp.Services;

import com.inventory.Erp.Repository.ProductRepository;
import com.inventory.Erp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockCardService stockCardService;

    public ProductService(ProductRepository productRepository, StockCardService stockCardService) {
        this.productRepository = productRepository;
        this.stockCardService = stockCardService;
    }

    public Product createNewProduct(Product product,int initialStock){
        product.setProductName(product.getProductName());
        product.setStock(product.getStock());
        product.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        product.setDescription(product.getProductName());
        product.setProductId(product.getProductId());
        product.setStatus(true);
        stockCardService.createStockCard(product,initialStock);
        return productRepository.save(product);
    }
    public Product findProductById(Long productId){
        Product searchproduct = productRepository.findById(productId).orElseThrow(()->
                new RuntimeException("Product with" + productId + "not found"));
        return searchproduct;
    }

    public List<Product> getAllProdcut(){
        return productRepository.findAll();
    }

    public Product updateProduct(long id, Product newProductDetails) {
        Optional<Product> findProductByid = productRepository.findById(id);
        if (!findProductByid.isPresent()) {
            throw new RuntimeException("Product not found!");
        }
        Product existingProduct = findProductByid.get();
        existingProduct.setProductName(existingProduct.getProductName());
        existingProduct.setProductId(existingProduct.getProductId());
        existingProduct.setStock(existingProduct.getStock());
        existingProduct.setStatus(true);
        existingProduct.setPrice(existingProduct.getPrice());
        existingProduct.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        existingProduct.setDescription(existingProduct.getDescription());
        return productRepository.save(existingProduct);
    }
    public void deleteProduct(long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStatus(false);
        productRepository.save(product);
    }

}
