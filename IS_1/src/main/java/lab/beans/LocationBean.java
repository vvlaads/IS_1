package lab.beans;

import lab.data.Location;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "locationBean")
@SessionScoped
public class LocationBean {
    @EJB
    private DatabaseManager databaseManager;
    private Location location = new Location();
    private List<Location> locationList;

    public String add() {
        databaseManager.saveLocation(location);
        location = new Location();
        locationList = null;
        return "index.xhtml";
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = databaseManager.getAllLocations();
        }
        return locationList;
    }

    public void updateList() {
        locationList = null;
    }
}
