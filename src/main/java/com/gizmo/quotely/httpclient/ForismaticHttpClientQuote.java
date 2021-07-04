package com.gizmo.quotely.httpclient;

import com.gizmo.quotely.cli.models.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component // create client to keep URL away from service
public class ForismaticHttpClientQuote implements QuoteClient {
    private static String FORISMATIC_URL = "https://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=";
    private RestTemplate httpClient;
    public ForismaticHttpClientQuote (RestTemplate httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public Quote requestQuoteByLanguage(String language) {
        ResponseEntity<Quote> response = httpClient.getForEntity(FORISMATIC_URL + language, Quote.class);
        return response.getBody();
    }
}