package lab.beans;

import lab.data.Person;
import lab.database.DatabaseManager;
import lab.data.enums.Color;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;
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
    private String sortByColumn;
    private final List<String> sortColumns = Arrays.asList("Нет", "По имени", "По цвету глаз", "По цвету волос");

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
        switch (sortByColumn) {
            case "По имени":
                filteredPersonList = filteredPersonList.stream()
                        .sorted(Comparator.comparing(Person::getName))
                        .collect(Collectors.toList());
                break;
            case "По цвету глаз":
                filteredPersonList = filteredPersonList.stream()
                        .sorted(Comparator.comparing(Person::getEyeColor))
                        .collect(Collectors.toList());
                break;
            case "По цвету волос":
                filteredPersonList = filteredPersonList.stream()
                        .sorted(Comparator.comparing(Person::getHairColor))
                        .collect(Collectors.toList());
                break;
            default:
                filteredPersonList = filteredPersonList.stream()
                        .sorted(Comparator.comparing(Person::getId))
                        .collect(Collectors.toList());
                break;
        }
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

    public String getSortByColumn() {
        return sortByColumn;
    }

    public void setSortByColumn(String sortByColumn) {
        this.sortByColumn = sortByColumn;
    }

    public List<String> getSortColumns() {
        return sortColumns;
    }
}
