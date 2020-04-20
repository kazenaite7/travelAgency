
package usecases;

import entities.Guide;
import lombok.Getter;
import lombok.Setter;
import persistance.GuideDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

@Model
public class TripsForGuide implements Serializable {

    @Inject
    private GuideDAO guideDAO;

    @Getter @Setter
    private Guide guide;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer guideId = Integer.parseInt(requestParameters.get("guideId"));
        this.guide = guideDAO.findOne(guideId);
    }
}