package br.com.sosvip.apigerenciamentoclientes.aws.sqs.auth;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

public class AWSCredentials {

    public static AwsCredentialsProvider returnCredentials() {
        AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return System.getenv("AWS_ACCESS_KEY");
                    }

                    @Override
                    public String secretAccessKey() {
                        return System.getenv("AWS_SECRET_KEY");
                    }
                };
            };
        };
        return credentialsProvider;
    };
}
