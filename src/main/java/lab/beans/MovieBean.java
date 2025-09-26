package lab.beans;

import lab.data.Coordinates;
import lab.data.Movie;
import lab.data.Person;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.time.LocalDate;
import java.util.List;

@ManagedBean(name = "movieBean")
@SessionScoped
public class MovieBean {
    @EJB
    private DatabaseManager databaseManager;

    private Movie movie = new Movie();
    private List<Movie> movieList;

    // списки для выпадающих списков
    private List<Person> personList;
    private List<Coordinates> coordinatesList;

    // выбранные значения
    private Person selectedDirector;
    private Person selectedScreenwriter;
    private Person selectedOperator;
    private Coordinates selectedCoordinates;

    public String add() {
        movie.setDirector(selectedDirector);
        movie.setScreenwriter(selectedScreenwriter);
        movie.setOperator(selectedOperator);
        movie.setCoordinates(selectedCoordinates);
        databaseManager.saveMovie(movie);

        // сброс
        movie = new Movie();
        selectedDirector = null;
        selectedScreenwriter = null;
        selectedOperator = null;
        selectedCoordinates = null;

        movieList = databaseManager.getAllMovies(); // обновляем список
        return "index.xhtml?faces-redirect=true";
    }

    public List<Movie> getMovieList() {
        if (movieList == null) {
            movieList = databaseManager.getAllMovies();
        }
        return movieList;
    }

    public List<Person> getPersonList() {
        if (personList == null) {
            personList = databaseManager.getAllPersons();
        }
        return personList;
    }

    public List<Coordinates> getCoordinatesList() {
        if (coordinatesList == null) {
            coordinatesList = databaseManager.getAllCoordinates();
        }
        return coordinatesList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getSelectedDirector() {
        return selectedDirector;
    }

    public void setSelectedDirector(Person selectedDirector) {
        this.selectedDirector = selectedDirector;
    }

    public Person getSelectedScreenwriter() {
        return selectedScreenwriter;
    }

    public void setSelectedScreenwriter(Person selectedScreenwriter) {
        this.selectedScreenwriter = selectedScreenwriter;
    }

    public Person getSelectedOperator() {
        return selectedOperator;
    }

    public void setSelectedOperator(Person selectedOperator) {
        this.selectedOperator = selectedOperator;
    }

    public Coordinates getSelectedCoordinates() {
        return selectedCoordinates;
    }

    public void setSelectedCoordinates(Coordinates selectedCoordinates) {
        this.selectedCoordinates = selectedCoordinates;
    }
}
