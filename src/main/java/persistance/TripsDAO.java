package persistance;

import entities.Trip;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class TripsDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Trip> loadAll() {
        return em.createNamedQuery("Trip.findAll", Trip.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Trip trip){
        this.em.persist(trip);
    }
}
