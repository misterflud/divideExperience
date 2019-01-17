package com.divideExperience.article.daoServices.Impl;

import com.divideExperience.article.daoServices.AuthorDao;
import com.divideExperience.article.domainObjects.ArticleModel;
import com.divideExperience.article.domainObjects.AuthorModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addAuthor(AuthorModel authorModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()){
            session.saveOrUpdate(authorModel);
        }
    }

    @Override
    public void updateAuthor(AuthorModel authorModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()){
            session.saveOrUpdate(authorModel);
        }
    }

    @Override
    public AuthorModel getAuthor(Long authorId) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()){
            return (AuthorModel) session.getNamedQuery("AuthorModel.findById").setParameter("id", authorId).uniqueResult();
        }
    }

    @Override
    public void deleteAuthor(Long authorId) {

    }
}
