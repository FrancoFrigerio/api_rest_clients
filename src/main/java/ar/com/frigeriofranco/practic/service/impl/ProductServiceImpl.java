package ar.com.frigeriofranco.practic.service.impl;


import ar.com.frigeriofranco.practic.dto.ProductRequestDto;
import ar.com.frigeriofranco.practic.model.Product;
import ar.com.frigeriofranco.practic.repository.ProductRepository;
import ar.com.frigeriofranco.practic.service.AWSService;
import ar.com.frigeriofranco.practic.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    AWSService awsService;

    @Autowired
    ModelMapper mapper;


    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(null);

    }

    @Override
    public Product saveProduct(String name, String price, String created_At, MultipartFile file1,MultipartFile file2) throws IOException, ParseException {
        String urlFile1 = awsService.uploadFile(file1);
        String urlFile2 = awsService.uploadFile(file2);
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(created_At);

        ProductRequestDto productRequestDto = new ProductRequestDto();
            productRequestDto.setUrlPhoto1(urlFile1);
            productRequestDto.setUrlPhoto2(urlFile2);
            productRequestDto.setCreated_At(date);
            productRequestDto.setPrice(Double.parseDouble(price));
            productRequestDto.setName(name);
        return productRepository.save(mapper.map(productRequestDto,Product.class));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsLike(String name) {
        return productRepository.getProductLike(name);
    }

}
