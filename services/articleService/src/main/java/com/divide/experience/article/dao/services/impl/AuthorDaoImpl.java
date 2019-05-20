package com.divide.experience.article.dao.services.impl;

import com.divide.experience.article.dao.services.AuthorDao;
import com.divide.experience.article.objects.domain.AuthorModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO realisation for author.
 */
@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Adds author.
     * @param authorModel This is new author.
     */
    @Override
    public void addAuthor(AuthorModel authorModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.saveOrUpdate(authorModel);
        }
    }

    /**
     * Updates author in database.
     * @param authorModel This author model with updated fields.
     */
    @Override
    public void updateAuthor(AuthorModel authorModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.saveOrUpdate(authorModel);
        }
    }

    /**
     * Gets author.
     * @param authorId This is id.
     * @return Author.
     */
    @Override
    public AuthorModel getAuthor(Integer authorId) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (AuthorModel) session.getNamedQuery("AuthorModel.findById")
                    .setParameter("id", authorId).uniqueResult();
        }
    }

    /**
     * Gets author.
     * @param email This is registration email of author(user).
     * @return Author This is what exactly adds to database.
     */
    @Override
    public AuthorModel getAuthorByEmail(String email) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (AuthorModel) session.getNamedQuery("AuthorModel.findByEmail")
                    .setParameter("email", email).uniqueResult();
        }
    }

    /**
     * Deletes author.
     * @param authorId This is id of author.
     */
    @Override
    public void deleteAuthor(Integer authorId) {

    }
}
