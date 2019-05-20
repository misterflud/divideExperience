package com.divide.experience.article.dao.services.impl;

import com.divide.experience.article.dao.services.ArticleDao;
import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.domain.ArticleModel;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO realisation for article.
 */
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    /**
     * Adds article to database.
     * @param articleModel model.
     */
    @Override
    public void addArticle(ArticleModel articleModel) throws AddingArticleException {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            if (articleModel.getAuthorModel() == null) {
                throw new AddingArticleException();
            }
            session.saveOrUpdate(articleModel);
        }
    }

    /**
     * Updates article in database.
     * @param articleModel This is article.
     */
    @Override
    public void updateArticle(ArticleModel articleModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.saveOrUpdate(articleModel);
        }
    }

    /**
     * Gets article from database.
     * @param articleId This is id of article.
     * @return ArticleModel This returns after updating.
     */
    @Override
    public ArticleModel getArticle(Integer articleId) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (ArticleModel) session.getNamedQuery("ArticleModel.findById")
                    .setParameter("id", articleId).uniqueResult();
        }
    }

    /**
     * Deletes article from database.
     * @param articleId This is article's id.
     */
    @Override
    public void deleteArticle(Integer articleId) {

    }
}
