package com.example.manng.testnews;

/**
 * Created by manng on 28-Aug-17.
 */

public class News {
    private String author;
    private String title;
    private String description;
    private String image;
    private String link;
    private String time;

    public News() {
    }

    public News(String author, String title, String description, String link, String image, String time) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.image = image;
        this.link = link;
        this.time = time;
    }

    public News(String title, String description, String link, String image) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
