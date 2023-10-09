package com.divide.experience.article.facades.impl;

import com.divide.experience.article.converters.NewUserMapper;
import com.divide.experience.article.facades.RegistrationFacade;
import com.divide.experience.article.objects.transport.NewUserItem;
import com.divide.experience.article.services.RegistrationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by AOleynikov on 27.05.2019.
 */
@Component
public class RegistrationFacadeImpl implements RegistrationFacade {

    private RegistrationService registrationService;

    private NewUserMapper mapper;

    @Override
    public void registrationUser(NewUserItem newUserItem) {
        registrationService.registrationUser(mapper.toModel(newUserItem), newUserItem.getPassword());
    }

    @Resource
    public void setRegistrationService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Resource
    public void setMapper(NewUserMapper mapper) {
        this.mapper = mapper;
    }
}
