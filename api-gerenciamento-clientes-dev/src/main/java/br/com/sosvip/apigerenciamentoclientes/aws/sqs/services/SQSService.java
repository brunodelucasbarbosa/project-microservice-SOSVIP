package br.com.sosvip.apigerenciamentoclientes.aws.sqs.services;

import br.com.sosvip.apigerenciamentoclientes.aws.sqs.auth.AWSCredentials;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class SQSService {

    public static void sendMessage(String message){

        SqsClient sqsClient = SQSClient.returnSQSClient(AWSCredentials.returnCredentials());

        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("grupo1-sosvip")
                .queueOwnerAWSAccountId(System.getenv("AWS_ACCOUNT_ID")).build();

        GetQueueUrlResponse response = sqsClient.getQueueUrl(request);

        sendMessage(sqsClient, response.queueUrl(), message);

        sqsClient.close();
    }

    public static void sendMessage(SqsClient sqsClient, String queueUrl, String message) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(message)
                .build();
        sqsClient.sendMessage(sendMsgRequest);
    }
}
