import entities.Address;
import javax.persistence.EntityManager;

public class AddressesWithEmployeeCount {

    public void run(EntityManager entityManager){
        entityManager.createQuery("from Address order by employees.size desc", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(Address::printAddressesWithEmployeesCount);
    }
}
