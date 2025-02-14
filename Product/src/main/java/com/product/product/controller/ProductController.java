package com.product.product.controller;

import com.product.product.dto.BrandDTO;
import com.product.product.dto.ProductDTO;
import com.product.product.dto.ProductDiscreseDTO;
import com.product.product.model.Brand;
import com.product.product.model.Category;
import com.product.product.model.Product;
import com.product.product.model.Tag;
import com.product.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/product/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getproducts")
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }



    @GetMapping("/getcategories")
    public  List<Category> getCategories(){
        return productService.getAllCategories();
    }

    @GetMapping("/getbrands")
    public List<Brand> getBrands(){
        return productService.getAllBrands();
    }

//    @GetMapping("/gettags")
//    public List<Tag> getTags(){
//        return productService.getAllTag();
//    }

    @GetMapping("/getproductbyid/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @PostMapping("/getproductsbyids")
    public List<Product> getProductsByIds(@RequestBody List<Integer> productIds) {
        System.out.println(productIds);
        return productService.getProductsByIds(productIds);
    }





    @GetMapping("/getproductbycategory/{category}")
    public List<ProductDTO> getProductByCategory(@PathVariable String category){
        return productService.getProductByCategory(category);
    }

    @GetMapping("/getproductbybrand/{brand}")
    public List<ProductDTO> getProductByBrand(@PathVariable String brand){
        return productService.getProductByBrand(brand);
    }

    @GetMapping("/checkquantity/{id}/{quantity}")
    public boolean checkQuantity(@PathVariable Long id, @PathVariable Integer quantity){
        return productService.checkQuantity(id, quantity);
    }

    @PatchMapping("/discresquantity")
    public Product discresQuantity(@RequestBody ProductDiscreseDTO productDiscreseDTO){
        return productService.discresQuantity(productDiscreseDTO.getProductId(), productDiscreseDTO.getQuantity());
    }

//
//    @GetMapping("/getproductbytag/{tag}")
//    public String getProductByTag(@PathVariable String tag){
//        return "Product by tag is ready";
//    }







}
