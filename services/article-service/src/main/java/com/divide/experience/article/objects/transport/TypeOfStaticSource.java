package com.divide.experience.article.objects.transport;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public enum TypeOfStaticSource {

    IMAGE("IMAGE"),
    MUSIC("MUSIC"),
    VIDEO("VIDEO");


    String typeName;

    TypeOfStaticSource(String typeName) {
        this.typeName = typeName;
    }
}
