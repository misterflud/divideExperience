package com.divide.experience.article.objects.transport;

import java.io.Serializable;

/**
 * Created by AOleynikov on 02.01.2019.
 */
public abstract class ArticleItem implements Serializable {

    private Integer id;

    private String title;

    private String body;

    private String date;

    private AuthorItem authorItem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AuthorItem getAuthorItem() {
        return authorItem;
    }

    public void setAuthorItem(AuthorItem authorItem) {
        this.authorItem = authorItem;
    }
}
