package com.divide.experience.article.services;

import com.divide.experience.article.objects.domain.ArticleModel;
import com.divide.experience.article.objects.transport.StaticSource;

import java.io.IOException;
import java.util.List;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 *
 * Main interface for each storage wrapper class.
 * In future move it to individual microservice.
 */
public interface StaticResourceStrategy {
    /**
     * Generates uri from params.
     *
     * @param nameSource name.
     * @param articleModel article.
     * @return uri.
     */
    String generateUri(String nameSource, ArticleModel articleModel);

    /**
     * Adds article to storage.
     *
     * @param source source.
     * @param nameSource name source with extension.
     * @param articleModel article.
     * @throws IOException exception. TODO: throws business exception.
     */
    void addArticleStaticSource(byte[] source, String nameSource, ArticleModel articleModel) throws IOException;

    /**
     * Deletes static source from storage.
     *
     * @param nameSource source.
     * @param articleModel article.
     */
    void deleteStaticSource(String nameSource, ArticleModel articleModel);

    /**
     * Gets file.
     *
     * @param nameSource source.
     * @param articleModel article.
     * @return file.
     * @throws IOException exception. TODO: throws business exception.
     */
    byte[] getArticleStaticSource(String nameSource, ArticleModel articleModel) throws IOException;

    /**
     * Get uri list static source from article.
     *
     * @param articleModel
     * @return
     */
    List<StaticSource> getListStaticsFromArticle(ArticleModel articleModel);
}
