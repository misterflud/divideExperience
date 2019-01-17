package com.divideExperience.article.facades;

import com.divideExperience.article.dataTransportObjects.AuthorItem;

/**
 * Created by AOleynikov on 17.01.2019.
 */
public interface AuthorFacade {
    void addAuthor(AuthorItem authorItem);
    boolean deleteAuthor(AuthorItem authorItem);
    AuthorItem getAuthor(Long authorId);
}
