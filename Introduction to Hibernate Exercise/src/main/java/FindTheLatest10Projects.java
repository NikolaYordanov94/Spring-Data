import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class FindTheLatest10Projects {
    public void run(EntityManager entityManager){
        entityManager.createQuery("from Project order by startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream().sorted(Comparator.comparing(Project::getName)).forEach(Project::printProject);
    }
}
