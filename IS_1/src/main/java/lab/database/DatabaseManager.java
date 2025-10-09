package lab.database;

import lab.util.Validator;
import lab.data.Coordinates;
import lab.data.Location;
import lab.data.Movie;
import lab.data.Person;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DatabaseManager {
    @PersistenceContext(unitName = "PersistenceUnit")
    private EntityManager em;

    private final Validator validator = new Validator();

    public void addMovie(Movie movie) {
        if (validator.validateMovie(movie)) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(movie);
                transaction.commit();
                System.out.println("Movie saved successfully: " + movie);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Movie saved ERROR: " + e);
            }
        } else {
            System.err.println("Illegal arguments for Movie: " + movie);
        }
    }

    public void addPerson(Person person) {
        if (validator.validatePerson(person)) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(person);
                transaction.commit();
                System.out.println("Person saved successfully: " + person);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Person saved ERROR: " + e);
            }
        } else {
            System.err.println("Illegal arguments for Person: " + person);
        }
    }

    public void addLocation(Location location) {
        if (validator.validateLocation(location)) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(location);
                transaction.commit();
                System.out.println("Location saved successfully: " + location);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Location saved ERROR: " + e);
            }
        } else {
            System.err.println("Illegal arguments for Location: " + location);
        }
    }

    public void addCoordinates(Coordinates coordinates) {
        if (validator.validateCoordinates(coordinates)) {
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(coordinates);
                transaction.commit();
                System.out.println("Coordinates saved successfully: " + coordinates);
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                System.err.println("Coordinates saved ERROR: " + e);
            }
        } else {
            System.err.println("Illegal arguments for Coordinates: " + coordinates);
        }
    }

    public List<Movie> getMovieList() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    public List<Person> getPersonList() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    public List<Location> getLocationList() {
        return em.createQuery("SELECT l FROM Location l", Location.class).getResultList();
    }

    public List<Coordinates> getCoordinatesList() {
        return em.createQuery("SELECT c FROM Coordinates c", Coordinates.class).getResultList();
    }

    public Movie getMovieById(int id) {
        try {
            Movie movie = em.find(Movie.class, id);
            if (movie == null) {
                System.err.println("Movie not found for id: " + id);
            }
            return movie;
        } catch (Exception e) {
            System.err.println("Error while fetching Movie by id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    public Person getPersonById(int id) {
        try {
            Person person = em.find(Person.class, id);
            if (person == null) {
                System.err.println("Person not found for id: " + id);
            }
            return person;
        } catch (Exception e) {
            System.err.println("Error while fetching Person by id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    public Location getLocationById(int id) {
        try {
            Location location = em.find(Location.class, id);
            if (location == null) {
                System.err.println("Location not found for id: " + id);
            }
            return location;
        } catch (Exception e) {
            System.err.println("Error while fetching Location by id: " + id);
            e.printStackTrace();
            return null;
        }
    }

    public Coordinates getCoordinatesById(int id) {
        try {
            Coordinates coordinates = em.find(Coordinates.class, id);
            if (coordinates == null) {
                System.err.println("Coordinates not found for id: " + id);
            }
            return coordinates;
        } catch (Exception e) {
            System.err.println("Error while fetching Coordinates by id: " + id);
            e.printStackTrace();
            return null;
        }
    }
}
