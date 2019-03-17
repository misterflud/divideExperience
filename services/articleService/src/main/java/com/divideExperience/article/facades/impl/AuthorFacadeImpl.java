package com.divideExperience.article.facades.impl;

import com.divideExperience.article.converters.toModel.AuthorConverterToModel;
import com.divideExperience.article.dataTransportObjects.AuthorItem;
import com.divideExperience.article.facades.AuthorFacade;
import com.divideExperience.article.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 17.01.2019.
 */
@Component
public class AuthorFacadeImpl implements AuthorFacade {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorConverterToModel authorConverterToModel;

    @Override
    public void addAuthor(AuthorItem authorItem) {
        authorService.addAuthor(authorConverterToModel.convert(authorItem));
    }

    @Override
    public boolean deleteAuthor(AuthorItem authorItem) {
        return false;
    }

    @Override
    public AuthorItem getAuthor(Integer id) {
        return null;
    }
}
