package lab.beans;

import lab.data.Coordinates;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "coordinatesBean")
@SessionScoped
public class CoordinatesBean {
    @EJB
    private DatabaseManager databaseManager;
    private Coordinates coordinates = new Coordinates();
    private List<Coordinates> coordinatesList;

    public String add() {
        databaseManager.saveCoordinates(coordinates);
        coordinates = new Coordinates();
        coordinatesList = null;
        return "index.xhtml";
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Coordinates> getCoordinatesList() {
        if (coordinatesList == null) {
            coordinatesList = databaseManager.getAllCoordinates();
        }
        return coordinatesList;
    }
}
