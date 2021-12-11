package ar.com.frigeriofranco.practic.controller;


import ar.com.frigeriofranco.practic.service.AWSService;
import ar.com.frigeriofranco.practic.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    AWSService awsService;

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?>getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?>saveProduct(
               @RequestParam(name = "name")String name,
               @RequestParam(name = "price")String price,
               @RequestParam(name = "created_At") String created_At,
               @RequestParam(name = "file1")MultipartFile file1,
               @RequestParam(name = "file2")MultipartFile file2) throws IOException, ParseException {
        return ResponseEntity.ok().body(productService.saveProduct(name,price,created_At,file1,file2));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?>getProducto(@PathVariable(name = "id")Long id){
        return ResponseEntity.ok().body(productService.findById(id));
    }

}
