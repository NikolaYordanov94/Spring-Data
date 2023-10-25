import entities.Employee;

import javax.persistence.EntityManager;

public class GetEmployeesWithProject {
    public void run(int employeeID, EntityManager entityManager){
        Employee employee = entityManager.createQuery("from Employee where id = :employeeID", Employee.class)
                .setParameter("employeeID", employeeID)
                .getSingleResult();

        employee.printEmployeeWithProjects();
    }
}
