package com.riseslabs.AwsBucketS3.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AmazonS3Config {

	private String awsAccessKey = "AKIAVAND6V4DC36JGN47";

    private String awsSecretKey= "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";

    
    private String region= "ap-south-1";

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials.create(awsAccessKey, awsSecretKey);

        return S3Client.builder()
        		.region(Region.of(region))
                .credentialsProvider(() -> awsCredentials)
                .build();
    }
}
