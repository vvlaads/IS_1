package lab.beans.util;

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

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        movieBean = context.getApplication()
                .evaluateExpressionGet(context, "#{movieFilterBean}", MovieBean.class);
        personBean = context.getApplication()
                .evaluateExpressionGet(context, "#{personBean}", PersonBean.class);
        locationBean = context.getApplication()
                .evaluateExpressionGet(context, "#{locationBean}", LocationBean.class);
        coordinatesBean = context.getApplication()
                .evaluateExpressionGet(context, "#{coordinatesBean}", CoordinatesBean.class);
    }

    public void checkAllUpdates() {
        movieBean.checkForUpdates();
        personBean.checkForUpdates();
        locationBean.checkForUpdates();
        coordinatesBean.checkForUpdates();
    }
}
