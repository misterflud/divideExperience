package com.divide.experience.article.objects.domain;

import java.util.stream.Stream;
import javax.persistence.AttributeConverter;

/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
public class RoleEnumConverter implements AttributeConverter<Role, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Role role) {
        if (role != null) {
            return role.getCode();
        }
        return null;
    }

    @Override
    public Role convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Stream.of(Role.values())
                .filter(c -> code.equals(c.getCode()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
