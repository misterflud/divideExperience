package com.divide.experience.article.converters.to.item;

import com.divide.experience.article.objects.domain.AuthorModel;
import com.divide.experience.article.objects.transport.AuthorItem;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Component
public class AuthorConverterToItem implements Converter<AuthorModel, AuthorItem> {
    @Override
    public AuthorItem convert(AuthorModel authorModel) {
        AuthorItem item = new AuthorItem();
        item.setEmail(authorModel.getEmail());

        return null;
    }
}
