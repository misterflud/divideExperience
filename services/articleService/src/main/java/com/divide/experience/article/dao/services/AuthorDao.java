package com.divide.experience.article.dao.services;

import com.divide.experience.article.objects.domain.AuthorModel;

/**
 * Created by AOleynikov on 04.01.2019.
 *
 * <p>DAO for author.
 */
public interface AuthorDao {

    /**
     * Adds author.
     * @param authorModel This is new author.
     */
    void addAuthor(AuthorModel authorModel);

    /**
     * Updates author in database.
     * @param authorModel This author model with updated fields.
     */
    void updateAuthor(AuthorModel authorModel);

    /**
     * Gets author.
     * @param authorId This is id.
     * @return Author.
     */
    AuthorModel getAuthor(Integer authorId);

    /**
     * Gets author.
     * @param email This is registration email of author(user).
     * @return Author This is what exactly adds to database.
     */
    AuthorModel getAuthorByEmail(String email);

    /**
     * Deletes author.
     * @param authorId This is id of author.
     */
    void deleteAuthor(Integer authorId);
}
