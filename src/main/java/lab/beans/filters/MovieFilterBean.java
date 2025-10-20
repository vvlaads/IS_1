package lab.beans.filters;

import lab.beans.data.MovieDataBean;
import lab.data.Movie;
import lab.database.DatabaseManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "movieFilterBean")
@SessionScoped
public class MovieFilterBean {
    @EJB
    private DatabaseManager databaseManager;

    private MovieDataBean movieDataBean;
    private List<Movie> filteredMovieList;
    private String nameFilter;
    private String mpaaRatingFilter;
    private String genreFilter;
    private String sortByColumn;
    private List<String> sortColumns = Arrays.asList("Нет", "По названию", "По рейтингу MPAA", "По жанру");
    private long lastKnownVersion = -1; // Последняя известная версия данных

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        movieDataBean = context.getApplication()
                .evaluateExpressionGet(context, "#{movieDataBean}", MovieDataBean.class);
        lastKnownVersion = movieDataBean.getVersion();
        applyFiltersAndSort();
    }

    // Этот метод будет вызываться периодически через polling
    public void checkForUpdates() {
        long currentVersion = movieDataBean.getVersion();
        if (currentVersion != lastKnownVersion) {
            // Данные изменились, обновляем
            lastKnownVersion = currentVersion;
            applyFiltersAndSort();
        }
    }

    public void update() {
        applyFiltersAndSort();
    }

    public void deleteMovie(int id) {
        movieDataBean.deleteMovie(id);
        lastKnownVersion = movieDataBean.getVersion();
        applyFiltersAndSort();
    }

    public void applyFiltersAndSort() {
        applyFilters();
        applySort();
    }

    private void applyFilters() {
        List<Movie> allMovies = movieDataBean.getMovieList();

        filteredMovieList = allMovies.stream()
                .filter(m -> nameFilter == null || nameFilter.isEmpty() ||
                        m.getName().toLowerCase().contains(nameFilter.toLowerCase()))
                .filter(m -> mpaaRatingFilter == null || mpaaRatingFilter.isEmpty() ||
                        (m.getMpaaRating() != null && m.getMpaaRating().name().equalsIgnoreCase(mpaaRatingFilter)))
                .filter(m -> genreFilter == null || genreFilter.isEmpty() ||
                        (m.getGenre() != null && m.getGenre().name().equalsIgnoreCase(genreFilter)))
                .collect(Collectors.toList());
    }

    public void removeFilters() {
        nameFilter = null;
        mpaaRatingFilter = null;
        genreFilter = null;
        applyFiltersAndSort();
    }

    private void applySort() {
        if (sortByColumn == null || sortByColumn.equals("Нет")) {
            filteredMovieList = filteredMovieList.stream()
                    .sorted(Comparator.comparing(Movie::getId))
                    .collect(Collectors.toList());
            return;
        }

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