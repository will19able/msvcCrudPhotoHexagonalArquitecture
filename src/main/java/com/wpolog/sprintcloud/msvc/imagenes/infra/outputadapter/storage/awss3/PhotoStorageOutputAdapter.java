package com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.storage.awss3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputadapter.storage.awss3.dto.Asset;
import com.wpolog.sprintcloud.msvc.imagenes.infra.outputport.storage.PhotoStorageOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class PhotoStorageOutputAdapter implements PhotoStorageOutputPort {


    private final String S3_BUCKET = "retopragmamsvcs3bucket";

    @Autowired
    private AmazonS3 amazonS3Client;

    @Override
    public String createPhoto(MultipartFile file){
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = String.format("%s.%s", UUID.randomUUID(), extension);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(S3_BUCKET, key, file.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3Client.putObject(putObjectRequest);
            return key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Asset getFile(String key){
        S3Object s3Object = amazonS3Client.getObject(S3_BUCKET, key);
        ObjectMetadata objectMetadata = s3Object.getObjectMetadata();

        try {
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);
            return Asset.builder()
                    .content(bytes)
                    .contenType(objectMetadata.getContentType())
                    .build();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteObject(String key){
        amazonS3Client.deleteObject(S3_BUCKET, key);
    }

    @Override
    public String getFileUrl(String key){
        return String.format("https://%s.s3.amazonaws.com/%s", S3_BUCKET, key);
    }
}
