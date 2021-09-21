package com.test.service;

import com.test.entity.Fruit${no};

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FruitService${no} {

    @Inject
    EntityManager entityManager;

    public void save(Fruit${no} fruit) {
        if (fruit.getId() == null) {
            entityManager.persist(fruit);
        } else {
            entityManager.merge(fruit);
        }
    }

    public void delete(Fruit${no} fruit) {
        entityManager.remove(fruit);
    }

    public List<Fruit${no}> list() {
        return entityManager.createQuery("from Fruit${no}").getResultList();
    }

    public Fruit${no} find(int id) {
        return entityManager.find(Fruit${no}.class, id);
    }
}
