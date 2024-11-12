package com.product.product.service;


import com.product.product.dto.*;
import com.product.product.model.*;
import com.product.product.repo.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAdminService {

    @Autowired
    private CategoryRepository CategoryRepository;

    @Autowired
    private BrandRepository BrandRepository;

    @Autowired
    private TagRepository TagRepository;

    @Autowired
    private ProductRepository ProductRepository;

    @Autowired
    private ProductTagRepository productTagRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ProductAdminService(CategoryRepository CategoryRepository,ProductTagRepository productTagRepository,BrandRepository BrandRepository,TagRepository TagRepository,ProductRepository ProductRepository) {
        this.CategoryRepository = CategoryRepository;
        this.BrandRepository = BrandRepository;
        this.TagRepository = TagRepository;
        this.ProductRepository = ProductRepository;
        this.productTagRepository = productTagRepository;
    }

    public Category addCategory(CategoryDTO categoryDTO) {

        Category category= new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());

        return CategoryRepository.save(category);
    }

    public Brand addBrand(BrandDTO brandDTO) {

//        Brand brand= new Brand();
//
//        brand.setBrandName(brandDTO.getBrandName());
        return BrandRepository.save(modelMapper.map(brandDTO, Brand.class));


    }

    public Tag addTag(TagDTO tagDTO) {

        Tag tag = new Tag();

        tag.setTagName(tagDTO.getTagName());
        return TagRepository.save(tag);

    }

    public Product addProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductPrice(productDTO.getProductPrice());
        product.setQuantityInStock(productDTO.getProductQuantity());
        product.setSKU(productDTO.getSKU());
        product.setIsDiscount(productDTO.getIsDiscount());
        product.setDiscount(productDTO.getDiscount());
        product.setStatus(productDTO.getStatus());

        Category category = CategoryRepository.findById(productDTO.getCategoryId())  .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        product.setCategory(category);

        Brand brand = BrandRepository.findById(productDTO.getBrandId())  .orElseThrow(() -> new IllegalArgumentException("Invalid brand ID"));
        product.setBrand(brand);

        return ProductRepository.save(product);
    }


    public ProductTagDTO addProductTag( ProductTagDTO productTag){
        ProductTag productTag1=new ProductTag();


        Product product = ProductRepository.findById(productTag.getProductId())  .orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        Tag tag = TagRepository.findById(productTag.getTagId())  .orElseThrow(() -> new IllegalArgumentException("Invalid tag ID"));


        productTag1.setProduct(product);
        productTag1.setTag(tag);
         productTagRepository.save(productTag1);
        return productTag;

         //       return modelMapper.map(productTagRepository.save(productTag1),new TypeToken<List<ProductTagDTO>>(){}.getType()) ;
    }

}





