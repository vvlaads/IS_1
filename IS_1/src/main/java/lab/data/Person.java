package lab.data;

import lab.enums.Color;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 1)
    @Column(nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Column(name = "EYE_COLOR", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color eyeColor; //Поле не может быть null
    @Column(name = "HAIR_COLOR", nullable = false)
    @Enumerated(EnumType.STRING)
    private Color hairColor; //Поле не может быть null
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location; //Поле не может быть null
    @Column(name = "birthday")
    private java.time.LocalDate birthday; //Поле может быть null
    @Column(nullable = false)
    @Min(1)
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0
    @Column(name = "PASSPORT_ID")
    @Size(min = 4)
    private String passportID; //Длина строки должна быть не меньше 4, Строка не может быть пустой, Поле может быть null

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    @Override
    public String toString() {
        return "{\n" +
                "Name:" + name + "\n" +
                "EyeColor: " + eyeColor + "\n" +
                "HairColor: " + hairColor + "\n" +
                "Location:" + location + "\n" +
                "Birthday:" + birthday + "\n" +
                "Weight:" + weight + "\n" +
                "passportID: " + passportID + "\n" +
                "}";
    }
}
