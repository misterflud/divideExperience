package com.divide.experience.auth.dao.services.impl;

import com.divide.experience.auth.dao.services.UserDao;
import com.divide.experience.auth.objects.domain.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * Created by AOleynikov on 21.05.2019.
 */
@Component
public class UserDaoImpl implements UserDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public UserModel getUserByEmail(String email) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (UserModel) session.getNamedQuery("UserModel.findByEmail")
                    .setParameter("email", email).uniqueResult();
        }
    }

    @Override
    public UserModel getUserByNickName(String nickName) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (UserModel) session.getNamedQuery("UserModel.findByNickName")
                    .setParameter("nickName", nickName).uniqueResult();
        }
    }

    @Override
    public void addUser(UserModel userModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.saveOrUpdate(userModel);
        }
    }

    @Override
    public void updateUser(UserModel userModel) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            session.getTransaction().begin();
            session.update(userModel);
            session.getTransaction().commit();
        }
    }

    @Override
    public UserModel getUserByAuthToken(String authToken) {
        try (Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession()) {
            return (UserModel) session.getNamedQuery("UserModel.findByAuthToken")
                    .setParameter("authToken", authToken).uniqueResult();
        }
    }
}
