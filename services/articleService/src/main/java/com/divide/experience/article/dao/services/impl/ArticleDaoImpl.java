package com.divide.experience.article.dao.services.impl;

import com.divide.experience.article.dao.services.ArticleDao;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.domain.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO realisation for article.
 */
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public ArticleModel addArticle(ArticleModel articleModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {

            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(articleModel);
            tx.commit();
            return articleModel;
        }
    }

    @Override
    public void updateArticle(ArticleModel articleModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(articleModel);
            tx.commit();
        }
    }

    @Override
    public List<ArticleModel> getArticles(PaginationParameters pagination) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (List<ArticleModel>) session.getNamedQuery("ArticleModel.list")
                    .setMaxResults(pagination.pageSize)
                    .setFirstResult(pagination.currentPage)
                    .getResultList();
        }
    }

    @Override
    public ArticleModel getArticle(Integer articleId) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (ArticleModel) session.getNamedQuery("ArticleModel.findById")
                    .setParameter("id", articleId).uniqueResult();
        }
    }

    @Override
    public void deleteArticle(Integer articleId) {

    }


    @Override
    public ArticleModel getNotSavedArticle(UserModel userModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (ArticleModel) session.getNamedQuery("ArticleModel.getNotSavedArticle")
                    .setParameter("authorId", userModel.getId()).uniqueResult();
        }
    }
}
