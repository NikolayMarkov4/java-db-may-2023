import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    private static final List<String> DEPARTMENTS_NAMES_TO_INCREASE_SALARIES =
            List.of("Engineering",
                    "Tool Design",
                    "Marketing",
                    "Information Services");

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.createEntityManager();

        entityManager.getTransaction().begin();

        final List<Employee> employees = entityManager
                .createQuery("FROM Employee WHERE department.name in (:deps)", Employee.class)
                .setParameter("deps", DEPARTMENTS_NAMES_TO_INCREASE_SALARIES)
                .getResultList();

        employees.forEach(employee -> employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12))));

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();

        employees.forEach(employee -> System.out.printf("%s %s (%s)%n",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary()));
    }
}

