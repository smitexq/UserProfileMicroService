package com.eventhub.UserProfileMicroService.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MailService {
    private final WebClient webClient;

    public MailService(WebClient webClient) {
        this.webClient = webClient;
    }


    public void sendPostRequestWithNoResponse(String uri, Object body) {
        webClient.post()
                .uri(uri)
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .subscribe(success -> {},
                        error -> System.err.println("Ошибка отправки письма: " + error.getMessage())
                );
    }
}
