package lab.beans.profile;

import lab.data.Movie;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "movieProfileBean")
@SessionScoped
public class MovieProfileBean {
    @EJB
    private DatabaseManager databaseManager;

    private Movie movie;
    private int id;

    public Movie getMovie() {
        return movie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        movie = databaseManager.getMovieById(id);
    }
}
