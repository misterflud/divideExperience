package com.divide.experience.auth.converters;

import com.divide.experience.auth.objects.domain.Model;
import com.divide.experience.auth.objects.transport.Item;

/**
 * Created by AOleynikov on 29.05.2019.
 *
 *<p>Modelmapper library does all operations for converting.
 * @param <M> Domain model.
 * @param <I> Transport Object.
 */
public interface Mapper<M extends Model, I extends Item> {

    /**
     * Converts to model.
     *
     * @param dto transport object.
     * @return domain model.
     */
    M toModel(I dto);

    /**
     * Convert to item.
     *
     * @param entity domain model.
     * @return transport object.
     */
    I toItem(M entity);
}
