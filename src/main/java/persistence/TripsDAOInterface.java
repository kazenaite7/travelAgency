package persistence;

import entities.Trip;

public interface TripsDAOInterface {

    Trip findOne(Integer id);

    Trip update(Trip trip);
}
