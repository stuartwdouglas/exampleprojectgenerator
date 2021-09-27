package com.test.service;

import com.test.entity.Fruit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FruitService {

    @Inject
    EntityManager entityManager;

    public void save(Fruit fruit) {
        if (fruit.getId() == null) {
            entityManager.persist(fruit);
        } else {
            entityManager.merge(fruit);
        }
    }

    public void delete(Fruit fruit) {
        entityManager.remove(fruit);
    }

    @SuppressWarnings("unchecked")
    public List<Fruit> list() {
        return entityManager.createQuery("from Fruit").getResultList();
    }

    public Fruit find(int id) {
        return entityManager.find(Fruit.class, id);
    }
}
