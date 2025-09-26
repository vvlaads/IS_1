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

    private Person person = new Person();
    private List<Person> personList;
    private List<Location> locationList;

    private Location selectedLocation;

    public String add() {
        person.setLocation(selectedLocation);
        databaseManager.savePerson(person);
        person = new Person(); // сброс формы
        return "index.xhtml?faces-redirect=true";
    }

    public String goToAddForm() {
        person = new Person();
        return "addPerson.xhtml";
    }

    public void selectLocation(Location loc) {
        selectedLocation = loc;
    }

    public List<Color> getColors() {
        return Arrays.asList(Color.values());
    }

    public List<Person> getPersonList() {
        if (personList == null) {
            personList = databaseManager.getAllPersons();
        }
        return personList;
    }

    public List<Location> getLocationList() {
        if (locationList == null) {
            locationList = databaseManager.getAllLocations();
        }
        return locationList;
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
