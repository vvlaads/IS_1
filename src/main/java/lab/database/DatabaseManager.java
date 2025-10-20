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

    public void updateMovie(Movie movie) {
        if (!validator.validateMovie(movie)) {
            System.err.println("Illegal arguments for Movie: " + movie);
            return;
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Movie existingMovie = em.find(Movie.class, movie.getId());
            if (existingMovie == null) {
                System.err.println("Movie not found with id: " + movie.getId());
                transaction.rollback();
                return;
            }

            em.merge(movie);
            transaction.commit();
            System.out.println("Movie updated successfully: " + movie);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Movie update ERROR: " + e);
        }
    }

    public void deleteMovie(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Movie existingMovie = em.find(Movie.class, id);
            if (existingMovie == null) {
                System.err.println("Movie not found with id: " + id);
                transaction.rollback();
                return;
            }

            em.remove(existingMovie);
            transaction.commit();
            System.out.println("Movie deleted successfully with id: " + id);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Movie delete ERROR: " + e);
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

    public void updatePerson(Person person) {
        if (!validator.validatePerson(person)) {
            System.err.println("Illegal arguments for Person: " + person);
            return;
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Person existingPerson = em.find(Person.class, person.getId());
            if (existingPerson == null) {
                System.err.println("Person not found with id: " + person.getId());
                transaction.rollback();
                return;
            }

            em.merge(person);
            transaction.commit();
            System.out.println("Person updated successfully: " + person);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Person update ERROR: " + e);
        }
    }

    public void deletePerson(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Person existingPerson = em.find(Person.class, id);
            if (existingPerson == null) {
                System.err.println("Person not found with id: " + id);
                transaction.rollback();
                return;
            }

            em.remove(existingPerson);
            transaction.commit();
            System.out.println("Person deleted successfully with id: " + id);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Person delete ERROR: " + e);
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

    public void updateLocation(Location location) {
        if (!validator.validateLocation(location)) {
            System.err.println("Illegal arguments for Location: " + location);
            return;
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Location existingLocation = em.find(Location.class, location.getId());
            if (existingLocation == null) {
                System.err.println("Location not found with id: " + location.getId());
                transaction.rollback();
                return;
            }

            em.merge(location);
            transaction.commit();
            System.out.println("Location updated successfully: " + location);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Location update ERROR: " + e);
        }
    }

    public void deleteLocation(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Location existingLocation = em.find(Location.class, id);
            if (existingLocation == null) {
                System.err.println("Location not found with id: " + id);
                transaction.rollback();
                return;
            }

            em.remove(existingLocation);
            transaction.commit();
            System.out.println("Location deleted successfully with id: " + id);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Location delete ERROR: " + e);
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

    public void updateCoordinates(Coordinates coordinates) {
        if (!validator.validateCoordinates(coordinates)) {
            System.err.println("Illegal arguments for Coordinates: " + coordinates);
            return;
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Coordinates existingCoordinates = em.find(Coordinates.class, coordinates.getId());
            if (existingCoordinates == null) {
                System.err.println("Coordinates not found with id: " + coordinates.getId());
                transaction.rollback();
                return;
            }

            em.merge(coordinates);
            transaction.commit();
            System.out.println("Coordinates updated successfully: " + coordinates);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Coordinates update ERROR: " + e);
        }
    }

    public void deleteCoordinates(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Coordinates existingCoordinates = em.find(Coordinates.class, id);
            if (existingCoordinates == null) {
                System.err.println("Coordinates not found with id: " + id);
                transaction.rollback();
                return;
            }

            em.remove(existingCoordinates);
            transaction.commit();
            System.out.println("Coordinates deleted successfully with id: " + id);

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Coordinates delete ERROR: " + e);
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
