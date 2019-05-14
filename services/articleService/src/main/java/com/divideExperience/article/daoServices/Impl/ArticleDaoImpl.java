package com.divideExperience.article.daoServices.Impl;

import com.divideExperience.article.domainObjects.ArticleModel;
import com.divideExperience.article.daoServices.ArticleDao;
import com.divideExperience.article.exceptions.AddingArticleException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

//    private SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);;


    @Override
    public void addArticle(ArticleModel articleModel) throws AddingArticleException {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()){
            if (articleModel.getAuthorModel() == null) {
                throw new AddingArticleException();
            }
            session.saveOrUpdate(articleModel);
        }
    }

    @Override
    public void updateArticle(ArticleModel articleModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()){
            session.saveOrUpdate(articleModel);
        }
    }

    @Override
    public ArticleModel getArticle(Integer articleId) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()){
            return (ArticleModel) session.getNamedQuery("ArticleModel.findById").setParameter("id", articleId).uniqueResult();
        }
    }

    @Override
    public void deleteArticle(Integer articleId) {
//        sessionFactory.getCurrentSession().delete();
    }
}
