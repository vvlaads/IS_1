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
    private Location selectedLocation;

    public String addPerson() {
        person.setLocation(selectedLocation);
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
        selectedLocation = null;
        System.out.println("Cleaned Person Form");
    }

    public void selectLocation(Location loc) {
        selectedLocation = loc;
    }

    public List<Color> getColors() {
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

    public Location getSelectedLocation() {
        return selectedLocation;
    }

    public void setSelectedLocation(Location selectedLocation) {
        this.selectedLocation = selectedLocation;
    }
}
