package com.example.reviewerjava.data.model;

import java.util.List;

public class Paragraph {
    private List <String> images;
    private String title;
    private String text;

    public Paragraph(String title, String text, List <String> images) {
        this.title = title;
        this.text = text;
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getParagraphTitle() {
        return title;
    }

    public void setParagraphTitle(String title) {
        this.title = title;
    }

    public String getParagraphText() {
        return text;
    }

    public void setParagraphText(String text) {
        this.text = text;
    }
}
