package com.gizmo.quotely.cli.models;

public class Language {
    private String name;
    private String abbreviation;
    public Language(String name, String abbreviation){
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName(){
        return this.name;
    }
    public String getAbbreviation(){
        return this.abbreviation;
    }
}