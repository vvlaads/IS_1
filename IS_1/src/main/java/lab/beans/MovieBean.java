package lab.beans;

import lab.data.Movie;
import lab.database.DatabaseManager;
import lab.enums.MovieGenre;
import lab.enums.MpaaRating;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "movieBean")
@SessionScoped
public class MovieBean {
    @EJB
    private DatabaseManager databaseManager;

    private List<Movie> movieList;
    private Movie movie = new Movie();

    private Integer selectedDirectorId;
    private Integer selectedScreenwriterId;
    private Integer selectedOperatorId;
    private Integer selectedCoordinatesId;

    public String addMovie() {
        movie.setDirector(databaseManager.getPersonById(selectedDirectorId));
        movie.setScreenwriter(databaseManager.getPersonById(selectedScreenwriterId));
        movie.setOperator(databaseManager.getPersonById(selectedOperatorId));
        movie.setCoordinates(databaseManager.getCoordinatesById(selectedCoordinatesId));
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
        selectedDirectorId = null;
        selectedScreenwriterId = null;
        selectedOperatorId = null;
        selectedCoordinatesId = null;
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

    public List<MpaaRating> getMpaaRatingList() {
        return Arrays.asList(MpaaRating.values());
    }

    public List<MovieGenre> getMovieGenreList() {
        return Arrays.asList(MovieGenre.values());
    }

    public Integer getSelectedDirectorId() {
        return selectedDirectorId;
    }

    public void setSelectedDirectorId(Integer selectedDirectorId) {
        this.selectedDirectorId = selectedDirectorId;
    }

    public Integer getSelectedScreenwriterId() {
        return selectedScreenwriterId;
    }

    public void setSelectedScreenwriterId(Integer selectedScreenwriterId) {
        this.selectedScreenwriterId = selectedScreenwriterId;
    }

    public Integer getSelectedOperatorId() {
        return selectedOperatorId;
    }

    public void setSelectedOperatorId(Integer selectedOperatorId) {
        this.selectedOperatorId = selectedOperatorId;
    }

    public Integer getSelectedCoordinatesId() {
        return selectedCoordinatesId;
    }

    public void setSelectedCoordinatesId(Integer selectedCoordinatesId) {
        this.selectedCoordinatesId = selectedCoordinatesId;
    }
}
