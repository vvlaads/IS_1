package lab.data;

import lab.enums.MovieGenre;
import lab.enums.MpaaRating;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Column(nullable = false)
    @Size(min = 1)
    private String name; //Поле не может быть null, Строка не может быть пустой
    @ManyToOne
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates; //Поле не может быть null
    @Column(name = "CREATION_DATE", nullable = false)
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Column(name = "OSCARS_COUNT", nullable = false)
    @Min(1)
    private Long oscarsCount; //Значение поля должно быть больше 0, Поле не может быть null
    @Column
    @Min(1)
    private Integer budget; //Значение поля должно быть больше 0, Поле может быть null
    @Column(name = "TOTAL_BOX_OFFICE")
    @DecimalMin(value = "0.0", inclusive = false)
    private float totalBoxOffice; //Значение поля должно быть больше 0
    @Column(name = "MPAA_RATING")
    @Enumerated(EnumType.STRING)
    private MpaaRating mpaaRating; //Поле может быть null
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Person director; //Поле может быть null
    @ManyToOne
    @JoinColumn(name = "screenwriter_id")
    private Person screenwriter;
    @ManyToOne
    @JoinColumn(name = "operator_id")
    private Person operator; //Поле может быть null
    @Column(nullable = false)
    @Min(1)
    private Integer length; //Поле не может быть null, Значение поля должно быть больше 0
    @Column(name = "GOLDEN_PALM_COUNT")
    @Min(1)
    private int goldenPalmCount; //Значение поля должно быть больше 0
    @Column(name = "USA_BOX_OFFICE")
    @DecimalMin(value = "0.0", inclusive = false)
    private double usaBoxOffice; //Значение поля должно быть больше 0
    @Column
    @Enumerated(EnumType.STRING)
    private MovieGenre genre; //Поле может быть null

    public Movie() {
        this.creationDate = LocalDate.now();
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Long getOscarsCount() {
        return oscarsCount;
    }

    public void setOscarsCount(Long oscarsCount) {
        this.oscarsCount = oscarsCount;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public float getTotalBoxOffice() {
        return totalBoxOffice;
    }

    public void setTotalBoxOffice(float totalBoxOffice) {
        this.totalBoxOffice = totalBoxOffice;
    }

    public MpaaRating getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(MpaaRating mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public Person getDirector() {
        return director;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public Person getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(Person screenwriter) {
        this.screenwriter = screenwriter;
    }

    public Person getOperator() {
        return operator;
    }

    public void setOperator(Person operator) {
        this.operator = operator;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public int getGoldenPalmCount() {
        return goldenPalmCount;
    }

    public void setGoldenPalmCount(int goldenPalmCount) {
        this.goldenPalmCount = goldenPalmCount;
    }

    public double getUsaBoxOffice() {
        return usaBoxOffice;
    }

    public void setUsaBoxOffice(double usaBoxOffice) {
        this.usaBoxOffice = usaBoxOffice;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre genre) {
        this.genre = genre;
    }
}
