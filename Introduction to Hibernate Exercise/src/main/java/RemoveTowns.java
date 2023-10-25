import entities.Address;
import entities.Town;
import javax.persistence.EntityManager;
import java.util.List;

public class RemoveTowns {

    public void run(String townName, EntityManager entityManager){

        entityManager.getTransaction().begin();

        final Town townDelete = entityManager.createQuery("FROM Town "
                        + "WHERE name = :town", Town.class)
                .setParameter("town", townName)
                .getSingleResult();

        final List<Address> addressesToDelete = entityManager
                .createQuery("FROM Address WHERE town.id= :id", Address.class)
                .setParameter("id", townDelete.getId())
                .getResultList();

        addressesToDelete
                .forEach(t -> t.getEmployees()
                        .forEach(em -> em.setAddress(null)));

        addressesToDelete.forEach(entityManager::remove);
        entityManager.remove(townDelete);

        final int countDeletedAddresses = addressesToDelete.size();

        System.out.printf("%d address%s in %s deleted",
                countDeletedAddresses,
                countDeletedAddresses == 1 ? "" : "es",
                townName);

        entityManager.getTransaction().commit();
    }
}
