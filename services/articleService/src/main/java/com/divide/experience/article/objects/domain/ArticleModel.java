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
                query = "select distinct c from ArticleModel c left join fetch c.authorModel t where c.id =:id"),
        @NamedQuery(name = "ArticleModel.findById.short",
                query = "select distinct c from ArticleModel c where c.id =:id"),
})
public class ArticleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

//    @Version
//    @Column(name = "VERSION", insertable = false, updatable = false)
//    private int version;

    @OneToOne
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorModel authorModel;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;

    @Column(name = "DATE")
    private Date date;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public int getVersion() {
//        return version;
//    }
//
//    public void setVersion(int version) {
//        this.version = version;
//    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}