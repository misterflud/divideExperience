package com.divideExperiense.addStory.daoServices.Impl;

import com.divideExperiense.addStory.daoServices.AuthorDao;
import com.divideExperiense.addStory.domainObjects.AuthorModel;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 04.01.2019.
 */
@Repository("authorDao")
public class AuthorDaoImpl implements AuthorDao {

    private SessionFactory sessionFactory;
    @Override
    public void addAuthor(AuthorModel authorModel) {

    }

    @Override
    public void updateAuthor(AuthorModel authorModel) {

    }

    @Override
    public void getAuthor(String articleId) {

    }

    @Override
    public void deleteAuthor(String articleId) {

    }
}
