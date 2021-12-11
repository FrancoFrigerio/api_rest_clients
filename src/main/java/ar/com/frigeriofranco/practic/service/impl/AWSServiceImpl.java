package ar.com.frigeriofranco.practic.service.impl;


import ar.com.frigeriofranco.practic.service.AWSService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
@Slf4j
public class AWSServiceImpl implements AWSService {


    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.bucketName}")
    private String buketName;

    @Value("${amazonProperties.endpointUrl}")
    private String url;

     @Value("${amazonProperties.bucketName}")
     private String bucketName;


    @Override
    public String uploadFile(MultipartFile file) throws IOException{
        File mainFile = new File(file.getOriginalFilename());
        try(FileOutputStream stream = new FileOutputStream(mainFile)){
            stream.write(file.getBytes());
            String newFileName = System.currentTimeMillis()+mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(buketName,newFileName,mainFile);
            amazonS3.putObject(request);
            return url.concat(bucketName).concat("/").concat(newFileName);
        }catch (IOException e){
            log.info(e.getMessage());
            throw new IOException("file not found");
        }

    }
}
