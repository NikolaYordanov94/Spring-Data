import entities.Employee;

import javax.persistence.EntityManager;

public class ContainsEmployee {

    public void run(String fullName, EntityManager entityManager){
        try{
            entityManager
                    .createQuery("FROM Employee where concat_ws(' ', first_name, last_name) = :fullName", Employee.class)
                    .setParameter("fullName", fullName)
                    .getSingleResult();

            entityManager.getTransaction().commit();
            System.out.println("Yes");

        }catch(Exception e){
            System.out.println("No");
        }
    }
}
