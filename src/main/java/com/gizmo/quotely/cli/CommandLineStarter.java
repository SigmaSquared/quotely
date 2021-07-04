package com.gizmo.quotely.cli;


import com.gizmo.quotely.cli.models.LanguageValidationResult;
import com.gizmo.quotely.cli.models.Quote;
import com.gizmo.quotely.cli.validation.LanguageValidator;
import com.gizmo.quotely.services.QuoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLineStarter implements CommandLineRunner, CLILanguageScanner {
    private static final String GIZMO = "v6.8.1984";
    private final LanguageValidator languageValidator;
    private final QuoteService forismaticService;
    private final Scanner scanner = new Scanner(System.in);

    public CommandLineStarter(QuoteService forismaticService, LanguageValidator languageValidator) {
        this.forismaticService = forismaticService;
        this.languageValidator = languageValidator;
    }

    @Override//test for null language?
    public void run(String... args) {
        try{
            printIntro();
            String language = getLanguageFromUserInput();
            LanguageValidationResult languageValidationResult = getAbbrivatedLanguageFromUserInput(language);
            String abbreviatedLang = languageValidationResult.getLanguage().getAbbreviation();
            System.out.println("Requesting quote " + "from forismatic in your choice of language: " + languageValidationResult.getLanguage().getName() );
            Quote quote = this.forismaticService.getQuoteByLanguage(abbreviatedLang);
            printQuote(quote);
            loopQuoteRequestUntilUserExits(loopChoice());
        } catch (Exception e){
            // should hide the error from user, but print in a debug log of sorts
            System.out.println("Oh noes...something went awry...restarting");
            e.printStackTrace();
            run();
        }
    }

    private void printQuote(Quote result) {
        System.out.println("Printing Quote...");
        System.out.println("Author: " + result.getQuoteAuthor());
        System.out.println("Quote: " + result.getQuoteText());
    }

    @Override
    public LanguageValidationResult getAbbrivatedLanguageFromUserInput(String language) {
        LanguageValidationResult validationStatus = this.languageValidator.getLanguageResult(language);
        switch(validationStatus.getStatus()) {
            case INVALID_STATUS:
                wrongEntry(validationStatus);
                break;
            case VALID_STATUS:
                return validationStatus;
            default:
                wrongEntry(validationStatus); // should never make it here...
                break;
        }
        return null; // Oooops...//perhaps throw a customized exception?
    }

    private void wrongEntry(LanguageValidationResult validationResult) {
        System.out.println("'" + validationResult.getMessage() + "'");
        printReminder();
    }

    @Override
    public void loopQuoteRequestUntilUserExits(String anotherQuoteRequest) {
        while(anotherQuoteRequest.equalsIgnoreCase("Y")){
            String language = getLanguageFromUserInput();
            LanguageValidationResult languageValidationResult = getAbbrivatedLanguageFromUserInput(language);

            String abbreviatedLanguage = (languageValidationResult != null
                    && languageValidationResult.getLanguage() != null
                    && languageValidationResult.getLanguage().getAbbreviation() != null)
                    ?  languageValidationResult.getLanguage().getAbbreviation()
                    :  null;

            if(abbreviatedLanguage != null) {
                Quote quote = this.forismaticService.getQuoteByLanguage(abbreviatedLanguage);
                printQuote(quote);
                loopQuoteRequestUntilUserExits(loopChoice());
            } else {
                System.out.println("Something went terribly wrong...");
                loopQuoteRequestUntilUserExits(loopChoice());
            }
        }
        close();
    }


    @Override
    public void printIntro() {
        System.out.println();
        System.out.println();
        System.out.println("Welcome to quotely " + GIZMO + ".");
        String name = getUserName();
        System.out.println();
        System.out.println("Hey " + name + ", we made using our application a little bit easier by making English our default language.");
        System.out.println();
    }

    @Override
    public void printReminder() {
        System.out.println();
        System.out.println("If you recall, quotely only supports 2 languages:");
        System.out.println(" 1) English (by default)");
        System.out.println(" 2) and Russian");
        System.out.println();
        System.out.println("If you want to request a quote in Russian, please enter 'Russian' and hit enter.");
        System.out.println("If you want to request a quote in English, please enter 'English', or leave it blank and hit enter.");
        System.out.println();
        System.out.println("Please select language.");
    }

    @Override
    public void close() {
        System.out.println("Thanks for using our app. Goodbye.");
        System.out.println();
        this.scanner.close();
        System.exit(0);
    }

    private String loopChoice() {
        System.out.println();
        System.out.println("Would you like another quote? Type 'Y' for YES and 'N' for NO.");
        System.out.println();
        System.out.println("If you want to quit the application, hit Enter or any key + Enter.");
        return this.scanner.nextLine();
    }
    private String getLanguageFromUserInput() {
        System.out.println();
        System.out.println("Please select language.");
        return scanner.nextLine().toLowerCase();
    }

    private String getUserName() {
        System.out.println("Please enter your name.");
        return scanner.nextLine();
    }
}
