package com.divide.experience.article.objects.transport;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@ApiModel(description = "The DTO of a static resource (contains url which uses for getting bytes array).")
public class StaticSource implements Serializable {

    private static final long serialVersionUID = -1130614799017398116L;

    @ApiModelProperty(value = "The name of the resource", example = "Cats.jpg")
    private String name;

    @ApiModelProperty(value = "The unique resource identifier", example = "/article/image/s/10/Cats.jpg")
    private String uri;

    @ApiModelProperty(value = "The type of resource")
    private TypeOfStaticSource type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public TypeOfStaticSource getType() {
        return type;
    }

    public void setType(TypeOfStaticSource type) {
        this.type = type;
    }
}
