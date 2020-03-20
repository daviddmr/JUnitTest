package builders;

import entities.Location;
import entities.Movie;
import utils.DataUtils;

import java.util.Arrays;
import java.util.Date;

public class LocationBuilder {

    private Location location;

    private LocationBuilder() {
    }

    public static LocationBuilder getLocation() {
        LocationBuilder builder = new LocationBuilder();
        builder.location = new Location();
        builder.location.setUser(UserBuilder.getUser().build());
        builder.location.setMovieList(Arrays.asList(MovieBuilder.getMovie().build()));
        builder.location.setLocationDate(new Date());
        builder.location.setReturnDate(DataUtils.addDays(new Date(), 1));
        builder.location.setValue(10d);

        return builder;
    }

    public Location build() {
        return location;
    }

}
