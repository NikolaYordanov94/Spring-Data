import javax.persistence.EntityManager;
import java.util.Scanner;

public class Engine {
    public void run(){
        Scanner scanner = new Scanner(System.in);

        final EntityManager entityManager = Utils.createEntityManager();

        entityManager.getTransaction().begin();

        System.out.println("Do you want to continue with tasks (type Yes/No)");
        String runningTasks = scanner.nextLine();

        while (!runningTasks.equals("No")){
            System.out.println("Task to start (type from 2 to 13): ");
            int taskToStart = Integer.parseInt(scanner.nextLine());
            switch (taskToStart) {
                case 2 -> {
                    ChangeCasing changeCasing = new ChangeCasing();
                    changeCasing.run(entityManager);
                }
                case 3 -> {
                    ContainsEmployee containsEmployee = new ContainsEmployee();
                    String name = scanner.nextLine();
                    containsEmployee.run(name, entityManager);
                }
                case 4 -> {
                    EmployeesWithASalaryOver50000 employees = new EmployeesWithASalaryOver50000();
                    employees.run(entityManager);
                }
                case 5 -> {
                    EmployeesFromDepartment employeesFromDepartment = new EmployeesFromDepartment();
                    employeesFromDepartment.run(entityManager);
                }
                case 6 -> {
                    AddingANewAddressAndUpdatingTheEmployee employee = new AddingANewAddressAndUpdatingTheEmployee();
                    String lastName = scanner.nextLine();
                    employee.run(lastName, entityManager);
                }
                case 7 -> {
                    AddressesWithEmployeeCount addresses = new AddressesWithEmployeeCount();
                    addresses.run(entityManager);
                }
                case 8 -> {
                    GetEmployeesWithProject employee = new GetEmployeesWithProject();
                    int employeeID = Integer.parseInt(scanner.nextLine());
                    employee.run(employeeID, entityManager);
                }
                case 9 -> {
                    FindTheLatest10Projects projects = new FindTheLatest10Projects();
                    projects.run(entityManager);
                }
                case 10 -> {
                    IncreaseSalaries employeesWithNewSalaries = new IncreaseSalaries();
                    employeesWithNewSalaries.run(entityManager);
                }
                case 11 -> {
                    String stringElement = scanner.nextLine();
                    FindEmployeesByFirstName employees = new FindEmployeesByFirstName();
                    employees.run(stringElement, entityManager);
                }
                case 12 -> {
                    EmployeesMaximumSalaries employees = new EmployeesMaximumSalaries();
                    employees.run(entityManager);
                }
                case 13 -> {
                    String townToRemove = scanner.nextLine();
                    RemoveTowns towns = new RemoveTowns();
                    towns.run(townToRemove, entityManager);
                }
            }
            System.out.println("Do you want to continue with tasks (type Yes/No)");
            runningTasks = scanner.nextLine();
        }
        entityManager.close();
    }
}
