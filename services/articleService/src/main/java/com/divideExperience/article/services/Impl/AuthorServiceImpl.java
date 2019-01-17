package com.divideExperience.article.services.Impl;

import com.divideExperience.article.daoServices.Impl.AuthorDaoImpl;
import com.divideExperience.article.domainObjects.AuthorModel;
import com.divideExperience.article.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorDaoImpl authorDao;

    @Override
    public AuthorModel getAuthor(Long authorId) {
        return authorDao.getAuthor(authorId);
    }

    @Override
    public void addAuthor(AuthorModel authorModel) {
        authorDao.addAuthor(authorModel);
    }
}
