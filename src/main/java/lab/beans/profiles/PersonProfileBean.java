package lab.beans.profiles;

import lab.data.Person;
import lab.database.DatabaseManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "personProfileBean")
@SessionScoped
public class PersonProfileBean {
    @EJB
    private DatabaseManager databaseManager;

    private int id;
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        person = databaseManager.getPersonById(id);
    }

    public Person getPerson() {
        return person;
    }
}
