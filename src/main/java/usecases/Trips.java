package usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.Trip;

@Model
public class Trips implements Serializable {

    private List<Trip> allTrips;

    @PostConstruct
    public void init() {
        loadTrips();
    }

    public void loadTrips() {
        // TODO connect it to real data store
        List<Trip> trips = new ArrayList<Trip>();
        trips.add(new Trip("couch"));
        trips.add(new Trip("fridge"));

        this.allTrips = trips;
    }

    public List<Trip> getAllTrips() {
        return allTrips;
    }
}