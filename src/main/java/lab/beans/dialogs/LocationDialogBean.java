package lab.beans.dialogs;

import lab.data.Location;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "locationDialogBean")
@ViewScoped
public class LocationDialogBean {
    @EJB
    private DatabaseManager databaseManager;
    private Location location = new Location();

    private boolean editing = false;

    public void openAddDialog() {
        location = new Location();
        editing = false;
    }

    public void openEditDialog(int id) {
        location = databaseManager.getLocationById(id);
        editing = true;
    }

    public void addLocation() {
        databaseManager.addLocation(location);
    }

    public void updateLocation() {
        databaseManager.updateLocation(location);
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
}
