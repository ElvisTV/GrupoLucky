package com.elvistezen.grupolucky.ui.home;

public class Phrase {
    private int id;
    private String author;
    private String phrase;
    private String meaning;

    public Phrase(int id, String author, String phrase, String meaning) {
        this.id = id;
        this.author = author;
        this.phrase = phrase;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getMeaning() {
        return meaning;
    }
}
