package decorators;

import entities.Trip;
import persistence.TripsDAOInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class TripsDAODecorator implements TripsDAOInterface {

    @Inject @Delegate @Any
    TripsDAOInterface tripDAO;

    public Trip update(Trip trip){
        if(trip.getPrice() < 200){
            System.out.println("Budget trip");
        }
        return tripDAO.update(trip);
    }

}