package com.divide.experience.article.dao.services.impl;

import com.divide.experience.article.dao.services.AuthorDao;
import com.divide.experience.article.objects.domain.AuthorModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO realisation for author.
 */
@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addAuthor(AuthorModel authorModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.saveOrUpdate(authorModel);
        }
    }

    @Override
    public void updateAuthor(AuthorModel authorModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.saveOrUpdate(authorModel);
        }
    }

    @Override
    public AuthorModel getAuthor(Integer authorId) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (AuthorModel) session.getNamedQuery("AuthorModel.findById")
                    .setParameter("id", authorId).uniqueResult();
        }
    }

    @Override
    public AuthorModel getAuthorByEmail(String email) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (AuthorModel) session.getNamedQuery("AuthorModel.findByEmail")
                    .setParameter("email", email).uniqueResult();
        }
    }

    @Override
    public void deleteAuthor(Integer authorId) {

    }
}
