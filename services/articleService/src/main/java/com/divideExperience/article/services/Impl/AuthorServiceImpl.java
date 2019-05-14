package com.divideExperience.article.services.Impl;

import com.divideExperience.article.daoServices.Impl.AuthorDaoImpl;
import com.divideExperience.article.domainObjects.AuthorModel;
import com.divideExperience.article.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDaoImpl authorDao;

    @Override
    public AuthorModel getAuthor(Integer authorId) {
        return authorDao.getAuthor(authorId);
    }

    @Override
    public AuthorModel getAuthorByEmail(String email) {
        return authorDao.getAuthorByEmail(email);
    }

    @Override
    public void addAuthor(AuthorModel authorModel) {
        authorModel.setDateOfRegistration(new Date());
        authorDao.addAuthor(authorModel);
    }
}
