package com.divideExperiense.addStory.domainObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by AOleynikov on 02.01.2019.
 */
@Entity
@Table(name = "ARTICLE")
public class ArticleModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Version
    @Column(name = "VERSION")
    private int version;

    @OneToOne
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorModel authorModel;

    @Column(name = "VERSION")
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
