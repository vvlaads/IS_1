package lab.beans.dialogs;

import lab.data.Coordinates;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "coordinatesDialogBean")
@ViewScoped
public class CoordinatesDialogBean {
    @EJB
    private DatabaseManager databaseManager;
    private Coordinates coordinates = new Coordinates();

    private boolean editing = false;

    public void openAddDialog() {
        coordinates = new Coordinates();
        editing = false;
    }

    public void openEditDialog(int id) {
        coordinates = databaseManager.getCoordinatesById(id);
        editing = true;
    }

    public void addCoordinates() {
        databaseManager.addCoordinates(coordinates);
    }

    public void updateCoordinates() {
        databaseManager.updateCoordinates(coordinates);
    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isEditing() {
        return editing;
    }

    public void setEditing(boolean editing) {
        this.editing = editing;
    }
}
