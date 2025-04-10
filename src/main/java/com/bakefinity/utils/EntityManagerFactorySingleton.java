package com.bakefinity.utils;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactorySingleton {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("main_unit");

    private EntityManagerFactorySingleton() {}

    public static EntityManagerFactory getInstance() {
        return entityManagerFactory;
    }
}