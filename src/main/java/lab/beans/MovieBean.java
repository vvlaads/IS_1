package lab.beans;

import lab.data.Movie;
import lab.data.Person;
import lab.database.DatabaseManager;
import lab.enums.MovieGenre;
import lab.enums.MpaaRating;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "movieBean")
@SessionScoped
public class MovieBean {
    @EJB
    private DatabaseManager databaseManager;

    private List<Movie> movieList;
    private List<Movie> filteredMovieList;
    private Movie movie = new Movie();

    private Integer selectedDirectorId;
    private Integer selectedScreenwriterId;
    private Integer selectedOperatorId;
    private Integer selectedCoordinatesId;

    private String nameFilter;
    private String mpaaRatingFilter;
    private String genreFilter;
    private String sortByColumn;
    private List<String> sortColumns = Arrays.asList("Нет", "По названию", "По рейтингу MPAA", "По жанру");

    @PostConstruct
    public void init() {
        movieList = databaseManager.getMovieList();
        filteredMovieList = movieList;
    }

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
        movieList = databaseManager.getMovieList();
        filteredMovieList = movieList;
        System.out.println("Cleaned Movie Form");
    }

    public void applyFilters() {
        filteredMovieList = movieList.stream()
                .filter(m -> nameFilter == null || nameFilter.isEmpty() || m.getName().equals(nameFilter))
                .filter(m -> mpaaRatingFilter == null || mpaaRatingFilter.isEmpty() ||
                        (m.getMpaaRating() != null && m.getMpaaRating().name().equalsIgnoreCase(mpaaRatingFilter)))
                .filter(m -> genreFilter == null || genreFilter.isEmpty() ||
                        (m.getGenre() != null && m.getGenre().name().equalsIgnoreCase(genreFilter)))
                .collect(Collectors.toList());
    }

    public void removeFilters() {
        filteredMovieList = movieList;
    }

    public void applySort() {
        switch (sortByColumn) {
            case "По названию":
                filteredMovieList = filteredMovieList.stream()
                        .sorted(Comparator.comparing(Movie::getName))
                        .collect(Collectors.toList());
                break;
            case "По рейтингу MPAA":
                filteredMovieList = filteredMovieList.stream()
                        .sorted(Comparator.comparing(Movie::getMpaaRating, Comparator.nullsLast(Comparator.naturalOrder())))
                        .collect(Collectors.toList());
                break;
            case "По жанру":
                filteredMovieList = filteredMovieList.stream()
                        .sorted(Comparator.comparing(Movie::getGenre, Comparator.nullsLast(Comparator.naturalOrder())))
                        .collect(Collectors.toList());
                break;
            default:
                filteredMovieList = filteredMovieList.stream()
                        .sorted(Comparator.comparing(Movie::getId))
                        .collect(Collectors.toList());
                break;
        }
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

    public List<Movie> getFilteredMovieList() {
        return filteredMovieList;
    }

    public void setFilteredMovieList(List<Movie> filteredMovieList) {
        this.filteredMovieList = filteredMovieList;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getMpaaRatingFilter() {
        return mpaaRatingFilter;
    }

    public void setMpaaRatingFilter(String mpaaRatingFilter) {
        this.mpaaRatingFilter = mpaaRatingFilter;
    }

    public String getGenreFilter() {
        return genreFilter;
    }

    public void setGenreFilter(String genreFilter) {
        this.genreFilter = genreFilter;
    }

    public String getSortByColumn() {
        return sortByColumn;
    }

    public void setSortByColumn(String sortByColumn) {
        this.sortByColumn = sortByColumn;
    }

    public List<String> getSortColumns() {
        return sortColumns;
    }

    public void setSortColumns(List<String> sortColumns) {
        this.sortColumns = sortColumns;
    }
}
