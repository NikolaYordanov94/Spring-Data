import entities.Employee;
import javax.persistence.EntityManager;
public class EmployeesWithASalaryOver50000 {
    public void run(EntityManager entityManager){
        entityManager
                .createQuery("FROM Employee WHERE salary > 50000", Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(employee.getFirstName()));
    }
}
