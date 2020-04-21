package usecases;

import lombok.Getter;
import lombok.Setter;
import myBatis.dao.GuideMapper;
import myBatis.model.Guide;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GuideMyBatis {
    @Inject
    private GuideMapper guideMapper;

    @Getter
    private List<Guide> allGuides;

    @Getter
    @Setter
    private Guide guideToCreate = new Guide();

    @PostConstruct
    public void init() {
        this.loadAllGuides();
    }

    private void loadAllGuides() {
        this.allGuides = guideMapper.selectAll();
    }

    @Transactional
    public String createGuide() {
        guideMapper.insert(guideToCreate);
        return "/myBatis/guides?faces-redirect=true";
    }
}
