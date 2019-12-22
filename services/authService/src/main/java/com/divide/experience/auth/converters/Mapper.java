package com.divide.experience.auth.converters;

import com.divide.experience.auth.objects.domain.Model;
import com.divide.experience.auth.objects.transport.Item;

/**
 * Created by AOleynikov on 29.05.2019.
 */
public interface Mapper <M extends Model, I extends Item> {
    M toModel(I dto);

    I toItem(M entity);
}
