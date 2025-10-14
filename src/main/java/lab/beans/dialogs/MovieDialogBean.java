package lab.beans.dialogs;

import lab.data.Movie;
import lab.data.enums.MovieGenre;
import lab.data.enums.MpaaRating;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "movieDialogBean")
@ViewScoped
public class MovieDialogBean {
    @EJB
    private DatabaseManager databaseManager;
    private Movie movie = new Movie();

    private Integer selectedDirectorId;
    private Integer selectedScreenwriterId;
    private Integer selectedOperatorId;
    private Integer selectedCoordinatesId;

    private boolean editing = false;

    public void openAddDialog() {
        movie = new Movie();
        editing = false;
    }

    public void openEditDialog(int id) {
        movie = databaseManager.getMovieById(id);
        selectedCoordinatesId = movie.getCoordinates() != null ? movie.getCoordinates().getId() : null;
        selectedDirectorId = movie.getDirector() != null ? movie.getDirector().getId() : null;
        selectedScreenwriterId = movie.getScreenwriter() != null ? movie.getScreenwriter().getId() : null;
        selectedOperatorId = movie.getOperator() != null ? movie.getOperator().getId() : null;
        editing = true;
    }

    public void addMovie() {
        movie.setDirector(databaseManager.getPersonById(selectedDirectorId));
        movie.setScreenwriter(databaseManager.getPersonById(selectedScreenwriterId));
        movie.setOperator(databaseManager.getPersonById(selectedOperatorId));
        movie.setCoordinates(databaseManager.getCoordinatesById(selectedCoordinatesId));
        databaseManager.addMovie(movie);
    }

    public void updateMovie() {
        movie.setDirector(databaseManager.getPersonById(selectedDirectorId));
        movie.setScreenwriter(databaseManager.getPersonById(selectedScreenwriterId));
        movie.setOperator(databaseManager.getPersonById(selectedOperatorId));
        movie.setCoordinates(databaseManager.getCoordinatesById(selectedCoordinatesId));
        databaseManager.updateMovie(movie);
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
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
