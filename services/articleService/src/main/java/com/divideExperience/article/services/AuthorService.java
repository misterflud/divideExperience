package com.divideExperience.article.services;

import com.divideExperience.article.domainObjects.AuthorModel;

/**
 * Created by AOleynikov on 05.01.2019.
 */
public interface AuthorService {
    AuthorModel getAuthor(Long authorId);
    void addAuthor(AuthorModel authorModel);
}
