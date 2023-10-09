package com.divide.experience.article.converters;

import com.divide.experience.article.objects.domain.Model;
import com.divide.experience.article.objects.transport.Item;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.Objects;
import javax.annotation.Resource;

/**
 * Created by AOleynikov on 29.05.2019.
 *
 *<p>For simple conversation current library (model mapper) easy solving,
 * but for other things you should add custom mapper or write converters.
 */
public abstract class AbstractMapper<M extends Model, I extends Item> implements Mapper<M, I> {

    private ModelMapper mapper;

    private Class<M> modelClass;
    private Class<I> itemClass;


    AbstractMapper(Class<M> modelClass, Class<I> itemClass) {
        this.modelClass = modelClass;
        this.itemClass = itemClass;
    }

    @Override
    public M toModel(I item) {
        return Objects.isNull(item)
                ? null
                : mapper.map(item, modelClass);
    }

    @Override
    public I toItem(M model) {
        return Objects.isNull(model)
                ? null
                : mapper.map(model, itemClass);
    }

    Converter<M, I> toItemConverter() {
        return context -> {
            M source = context.getSource();
            I destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<I, M> toModelConverter() {
        return context -> {
            I source = context.getSource();
            M destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(M source, I destination) {
    }

    void mapSpecificFields(I source, M destination) {
    }

    @Resource
    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
}
