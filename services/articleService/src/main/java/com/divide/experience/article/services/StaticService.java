package com.divide.experience.article.services;

import com.divide.experience.article.objects.transport.StaticSource;

import java.io.IOException;
import java.util.List;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public interface StaticService {
    byte[] getArticleStaticSource(String nameSource, int articleId) throws IOException;

    void addArticleStaticSource(byte[] source, String nameSource, int articleId) throws IOException;

    void deleteStaticSource(String nameSource);

    List<StaticSource> getListStaticsFromArticle(Integer articleId);
}
