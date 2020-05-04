package com.divide.experience.article.services.impl;

import com.divide.experience.article.dao.services.ArticleDao;
import com.divide.experience.article.dao.services.AuthorDao;
import com.divide.experience.article.objects.domain.AuthorModel;
import com.divide.experience.article.objects.transport.StaticSource;
import com.divide.experience.article.services.StaticResourceStrategy;
import com.divide.experience.article.services.StaticService;
import com.divide.experience.article.services.TypeStaticResourceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Service
public class StaticServiceImpl implements StaticService {

    private Map<String, StaticResourceStrategy> map;
    private ArticleDao articleDao;
    private AuthorDao authorDao;

    @Override
    public byte[] getArticleStaticSource(String nameSource, int articleId) throws IOException {
        return map.get(TypeStaticResourceStrategy.SERVER.getBeanName())
                .getArticleStaticSource(nameSource, articleDao.getArticle(articleId));
    }

    @Override
    public void addArticleStaticSource(byte[] source, String nameSource, int articleId) throws IOException {
        map.get(TypeStaticResourceStrategy.SERVER.getBeanName())
                .addArticleStaticSource(source, nameSource, articleDao.getArticle(articleId));
    }

    @Override
    public void deleteStaticSource(String nameSource) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails != null) {
            AuthorModel author = authorDao.getAuthorByEmail(userDetails.getUsername());
            map.get(TypeStaticResourceStrategy.SERVER.getBeanName())
                    .deleteStaticSource(nameSource, articleDao.getNotSavedArticle(author)); 
        }
    }

    @Override
    public List<StaticSource> getListStaticsFromArticle(Integer articleId) {
        return map.get(TypeStaticResourceStrategy.SERVER.getBeanName())
                .getListStaticsFromArticle(articleDao.getArticle(articleId));
    }

    @Autowired
    public void setMap(Map<String, StaticResourceStrategy> map) {
        this.map = map;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Autowired
    public void setAuthorDao(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }
}
