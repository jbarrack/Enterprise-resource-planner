package com.inventory.Erp.Controller;
import com.inventory.Erp.Services.ProductService;
import com.inventory.Erp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/getProductList",method = RequestMethod.GET)
    public ResponseEntity<List<Product>> getProductList(){

        List<Product> productList = productService.getAllProdcut();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(value = "/createNewProcuct",method = RequestMethod.POST)
    public ResponseEntity<?> createNewProduct(@RequestBody Product product){
        try {
            Product savedProducts = productService.createNewProduct(product, product.getStock());
            return new ResponseEntity<>(savedProducts, HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/getProductById/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getProductById(@PathVariable("id")Long id){
        try {
            Product product = productService.findProductById(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @RequestMapping(value = "/updateProduct/{id}",method = RequestMethod.PUT)
    public  ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,Product newProductsDetails){
        Product updateRecord = productService.updateProduct(id,newProductsDetails);
        return new ResponseEntity<>(updateRecord, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteProduct/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id){
       productService.deleteProduct(id);
     return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
