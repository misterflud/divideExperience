package com.divideExperiense.addStory.daoServices;

import com.divideExperiense.addStory.domainObjects.AuthorModel;

/**
 * Created by AOleynikov on 04.01.2019.
 */
public interface AuthorDao {
    void addAuthor(AuthorModel authorModel);
    void updateAuthor(AuthorModel authorModel);
    void getAuthor(String articleId);
    void deleteAuthor(String articleId);
}
