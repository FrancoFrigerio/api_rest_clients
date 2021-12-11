package ar.com.frigeriofranco.practic.service;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AWSService {

    String uploadFile(MultipartFile file)throws IOException;
}
