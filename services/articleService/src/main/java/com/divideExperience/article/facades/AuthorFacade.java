package com.divideExperience.article.facades;

import com.divideExperience.article.dataTransportObjects.AuthorItem;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by AOleynikov on 17.01.2019.
 */
public interface AuthorFacade {
    void addAuthor(AuthorItem authorItem);
    boolean deleteAuthor(AuthorItem authorItem);
    AuthorItem getAuthor(Integer authorId);
}
