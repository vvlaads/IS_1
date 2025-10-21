package lab.beans.util;

import lab.data.Movie;
import lab.data.Person;
import lab.database.DatabaseManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "specialFunctionBean")
@RequestScoped
public class SpecialFunctionBean {

    @EJB
    private DatabaseManager databaseManager;

    private int goldenPalmCount;
    private String namePrefix;
    private int minGoldenPalm;
    private int minLength;
    private int oscarsToAdd;
    private UpdateBean updateBean;

    private List<Movie> resultMovies;
    private List<Person> resultOperators;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        updateBean = context.getApplication()
                .evaluateExpressionGet(context, "#{updateBean}", UpdateBean.class);
    }

    public void deleteByGoldenPalmCount() {
        boolean result = databaseManager.deleteMovieByGoldenPalmCount(goldenPalmCount);
        if (result) {
            updateBean.increaseVersion();
        }
    }

    public void findByNamePrefix() {
        resultMovies = databaseManager.findMoviesByNameStartingWith(namePrefix);
    }

    public void findByGoldenPalmGreaterThan() {
        resultMovies = databaseManager.findMoviesByGoldenPalmCountGreaterThan(minGoldenPalm);
    }

    public void findOperatorsWithoutOscars() {
        resultOperators = databaseManager.findOperatorsWithoutOscars();
    }

    public void rewardLongMovies() {
        databaseManager.rewardLongMovies(minLength, oscarsToAdd);
    }

    public int getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public void setGoldenPalmCount(int goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public int getMinGoldenPalm() {
        return minGoldenPalm;
    }

    public void setMinGoldenPalm(int minGoldenPalm) {
        this.minGoldenPalm = minGoldenPalm;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getOscarsToAdd() {
        return oscarsToAdd;
    }

    public void setOscarsToAdd(int oscarsToAdd) {
        this.oscarsToAdd = oscarsToAdd;
    }

    public List<Movie> getResultMovies() {
        return resultMovies;
    }

    public List<Person> getResultOperators() {
        return resultOperators;
    }
}
