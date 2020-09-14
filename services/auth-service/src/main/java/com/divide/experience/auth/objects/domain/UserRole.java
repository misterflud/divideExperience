package com.divide.experience.auth.objects.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Entity
@Table(name = "USER_ROLE")
public class UserRole extends Model implements Serializable {

    private static final long serialVersionUID = 7521925212648707529L;

    public UserRole() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_role_seq")
    @SequenceGenerator(name = "user_role_seq", sequenceName = "user_role_id_seq")
    @Column(name = "USER_ROLE_ID")
    private Long id;

    @Column(name = "ROLE_NAME")
    private String name;

    @Column(name = "ROLE_DESCRIPTION")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
