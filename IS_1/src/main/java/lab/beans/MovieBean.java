package lab.beans;

import lab.data.Coordinates;
import lab.data.Movie;
import lab.data.Person;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "movieBean")
@SessionScoped
public class MovieBean {
    @EJB
    private DatabaseManager databaseManager;

    private List<Movie> movieList;
    private Movie movie = new Movie();

    private Person selectedDirector;
    private Person selectedScreenwriter;
    private Person selectedOperator;
    private Coordinates selectedCoordinates;

    public String addMovie() {
        movie.setDirector(selectedDirector);
        movie.setScreenwriter(selectedScreenwriter);
        movie.setOperator(selectedOperator);
        movie.setCoordinates(selectedCoordinates);
        databaseManager.addMovie(movie);
        cleanData();
        return "index.xhtml";
    }

    public void editMovie() {
    }

    public void deleteMovie() {
    }

    private void cleanData() {
        movie = new Movie();
        selectedDirector = null;
        selectedScreenwriter = null;
        selectedOperator = null;
        selectedCoordinates = null;
        movieList = null;
        System.out.println("Cleaned Movie Form");
    }

    public List<Movie> getMovieList() {
        if (movieList == null) {
            movieList = databaseManager.getMovieList();
        }
        return movieList;
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
