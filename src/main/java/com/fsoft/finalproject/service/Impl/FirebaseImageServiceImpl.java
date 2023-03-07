package com.fsoft.finalproject.service.Impl;

import com.fsoft.finalproject.service.FirebaseImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class FirebaseImageServiceImpl implements FirebaseImageService {

    @Autowired
    Properties properties;

    @Override
    public String getImageUrl(String name) {
        return String.format(properties.imageUrl, name);
    }

    @Override
    public String save(MultipartFile multipartFile) throws IOException {
        String projectId = "backendtgdd";
        String i =  UUID.randomUUID().toString();
        String filename = multipartFile.getOriginalFilename();
        filename = UUID.randomUUID().toString().concat(this.getExtension(filename));
        File file = this.convertToFile(multipartFile,filename);
        String url = this.uploadFile(file,filename,multipartFile.getContentType());
        file.delete();

        return url;
    }

    public File convertToFile(MultipartFile multipartFile,String filename) throws IOException{
        File tempFile = new File(filename);
        try(FileOutputStream fos = new FileOutputStream(tempFile)){
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    public String uploadFile(File file,String filename,String contentType) throws IOException{
        BlobId blobId = BlobId.of(properties.bucketName,filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        ClassPathResource serviceAccount = new ClassPathResource("serviceAccountKey.json");
        Credentials credentials = GoogleCredentials.fromStream(serviceAccount.getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(properties.imageUrl, URLEncoder.encode(filename, StandardCharsets.UTF_8));
    }

    @Override
    public void delete(String name) throws IOException {
        Bucket bucket = StorageClient.getInstance().bucket();

        if (StringUtils.isEmpty(name)) {
            throw new IOException("invalid file name");
        }
        Blob blob = bucket.get(name);

        if (blob == null) {
            throw new IOException("file not found");
        }

        blob.delete();
    }

    @Configuration
    @ConfigurationProperties(prefix = "firebase")
    public class Properties {


        private String bucketName;


        private String imageUrl;

        public String getBucketName() {
            return bucketName;
        }

        public void setBucketName(String bucketName) {
            this.bucketName = bucketName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
