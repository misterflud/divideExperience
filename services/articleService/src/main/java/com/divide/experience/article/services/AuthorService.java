package com.divide.experience.article.services;

import com.divide.experience.article.objects.domain.AuthorModel;

/**
 * Created by AOleynikov on 05.01.2019.
 *
 * <p>Service layer.
 */
public interface AuthorService {

    /**
     * Gets author.
     * @param authorId This is id of author.
     * @return Author.
     */
    AuthorModel getAuthor(Integer authorId);

    /**
     * Gets author.
     * @param email This is registration email of author.
     * @return Author.
     */
    AuthorModel getAuthorByEmail(String email);

    /**
     * Adds author.
     * @param authorModel This is new author.
     */
    void addAuthor(AuthorModel authorModel);
}
