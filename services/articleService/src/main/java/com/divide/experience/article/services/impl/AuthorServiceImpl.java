package com.divide.experience.article.services.impl;

import com.divide.experience.article.dao.services.impl.AuthorDaoImpl;
import com.divide.experience.article.objects.domain.AuthorModel;
import com.divide.experience.article.services.AuthorService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AOleynikov on 05.01.2019.
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDaoImpl authorDao;

    /**
     * Gets author.
     * @param authorId This is id of author.
     * @return Author.
     */
    @Override
    public AuthorModel getAuthor(Integer authorId) {
        return authorDao.getAuthor(authorId);
    }

    /**
     * Gets author.
     * @param email This is registration email of author.
     * @return Author.
     */
    @Override
    public AuthorModel getAuthorByEmail(String email) {
        return authorDao.getAuthorByEmail(email);
    }

    /**
     * Adds author.
     * @param authorModel This is new author.
     */
    @Override
    public void addAuthor(AuthorModel authorModel) {
        authorModel.setDateOfRegistration(new Date());
        authorDao.addAuthor(authorModel);
    }
}
