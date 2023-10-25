import entities.Employee;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {

    public void run(EntityManager entityManager) {

        entityManager.getTransaction().begin();

        final List<Employee> employees = entityManager
                .createQuery("FROM Employee WHERE department.name in ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();


        employees.forEach(employee -> employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12))));

        entityManager.flush();
        entityManager.getTransaction().commit();

        employees.forEach(employee -> System.out.printf("%s %s (%s)%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary()));

    }
}
