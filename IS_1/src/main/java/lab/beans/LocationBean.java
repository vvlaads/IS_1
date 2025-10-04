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

    private List<Location> locationList;
    private Location location = new Location();

    public String addLocation() {
        databaseManager.addLocation(location);
        cleanData();
        return "index.xhtml";
    }

    public void editLocation() {
    }

    public void deleteLocation() {
    }

    private void cleanData() {
        location = new Location();
        locationList = null;
        System.out.println("Cleaned Location Form");
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = databaseManager.getLocationList();
        }
        return locationList;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
