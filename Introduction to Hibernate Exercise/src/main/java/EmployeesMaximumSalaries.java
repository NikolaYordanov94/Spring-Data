import javax.persistence.EntityManager;

public class EmployeesMaximumSalaries {
    public void run(EntityManager entityManager){
        entityManager
                .createQuery("SELECT department.name, max(salary)" +
                        " FROM Employee " +
                        " GROUP BY department.name" +
                        " HAVING max(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach(objects -> System.out.println(objects[0] + " " + objects[1]));

        System.out.println(System.lineSeparator());

        // result with custom POJO
//        entityManager
//                .createQuery("SELECT NEW entities.models.Result(department.name, MAX(salary))" +
//                        " FROM Employee" +
//                        " GROUP BY department.name" +
//                        " HAVING MAX(salary) NOT BETWEEN 30000 AND 70000", Result.class)
//                .getResultList()
//                .forEach(System.out::println);
    }
}
