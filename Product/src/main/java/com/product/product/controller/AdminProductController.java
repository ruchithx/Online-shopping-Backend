package com.product.product.controller;


import com.product.product.dto.BrandDTO;
import com.product.product.dto.CategoryDTO;
import com.product.product.dto.ProductDTO;
import com.product.product.dto.TagDTO;
import com.product.product.model.Brand;
import com.product.product.model.Category;
import com.product.product.model.Product;
import com.product.product.model.Tag;
import com.product.product.service.ProductAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value="api/v1/admin/product")
public class AdminProductController {

    private final ProductAdminService productAdminService;

    public AdminProductController(ProductAdminService productAdminService) {
        this.productAdminService = productAdminService;
    }

    @PostMapping("/addproduct")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody ProductDTO productDTO){

        return productAdminService.addProduct(productDTO);
    }

    @PostMapping("/addcategory")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody CategoryDTO categoryDTO){
        System.out.println("Category added"+ categoryDTO);
        return productAdminService.addCategory(categoryDTO);

    }

    @PostMapping("/addbrand")
    @ResponseStatus(HttpStatus.CREATED)
    public Brand addBrand(@RequestBody BrandDTO brandDTO){

        return productAdminService.addBrand(brandDTO);

    }

    @PostMapping("/addtag")
    @ResponseStatus(HttpStatus.CREATED)
    public Tag addTag(@RequestBody TagDTO tagDTO){
        return productAdminService.addTag(tagDTO);

    }

    @PutMapping("/updateproduct")
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(){
        return "Product is updated";
    }

    @PatchMapping("{id}/price")
    @ResponseStatus(HttpStatus.OK)
    public String updatePrice(@PathVariable Long id){
        return "Price is updated";
    }

    @PatchMapping("{id}/discount")
    @ResponseStatus(HttpStatus.OK)
    public String updateDiscount(@PathVariable Long id){
        return "Discount is updated";
    }

    @PatchMapping("{id}/quantityInStock")
    @ResponseStatus(HttpStatus.OK)
    public String updateQuantityInStock(@PathVariable Long id){
        return "Quantity in stock is updated";
    }

    @PatchMapping("{id}/status")
    @ResponseStatus(HttpStatus.OK)
    public String updateStatus(@PathVariable Long id){
        return "Status is updated";
    }



}
