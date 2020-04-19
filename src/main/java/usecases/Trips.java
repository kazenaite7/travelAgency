package usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import entities.Trip;
import persistance.TripsDAO;

@Model
public class Trips implements Serializable {
    @Inject
    private TripsDAO tripsDAO;

    private Trip tripToCreate = new Trip();

    private List<Trip> allTrips;

    @PostConstruct
    public void init() {
        loadTrips();
    }

    public void loadTrips() {
        this.allTrips = tripsDAO.loadAll();
    }

    public List<Trip> getAllTrips() {
        return allTrips;
    }

    @Transactional
    public String createTrip(){
        this.tripsDAO.persist(tripToCreate);
        return "success";
    }

    public Trip getTripToCreate() {
        return tripToCreate;
    }

    public void setTripToCreate(Trip tripToCreate) {
        this.tripToCreate = tripToCreate;
    }
}