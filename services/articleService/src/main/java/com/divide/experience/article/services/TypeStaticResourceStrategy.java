package com.divide.experience.article.services;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public enum TypeStaticResourceStrategy {

    SERVER("serverStaticResourceStrategy"),
    CLOUD("cloudStaticResourceStrategy");

    private String beanName;

    TypeStaticResourceStrategy(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
