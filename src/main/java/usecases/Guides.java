
package usecases;

import entities.Guide;
import lombok.Getter;
import lombok.Setter;
import persistance.GuideDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Guides {

    @Inject
    private GuideDAO guideDAO;

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
        this.guideDAO.persist(guideToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllGuides(){
        this.allGuides = guideDAO.loadAll();
    }
}