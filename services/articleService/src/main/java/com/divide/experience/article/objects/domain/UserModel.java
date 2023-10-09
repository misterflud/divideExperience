package com.divide.experience.article.objects.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Created by AOleynikov on 21.05.2019.
 */
@Entity
@Table(name = "usr")
@NamedQueries({
        @NamedQuery(name = "UserModel.findById",
                query = "select a from UserModel a where a.id = :id"),
        @NamedQuery(name = "UserModel.findByEmail",
                query = "select a from UserModel a where a.email = :email"),
        @NamedQuery(name = "UserModel.findByNickName",
                query = "select a from UserModel a where a.nickName = :nickName"),
})
public class UserModel extends Model implements Serializable {

    private static final long serialVersionUID = -2583341696584839343L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usr_user_id_seq")
    @SequenceGenerator(name = "usr_user_id_seq", sequenceName = "usr_user_id_seq")
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "NICK_NAME", unique = true)
    private String nickName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "SECOND_NAME")
    private String secondName;

    @Column(name = "THIRD_NAME")
    private String thirdName;

    @Column(name = "DATE_OF_REGISTRATION")
    private Date dateOfRegistration;

    @Column(name = "ROLE")
    @Convert(converter = RoleEnumConverter.class)
    private Role role;

    @Column(name = "PASSWORD")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
