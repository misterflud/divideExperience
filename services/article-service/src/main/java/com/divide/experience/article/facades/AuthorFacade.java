package com.divide.experience.article.facades;

import com.divide.experience.article.objects.transport.AuthorItem;

/**
 * Created by AOleynikov on 17.01.2019.
 */
public interface AuthorFacade {

    /**
     * Adds author.
     *
     * @param authorItem This is dto of author.
     */
    void addAuthor(AuthorItem authorItem);

    /**
     * Deletes author.
     *
     * @param authorItem This is dto of author.
     * @return Result of deleting.
     */
    boolean deleteAuthor(AuthorItem authorItem);

    /**
     * Gets author.
     *
     * @param authorId This is id of author.
     * @return Author.
     */
    AuthorItem getAuthor(Integer authorId);
}
