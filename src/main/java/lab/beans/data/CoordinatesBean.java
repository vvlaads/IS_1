package lab.beans.data;

import lab.beans.util.Updatable;
import lab.beans.util.UpdateBean;
import lab.data.Coordinates;
import lab.database.DatabaseManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "coordinatesBean")
@SessionScoped
public class CoordinatesBean implements Updatable {
    @EJB
    private DatabaseManager databaseManager;
    private List<Coordinates> coordinatesList;

    private UpdateBean updateBean;
    private long lastKnownVersion = -1;


    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        updateBean = context.getApplication()
                .evaluateExpressionGet(context, "#{updateBean}", UpdateBean.class);
        lastKnownVersion = updateBean.getVersion();
        updateTable();
    }

    public void checkForUpdates() {
        long currentVersion = updateBean.getVersion();
        if (currentVersion != lastKnownVersion) {
            lastKnownVersion = currentVersion;
            updateTable();
        }
    }

    public void updateTable() {
        coordinatesList = databaseManager.getCoordinatesList();
    }

    public void deleteCoordinates(int id) {
        databaseManager.deleteCoordinates(id);
        updateTable();
    }

    public List<Coordinates> getCoordinatesList() {
        if (coordinatesList == null) {
            coordinatesList = databaseManager.getCoordinatesList();
        }
        return coordinatesList;
    }
}
