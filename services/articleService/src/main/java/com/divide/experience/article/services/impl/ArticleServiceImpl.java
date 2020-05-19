package com.divide.experience.article.services.impl;

import com.divide.experience.article.dao.services.ArticleDao;
import com.divide.experience.article.dao.services.AuthorDao;
import com.divide.experience.article.exceptions.AddingArticleException;
import com.divide.experience.article.objects.PaginationParameters;
import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.domain.AuthorModel;
import com.divide.experience.article.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;
    private AuthorDao authorDao;

    @Override
    public void addArticle(ArticleModel articleModel) throws AddingArticleException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails != null) {
            ArticleModel oldArticle = getArticle(articleModel.getId());
            //TODO: adds protect of saving by stranger author
            if (oldArticle != null) {
                oldArticle.setSaved(true);
                oldArticle.setBody(articleModel.getBody());
                oldArticle.setTitle(articleModel.getTitle());
                articleDao.updateArticle(oldArticle);
            } else {
                articleModel.setDate(new Date());
                articleModel.setAuthorModel(authorDao.getAuthorByEmail(userDetails.getUsername()));
                articleDao.addArticle(articleModel);
            }
        }
    }

    @Override
    public void saveArticle(ArticleModel articleModel) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails != null) {
            ArticleModel oldArticle = getArticle(articleModel.getId());
            if (oldArticle != null) {
                oldArticle.setBody(articleModel.getBody());
                oldArticle.setTitle(articleModel.getTitle());
                articleDao.updateArticle(oldArticle);
            }
        }
    }

    @Override
    public List<ArticleModel> getArticles(PaginationParameters pagination) {
        return articleDao.getArticles(pagination);
    }

    @Override
    public ArticleModel getArticle(Integer articleId) {
        return articleDao.getArticle(articleId);
    }

    @Override
    public ArticleModel generateNewArticle() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails != null) {
            AuthorModel author = authorDao.getAuthorByEmail(userDetails.getUsername());
            if (author != null) {
                if (articleDao.getNotSavedArticle(author) == null) {
                    ArticleModel article = new ArticleModel();
                    article.setSaved(false);
                    article.setAuthorModel(author);
                    article.setDate(new Date());
                    return articleDao.addArticle(article);
                } else {
                    return articleDao.getNotSavedArticle(author);
                }
            }
        }
        return null;
    }

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
