import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Set;

public class AddingANewAddressAndUpdatingTheEmployee {

    public void run(String lastName, EntityManager entityManager){

        entityManager.getTransaction().begin();

        Set<Employee> employees = Set.copyOf(entityManager.createQuery("from Employee where lastName = :lastName")
                .setParameter("lastName", lastName)
                .getResultList());

        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        entityManager.persist(newAddress);

        employees.forEach(employee -> employee.setAddress(newAddress));
        entityManager.flush();
        entityManager.getTransaction().commit();
    }
}
