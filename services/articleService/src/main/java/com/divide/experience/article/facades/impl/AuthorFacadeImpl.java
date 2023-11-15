package com.divide.experience.article.facades.impl;

import com.divide.experience.article.facades.AuthorFacade;
import com.divide.experience.article.objects.transport.AuthorItem;
import org.springframework.stereotype.Component;

/**
 * Created by AOleynikov on 17.01.2019.
 */
@Component
public class AuthorFacadeImpl implements AuthorFacade {

    /**
     * Adds author.
     * @param authorItem This is dto of author.
     */
    @Override
    public void addAuthor(AuthorItem authorItem) {
        // TODO: add user
    }

    /**
     * Deletes author.
     * @param authorItem This is dto of author.
     * @return Result of deleting.
     */
    @Override
    public boolean deleteAuthor(AuthorItem authorItem) {
        return false;
    }

    /**
     * Gets author.
     * @param authorId This is id of author.
     * @return Author.
     */
    @Override
    public AuthorItem getAuthor(Integer authorId) {
        return null;
    }
}
