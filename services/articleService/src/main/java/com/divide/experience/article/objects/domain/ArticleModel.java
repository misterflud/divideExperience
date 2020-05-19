package com.divide.experience.article.objects.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Entity
@Table(name = "ARTICLE")
@NamedQueries({
        @NamedQuery(name = "ArticleModel.findById",
                query = "SELECT DISTINCT a FROM ArticleModel a LEFT JOIN FETCH a.authorModel t WHERE a.id =:id"),
        @NamedQuery(name = "ArticleModel.findById.short",
                query = "SELECT DISTINCT a FROM ArticleModel a WHERE a.id =:id"),
        @NamedQuery(name = "ArticleModel.all",
                query = "SELECT a FROM ArticleModel a"),
        @NamedQuery(name = "ArticleModel.getNotSavedArticle",
                query = "SELECT a FROM ArticleModel a LEFT JOIN FETCH a.authorModel at WHERE at.id = :authorId and a.saved = false"),
        @NamedQuery(name = "ArticleModel.list",
                query = "SELECT a FROM ArticleModel a WHERE a.saved = true ORDER BY a.date DESC")
})
public class ArticleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorModel authorModel;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "SAVED")
    private boolean saved = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
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

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
