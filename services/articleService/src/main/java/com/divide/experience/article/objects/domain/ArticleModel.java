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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Entity
@Table(name = "ARTICLE")
@NamedQueries({
        @NamedQuery(name = "ArticleModel.findById",
                query = "SELECT DISTINCT a FROM ArticleModel a LEFT JOIN FETCH a.userModel t WHERE a.id =:id"),
        @NamedQuery(name = "ArticleModel.findById.short",
                query = "SELECT DISTINCT a FROM ArticleModel a WHERE a.id =:id"),
        @NamedQuery(name = "ArticleModel.all",
                query = "SELECT a FROM ArticleModel a"),
        @NamedQuery(name = "ArticleModel.getNotSavedArticle",
                query = "SELECT a FROM ArticleModel a LEFT JOIN FETCH a.userModel at WHERE at.id = :authorId and a.saved = false"),
        @NamedQuery(name = "ArticleModel.list",
                query = "SELECT a FROM ArticleModel a WHERE a.saved = true ORDER BY a.date DESC")
})
public class ArticleModel implements Serializable {

    private static final long serialVersionUID = 641321829372248245L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "article_article_id_seq")
    @SequenceGenerator(name = "article_article_id_seq", sequenceName = "article_article_id_seq", allocationSize = 1)
    @Column(name = "ARTICLE_ID")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private UserModel userModel;

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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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
