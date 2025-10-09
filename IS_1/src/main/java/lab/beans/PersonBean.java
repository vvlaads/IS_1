package lab.beans;

import lab.data.Location;
import lab.data.Person;
import lab.database.DatabaseManager;
import lab.enums.Color;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "personBean")
@SessionScoped
public class PersonBean {
    @EJB
    private DatabaseManager databaseManager;

    private List<Person> personList;
    private Person person = new Person();
    private Integer selectedLocationId;

    public String addPerson() {
        person.setLocation(databaseManager.getLocationById(selectedLocationId));
        databaseManager.addPerson(person);
        cleanData();
        return "index.xhtml";
    }

    public void editPerson() {

    }

    public void deletePerson() {

    }

    private void cleanData() {
        person = new Person();
        personList = null;
        selectedLocationId = null;
        System.out.println("Cleaned Person Form");
    }

    public List<Color> getColorList() {
        return Arrays.asList(Color.values());
    }

    public List<Person> getPersonList() {
        if (personList == null) {
            personList = databaseManager.getPersonList();
        }
        return personList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getSelectedLocationId() {
        return selectedLocationId;
    }

    public void setSelectedLocationId(Integer selectedLocationId) {
        this.selectedLocationId = selectedLocationId;
    }
}
