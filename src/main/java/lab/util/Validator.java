package lab.util;

import lab.data.Movie;
import lab.data.Person;
import lab.data.Location;
import lab.data.Coordinates;

public class Validator {
    public boolean validateMovie(Movie movie) {
        if (movie == null) return false;
        if (movie.getName() == null || movie.getName().isEmpty()) return false;
        if (movie.getCoordinates() == null || !validateCoordinates(movie.getCoordinates())) return false;
        if (movie.getCreationDate() == null) return false;
        if (movie.getOscarsCount() == null || movie.getOscarsCount() <= 0) return false;
        if (movie.getBudget() != null && movie.getBudget() <= 0) return false;
        if (movie.getTotalBoxOffice() <= 0) return false;
        if (movie.getLength() == null || movie.getLength() <= 0) return false;
        if (movie.getGoldenPalmCount() <= 0) return false;
        return !(movie.getUsaBoxOffice() <= 0);
    }

    public boolean validatePerson(Person person) {
        if (person == null) return false;
        if (person.getName() == null || person.getName().isEmpty()) return false;
        if (person.getEyeColor() == null) return false;
        if (person.getHairColor() == null) return false;
        if (person.getLocation() == null || !validateLocation(person.getLocation())) return false;
        if (person.getWeight() == null || person.getWeight() <= 0) return false;
        return person.getPassportID() == null || person.getPassportID().length() >= 4;
    }

    public boolean validateLocation(Location location) {
        if (location == null) return false;
        return location.getZ() != null;
    }

    public boolean validateCoordinates(Coordinates coordinates) {
        if (coordinates == null) return false;
        return coordinates.getX() != null;
    }
}
