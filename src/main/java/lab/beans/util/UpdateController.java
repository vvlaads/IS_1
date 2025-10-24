package lab.beans.util;

import lab.beans.conflicts.CoordinatesConflicts;
import lab.beans.conflicts.LocationConflicts;
import lab.beans.conflicts.PersonConflicts;
import lab.beans.data.CoordinatesBean;
import lab.beans.data.LocationBean;
import lab.beans.data.MovieBean;
import lab.beans.data.PersonBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "updateController")
@RequestScoped
public class UpdateController {
    private MovieBean movieBean;

    private PersonBean personBean;

    private LocationBean locationBean;

    private CoordinatesBean coordinatesBean;
    private CoordinatesConflicts coordinatesConflicts;
    private LocationConflicts locationConflicts;
    private PersonConflicts personConflicts;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        movieBean = context.getApplication()
                .evaluateExpressionGet(context, "#{movieBean}", MovieBean.class);
        personBean = context.getApplication()
                .evaluateExpressionGet(context, "#{personBean}", PersonBean.class);
        locationBean = context.getApplication()
                .evaluateExpressionGet(context, "#{locationBean}", LocationBean.class);
        coordinatesBean = context.getApplication()
                .evaluateExpressionGet(context, "#{coordinatesBean}", CoordinatesBean.class);
        coordinatesConflicts = context.getApplication()
                .evaluateExpressionGet(context, "#{coordinatesConflicts}", CoordinatesConflicts.class);
        locationConflicts = context.getApplication()
                .evaluateExpressionGet(context, "#{locationConflicts}", LocationConflicts.class);
        personConflicts = context.getApplication()
                .evaluateExpressionGet(context, "#{personConflicts}", PersonConflicts.class);
    }

    public void checkAllUpdates() {
        movieBean.checkForUpdates();
        personBean.checkForUpdates();
        locationBean.checkForUpdates();
        coordinatesBean.checkForUpdates();

        coordinatesConflicts.checkForUpdates();
        locationConflicts.checkForUpdates();
        personConflicts.checkForUpdates();
    }
}
