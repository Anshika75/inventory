package com.kcare.kcare.Product.controller.productController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kcare.kcare.File.FileStorageService;
import com.kcare.kcare.Product.Model.Product;
import com.kcare.kcare.Product.Model.ProductImage;
import com.kcare.kcare.Product.repository.ProductImageRepository;
import com.kcare.kcare.Product.repository.ProductRepository;
import com.kcare.kcare.common.Response;
import com.kcare.kcare.handler.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductMapper productMapper;
    private final FileStorageService fileStorageService;

    public Response<ProductResponse> createProduct(ProductRequest productRequest, List<MultipartFile> productImages) {

        Product product = productMapper.toProduct(productRequest);
        Product savedProduct = productRepository.save(product);

        List<String> imagePaths = productImages.stream()
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList());
        ProductResponse productResponse = productMapper.toProductResponse(savedProduct, imagePaths);

        saveImage(productImages, product.getId());
        return new Response<>(
                productResponse,
                LocalDateTime.now(),
                "Product detail save successfully",
                HttpStatus.CREATED

        );
    }

    public void saveImage(List<MultipartFile> productImages, Integer productId) {

        List<String> imagesPath = fileStorageService.saveProductImages(productImages, productId, "productImages");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found Id: " + productId));
        for (String imagesPath2 : imagesPath) {

            ProductImage productImage = new ProductImage();
            productImage.setImagePath(imagesPath2);
            productImage.setProduct(product);
            productImageRepository.save(productImage);

        }

    }

    public ProductResponse getProductImageById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product Not availabe for Id :" + productId));

        List<ProductImage> allImages = productImageRepository.findAllByProductId(productId);

        if (allImages.isEmpty()) {
            throw new ResourceNotFoundException("Images Not available for Product: " + productId);
        }

        List<String> allImageUrls = allImages.stream()
                .map(image -> {
                    Path normalizedPath = Paths.get(image.getImagePath()).normalize();
                    String relativePath = normalizedPath.toString().replace("\\", "/");
                    String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
                    return baseUrl + "/" + relativePath;

                })
                .collect(Collectors.toList());

        ProductResponse productResponse = productMapper.toProductResponse(product, allImageUrls);

        return productResponse;

    }

}
