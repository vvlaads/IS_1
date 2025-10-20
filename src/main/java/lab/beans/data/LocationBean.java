package lab.beans.data;

import lab.beans.util.Updatable;
import lab.beans.util.UpdateBean;
import lab.data.Location;
import lab.database.DatabaseManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name = "locationBean")
@SessionScoped
public class LocationBean implements Updatable {
    @EJB
    private DatabaseManager databaseManager;
    private List<Location> locationList;

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
        locationList = databaseManager.getLocationList();
    }

    public void deleteLocation(int id) {
        databaseManager.deleteLocation(id);
        updateTable();
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = databaseManager.getLocationList();
        }
        return locationList;
    }
}
