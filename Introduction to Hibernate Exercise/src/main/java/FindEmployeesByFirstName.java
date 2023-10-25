import entities.Employee;

import javax.persistence.EntityManager;

public class FindEmployeesByFirstName {
    public void run(String stringElement, EntityManager entityManager){
        entityManager.createQuery("from Employee where lower(firstName) like lower(concat(:string, '%'))", Employee.class)
                .setParameter("string", stringElement)
                .getResultList()
                .forEach(Employee::printEmployeeByFirstName);
    }
}
