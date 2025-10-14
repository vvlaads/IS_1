package lab.beans.profiles;

import lab.data.Location;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "locationProfileBean")
@SessionScoped
public class LocationProfileBean {
    @EJB
    private DatabaseManager databaseManager;

    private int id;
    private Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        location = databaseManager.getLocationById(id);
    }

    public Location getLocation() {
        return location;
    }
}
