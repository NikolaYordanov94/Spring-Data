import entities.Employee;

import javax.persistence.EntityManager;
public class EmployeesFromDepartment {
final String FIND_EMPLOYEES_FROM_DEPARTMENT = "from Employee where department.name = :depName order by salary, id";
    public void run(EntityManager entityManager){

        entityManager.createQuery(FIND_EMPLOYEES_FROM_DEPARTMENT, Employee.class)
                .setParameter("depName", "Research and Development")
                .getResultList()
                .forEach(Employee::printEmployeesFromDepartment);
    }
}
