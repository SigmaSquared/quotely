package com.gizmo.quotely.services;

import com.gizmo.quotely.cli.models.Quote;

public interface QuoteService {
    Quote getQuoteByLanguage(String aLanguage);
}
