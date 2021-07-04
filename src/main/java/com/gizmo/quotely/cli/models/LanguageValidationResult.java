package com.gizmo.quotely.cli.models;


import com.gizmo.quotely.cli.validation.ValidationStatus;

public class LanguageValidationResult {
    private String message;
    private ValidationStatus status;
    private Language language;

    public LanguageValidationResult(String message, ValidationStatus status, Language language){
        this.message = message;
        this.status = status;
        this.language = language;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidationStatus getStatus() {
        return status;
    }

    public void setStatus(ValidationStatus status) {
        this.status = status;
    }
    public Language getLanguage() {
        return language;
    }

    public void setStatus(Language language) {
        this.language = language;
    }
}