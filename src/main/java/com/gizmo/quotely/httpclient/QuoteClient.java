package com.gizmo.quotely.httpclient;

import com.gizmo.quotely.cli.models.Quote;
import org.springframework.http.ResponseEntity;

public interface QuoteClient {
    Quote requestQuoteByLanguage(String language);
}
