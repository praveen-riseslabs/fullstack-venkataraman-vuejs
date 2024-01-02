package com.riseslabs.AwsBucketS3.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsS3Config {

    //@Value("${aws.accessKey}")
    private String accessKey="AKIAVAND6V4DPBNIYBPC";

    //@Value("${aws.secretKey}")
    private String secretKey="pvMYroioWJwIXoT6wLNQ/jTDc7i+8FSgI+i00vKx+JXEhV8jE";

    //@Value("${aws.region}")
    private String region="riseslabs";

    @Bean
    public AmazonS3 getAmazonS3Client() {
        final BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }
}
