
package usecases;

import entities.Guide;
import lombok.Getter;
import lombok.Setter;
import persistence.GuidesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Guides {

    @Inject
    private GuidesDAO guidesDAO;

    @Getter @Setter
    private Guide guideToCreate = new Guide();

    @Getter
    private List<Guide> allGuides;

    @PostConstruct
    public void init(){
        loadAllGuides();
    }

    @Transactional
    public String createGuide(){
        this.guidesDAO.persist(guideToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllGuides(){
        this.allGuides = guidesDAO.loadAll();
    }
}