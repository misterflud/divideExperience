package com.divide.experience.lib.security.configuration.user;

import com.divide.experience.lib.security.configuration.SecurityHelper;
import org.springframework.stereotype.Service;


/**
 * @author Anton Oleynikov {@literal <yurolejniko@yandex.ru>}
 */
@Service
public class UserDetailsServiceHelper {

    /**
     * Получить данные о текущем пользователе.
     * Можно использовать фейн для получения данных о пользователе. Это дорогая операция,
     * поэтому нужно информацию о пользователе передавать в jwt токене, и брать оттуда же.
     *
     * @return данные пользователя
     */
    public UserDetails getUser() {
        UserDetails userDetails = null;
        if (SecurityHelper.userDetails().isPresent()) {
            // Если данные изменены через REST-запрос
            return SecurityHelper.userDetails().get();
        }
        return userDetails;
    }

}
