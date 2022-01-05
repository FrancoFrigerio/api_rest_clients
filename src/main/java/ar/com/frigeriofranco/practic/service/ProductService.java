package ar.com.frigeriofranco.practic.service;

import ar.com.frigeriofranco.practic.dto.ProductResponseDto;
import ar.com.frigeriofranco.practic.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ProductService {

    Product findById(Long id);

    Product saveProduct(String name, String price, String created_At, MultipartFile file1, MultipartFile file2) throws IOException, ParseException;

    List<ProductResponseDto> getAllProducts();

    List<ProductResponseDto> getProductsLike(String name);
}
