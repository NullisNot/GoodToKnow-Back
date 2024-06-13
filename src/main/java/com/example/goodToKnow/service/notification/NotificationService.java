package com.example.goodToKnow.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class NotificationService {
  @Autowired
  private WebClient.Builder webClientBuilder;

  public void sendTelegramNotification(String message) {
    String url = "http://localhost:8081/send";

    MessageRequest messageRequest = new MessageRequest();
    messageRequest.setMessage(message);

    WebClient webClient = webClientBuilder.build();

    webClient.post()
        .uri(url)
        .body(Mono.just(messageRequest), MessageRequest.class)
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }

}
