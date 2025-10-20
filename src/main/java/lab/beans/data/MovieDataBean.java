package lab.beans.data;

import lab.data.Movie;
import lab.database.DatabaseManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ManagedBean(name = "movieDataBean")
@ApplicationScoped
public class MovieDataBean {
    @EJB
    private DatabaseManager databaseManager;
    private List<Movie> movieList;
    private AtomicLong version = new AtomicLong(0); // Версия данных

    @PostConstruct
    public void init() {
        loadMovies();
    }

    public void loadMovies() {
        movieList = databaseManager.getMovieList();
        version.incrementAndGet(); // Увеличиваем версию при изменении
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public long getVersion() {
        return version.get();
    }

    public void deleteMovie(int id) {
        databaseManager.deleteMovie(id);
        loadMovies(); // Обновляем данные и увеличиваем версию
    }
}