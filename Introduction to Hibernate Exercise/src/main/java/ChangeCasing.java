import entities.Town;
import javax.persistence.EntityManager;
import java.util.List;
public class ChangeCasing {
    public void run(EntityManager entityManager){
        entityManager.getTransaction().begin();

        final List<Town> allTowns = entityManager.createQuery("FROM Town", Town.class)
                .getResultList();

        for (Town town : allTowns) {
            if (town.getName().length() > 5) {
                entityManager.detach(town);
                continue;
            }

            town.setName(town.getName().toUpperCase());
            entityManager.persist(town);
        }

        entityManager.getTransaction().commit();
    }
}
