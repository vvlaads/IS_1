package lab.beans.profile;

import lab.data.Coordinates;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "coordinatesProfileBean")
@SessionScoped
public class CoordinatesProfileBean {
    @EJB
    private DatabaseManager databaseManager;

    private int id;
    private Coordinates coordinates;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        coordinates = databaseManager.getCoordinatesById(id);
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
