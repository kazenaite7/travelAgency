package usecases;

import entities.Trip;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistance.TripsDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateTripDetails implements Serializable {

    private Trip trip;

    @Inject
    private TripsDAO tripsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateTripDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer tripId = Integer.parseInt(requestParameters.get("tripId"));
        this.trip = tripsDAO.findOne(tripId);
    }

    @Transactional
    @LoggedInvocation
    public String updateTripPrice() {
        try{
            tripsDAO.update(this.trip);
        } catch (OptimisticLockException e) {
            return "/tripDetails.xhtml?faces-redirect=true&tripId=" + this.trip.getId() + "&error=optimistic-lock-exception";
        }
        return "trips.xhtml?guideId=" + this.trip.getGuide().getId() + "&faces-redirect=true";
    }
}