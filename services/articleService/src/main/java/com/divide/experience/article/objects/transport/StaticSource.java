package com.divide.experience.article.objects.transport;

import java.io.Serializable;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class StaticSource implements Serializable {

    private String name;
    private String uri;
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
