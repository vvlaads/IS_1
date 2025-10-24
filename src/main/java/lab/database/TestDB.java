package lab.database;

import lab.data.Coordinates;
import lab.util.Validator;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class TestDB {
    @PersistenceContext(unitName = "PersistenceUnit")
    private EntityManager em;

    @Transactional
    public void addCoordinates(Coordinates coordinates) {
        if (Validator.validateObject(coordinates)) {
            System.out.println("Before persist");
            em.persist(coordinates);
            System.out.println("After persist, before flush");
        } else {
            throw new IllegalArgumentException("Coordinates validation failed");
        }
    }
}