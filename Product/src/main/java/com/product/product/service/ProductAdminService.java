package com.product.product.service;


import com.product.product.dto.BrandDTO;
import com.product.product.dto.CategoryDTO;
import com.product.product.dto.ProductDTO;
import com.product.product.dto.TagDTO;
import com.product.product.model.Brand;
import com.product.product.model.Category;
import com.product.product.model.Product;
import com.product.product.model.Tag;
import com.product.product.repo.CategoryRepository;
import com.product.product.repo.BrandRepository;
import com.product.product.repo.TagRepository;
import com.product.product.repo.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ModelMapper modelMapper;


    public ProductAdminService(CategoryRepository CategoryRepository,BrandRepository BrandRepository,TagRepository TagRepository,ProductRepository ProductRepository) {
        this.CategoryRepository = CategoryRepository;
        this.BrandRepository = BrandRepository;
        this.TagRepository = TagRepository;
        this.ProductRepository = ProductRepository;
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
}
