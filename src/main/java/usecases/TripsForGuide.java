
package usecases;

import entities.Guide;
import entities.Trip;
import interceptors.LoggedInvocation;
import lombok.Getter;
import lombok.Setter;
import persistance.GuidesDAO;
import persistance.TripsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class TripsForGuide implements Serializable {

    @Inject
    private GuidesDAO guidesDAO;

    @Inject
    private TripsDAO tripsDAO;

    @Getter @Setter
    private Guide guide;

    @Getter @Setter
    private Trip tripToCreate = new Trip();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer guideId = Integer.parseInt(requestParameters.get("guideId"));
        this.guide = guidesDAO.findOne(guideId);
    }

    @Transactional
    @LoggedInvocation
    public String createTrip() {
        tripToCreate.setGuide(this.guide);
        tripsDAO.persist(tripToCreate);
        return "trips?faces-redirect=true&guideId=" + this.guide.getId();
    }
}