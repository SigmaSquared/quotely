package com.gizmo.quotely.services;

import com.gizmo.quotely.cli.models.Quote;
import com.gizmo.quotely.httpclient.QuoteClient;
import org.springframework.stereotype.Component;

@Component
public class ForismaticBackedQuoteService implements QuoteService {
    private QuoteClient httpClient;
    public ForismaticBackedQuoteService(QuoteClient httpClient){
        this.httpClient = httpClient;
    }

    @Override
    public Quote getQuoteByLanguage(String language) {
        return this.httpClient.requestQuoteByLanguage(language);
    }
}
