package com.divideExperience.article.daoServices;

import com.divideExperience.article.domainObjects.AuthorModel;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface AuthorDao {
    void addAuthor(AuthorModel authorModel);
    void updateAuthor(AuthorModel authorModel);
    AuthorModel getAuthor(Integer authorId);
    AuthorModel getAuthorByEmail(String email);
    void deleteAuthor(Integer authorId);
}
