package com.divide.experience.auth.facades.impl;

import com.divide.experience.auth.converters.NewUserMapper;
import com.divide.experience.auth.facades.RegistrationFacade;
import com.divide.experience.auth.objects.transport.NewUserItem;
import com.divide.experience.auth.services.RegistrationService;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

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
