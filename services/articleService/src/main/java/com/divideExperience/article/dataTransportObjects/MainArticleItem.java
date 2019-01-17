package com.divideExperience.article.dataTransportObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by AOleynikov on 02.01.2019.
 */
public abstract class MainArticleItem implements Serializable {

    private Integer id;

    private String title;

    private String body;

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

    public AuthorItem getAuthorItem() {
        return authorItem;
    }

    public void setAuthorItem(AuthorItem authorItem) {
        this.authorItem = authorItem;
    }
}
