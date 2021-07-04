package com.gizmo.quotely.cli.validation;


import com.gizmo.quotely.cli.models.Language;
import com.gizmo.quotely.cli.models.LanguageValidationResult;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LanguageValidator {

    private Collection<Language> defaultLanguages = new HashSet<>();

    //can overwrite language if needed, but the no arg constructor
    //can set the default. sas iffy on making this an interface...
    public LanguageValidator(Collection<Language> defaultLanguages) {
        this.defaultLanguages = defaultLanguages;
    }

    public LanguageValidator() {
        this(new HashSet<>(Arrays.asList(
                new Language("Russian", "ru"),
                new Language("English", "en"),
                new Language("", "en"))));
    }

    public LanguageValidationResult getLanguageResult(String language) {
        List<Language> languages = defaultLanguages
                .stream()
                .filter(lang -> lang.getName()
                        .equalsIgnoreCase(language))
                .collect(Collectors.toList());

        if (languages.size() == 1)
            return new LanguageValidationResult(
                    "You chose " + language + ".",
                    ValidationStatus.VALID_STATUS,
                    languages.get(0));
        else
            return new LanguageValidationResult(
                    "You chose " + language + ". Unfortunately we do not support this language. Please try again.",
                    ValidationStatus.INVALID_STATUS,
                    new Language(language, "[none]"));
    }
}