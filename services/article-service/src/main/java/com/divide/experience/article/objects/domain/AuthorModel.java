package com.divide.experience.article.objects.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@Entity
@Table(name = "AUTHOR")
@NamedQueries({
        @NamedQuery(name = "AuthorModel.findById",
                query = "select a from AuthorModel a where a.id = :id"),
        @NamedQuery(name = "AuthorModel.findByEmail",
                query = "select a from AuthorModel a where a.email = :email")
})
public class AuthorModel implements Serializable {

    private static final long serialVersionUID = 4355447490223112884L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "author_author_id_seq")
    @SequenceGenerator(name = "author_author_id_seq", sequenceName = "author_author_id_seq")
    @Column(name = "AUTHOR_ID")
    private Integer id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NICK_NAME")
    private String nickName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "THIRD_NAME")
    private String thirdName;

    @Column(name = "DATE_OF_REGISTRATION")
    private Date dateOfRegistration;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }
}
