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

    private List<Coordinates> coordinatesList;
    private Coordinates coordinates = new Coordinates();

    public String addCoordinates() {
        databaseManager.addCoordinates(coordinates);
        cleanData();
        return "index.xhtml";
    }

    public void editCoordinates() {
    }

    public void deleteCoordinates() {
    }

    private void cleanData() {
        coordinates = new Coordinates();
        coordinatesList = null;
        System.out.println("Cleaned Coordinates Form");
    }

    public List<Coordinates> getCoordinatesList() {
        if (coordinatesList == null) {
            coordinatesList = databaseManager.getCoordinatesList();
        }
        return coordinatesList;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
