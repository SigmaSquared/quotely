package com.gizmo.quotely.cli;

import com.gizmo.quotely.cli.models.LanguageValidationResult;

public interface CLILanguageScanner {
    void printIntro();
    void printReminder();
    LanguageValidationResult getAbbrivatedLanguageFromUserInput(String language);
    void loopQuoteRequestUntilUserExits(String anotherQuoteRequest);
    void close();
}