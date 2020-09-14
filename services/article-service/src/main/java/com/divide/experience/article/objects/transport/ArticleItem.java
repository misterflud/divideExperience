package com.divide.experience.article.objects.transport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@ApiModel(description = "This is the main transport object for Article. It can contain null fields.")
public abstract class ArticleItem implements Serializable {

    private static final long serialVersionUID = 8493721504926217113L;

    @ApiModelProperty("This id is corresponded with id from data base")
    private Integer id;

    @ApiModelProperty("Title of article")
    private String title;

    @ApiModelProperty("Main text of article (with links to static data")
    private String body;

    @ApiModelProperty("Date of any action")
    private String date;

    @ApiModelProperty("Author of this article. Sometimes, it's unnecessary")
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
