package lab.beans;

import lab.data.Person;
import lab.database.DatabaseManager;
import lab.enums.Color;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "personBean")
@SessionScoped
public class PersonBean {
    @EJB
    private DatabaseManager databaseManager;

    private List<Person> personList;
    private List<Person> filteredPersonList;
    private Person person = new Person();
    private Integer selectedLocationId;
    private String nameFilter;
    private String eyeColorFilter;
    private String hairColorFilter;
    private String passportIDFilter;

    @PostConstruct
    public void init() {
        personList = databaseManager.getPersonList();
        filteredPersonList = personList;
    }

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
        selectedLocationId = null;
        personList = databaseManager.getPersonList();
        filteredPersonList = personList;
        System.out.println("Cleaned Person Form");
    }

    public void applyFilters() {
        filteredPersonList = personList.stream()
                .filter(p -> nameFilter == null || nameFilter.isEmpty() || p.getName().equals(nameFilter))
                .filter(p -> eyeColorFilter == null || eyeColorFilter.isEmpty() || p.getEyeColor().name().equalsIgnoreCase(eyeColorFilter))
                .filter(p -> hairColorFilter == null || hairColorFilter.isEmpty() || p.getHairColor().name().equalsIgnoreCase(hairColorFilter))
                .filter(p -> passportIDFilter == null || passportIDFilter.isEmpty() || p.getPassportID().equals(passportIDFilter))
                .collect(Collectors.toList());
    }

    public void removeFilters() {
        filteredPersonList = personList;
    }

    public void applySort() {
        filteredPersonList = personList.stream()
                .sorted((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()))
                .collect(Collectors.toList());
    }

    public void removeSort() {
        filteredPersonList = personList.stream()
                .sorted(Comparator.comparing(Person::getId))
                .collect(Collectors.toList());
    }

    public List<Color> getColorList() {
        return Arrays.asList(Color.values());
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<Person> getFilteredPersonList() {
        return filteredPersonList;
    }

    public void setFilteredPersonList(List<Person> filteredPersonList) {
        this.filteredPersonList = filteredPersonList;
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

    public String getNameFilter() {
        return nameFilter;
    }

    public void setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public String getEyeColorFilter() {
        return eyeColorFilter;
    }

    public void setEyeColorFilter(String eyeColorFilter) {
        this.eyeColorFilter = eyeColorFilter;
    }

    public String getHairColorFilter() {
        return hairColorFilter;
    }

    public void setHairColorFilter(String hairColorFilter) {
        this.hairColorFilter = hairColorFilter;
    }


    public String getPassportIDFilter() {
        return passportIDFilter;
    }

    public void setPassportIDFilter(String passportIDFilter) {
        this.passportIDFilter = passportIDFilter;
    }
}
