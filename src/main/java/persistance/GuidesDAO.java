package persistance;


import entities.Guide;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class GuidesDAO {

    @Inject
    private EntityManager em;

    public List<Guide> loadAll() {
        return em.createNamedQuery("Guide.findAll", Guide.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Guide guide){
        this.em.persist(guide);
    }

    public Guide findOne(Integer id) { return em.find(Guide.class, id); }
}