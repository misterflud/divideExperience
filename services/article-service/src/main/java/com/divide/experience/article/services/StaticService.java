package com.divide.experience.article.services;

import com.divide.experience.article.objects.transport.StaticSource;

import java.io.IOException;
import java.util.List;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 *      Service layer.
 */
public interface StaticService {

    /**
     * Returns byte array of the static resource. Resource can be saved by different strategies -- on a server, on a google image, etc..
     *
     *<p>TODO: adds a table for control static resource. The table should have information about hash sum, where it saved, and so on.
     *
     * @param nameSource the name of the source. It's the first unique identifier of the resource.
     * @param articleId the id of the article, where static source are shown. It's the second unique identifier of the resource.
     * @return byte array of the static resource.
     * @throws IOException exception which can be happened. TODO: adds determent exceptions.
     */
    byte[] getArticleStaticSource(String nameSource, int articleId) throws IOException;

    /**
     * Adds the static source to the server. The server can save it in difference ways.
     *
     * @param source byte array of the static resource.
     * @param nameSource the name of the source. It's the first unique identifier of the resource.
     * @param articleId the id of the article, where static source are shown. It's the second unique identifier of the resource.
     * @throws IOException exception.
     */
    void addArticleStaticSource(byte[] source, String nameSource, int articleId) throws IOException;

    /**
     * Deletes the static resource only from not published article (For one author can be one isn't published article).
     *
     *<p>Only the author of article can delete the image.
     * @param nameSource the name of the source. It's the first unique identifier of the resource.
     *                   The second unique identifier (the id of the article) are taken from session.
     */
    void deleteStaticSource(String nameSource);

    /**
     * Gets list DTOs of the resources which are contained in the article.
     *
     * @param articleId article id.
     * @return list DTOs.
     */
    List<StaticSource> getListStaticsFromArticle(Integer articleId);
}
