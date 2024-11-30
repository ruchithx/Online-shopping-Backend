package com.product.product.service;

import com.product.product.dto.BrandDTO;
import com.product.product.dto.ProductDTO;
import com.product.product.model.Brand;
import com.product.product.model.Category;
import com.product.product.model.Product;
import com.product.product.model.Tag;
import com.product.product.repo.BrandRepository;
import com.product.product.repo.CategoryRepository;
import com.product.product.repo.ProductRepository;
import com.product.product.repo.TagRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    public final ProductRepository productRepository;
    public final TagRepository tagRepository;
    public final BrandRepository brandRepository;
    public final CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,CategoryRepository categoryRepository,BrandRepository brandRepository,TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
//        return modelMapper.map(brandRepository.findAll(), new TypeToken<List<BrandDTO>>(){}.getType());
    }
    public List<Tag> getAllTag() {
        return tagRepository.findAll();
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<ProductDTO> getProductByCategory(String category) {
        return modelMapper.map(productRepository.findByCategory(category),new TypeToken<List<ProductDTO>>(){}.getType());
    }

    public List<ProductDTO> getProductByBrand(String brand) {
        return modelMapper.map(productRepository.findByBrand(brand),new TypeToken<List<ProductDTO>>(){}.getType());
    }
    
    public boolean checkQuantity(Long id, int quantity) {
        Product product = productRepository.findById(id).orElse(null);
        if(product.getQuantityInStock() >= quantity) {
            return true;
        }
        return false;
    }

    public Product discresQuantity(Long id, int quantity) {
        Product product = productRepository.findById(id).orElse(null);

        if(product.getQuantityInStock() < quantity) {
            return null;
        }
        product.setQuantityInStock(product.getQuantityInStock() - quantity);
        productRepository.save(product);
        return product;
    }

//    public List<ProductDTO> getProductByTag(String brand) {
//        return modelMapper.map(productRepository.findByTag(brand),new TypeToken<List<ProductDTO>>(){}.getType());
//    }


}
