import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.createEntityManager();
        final Scanner scanner = new Scanner(System.in);
        final String fullName = scanner.nextLine();

        entityManager.getTransaction().begin();

        final String isEmployeePresented = entityManager
                .createQuery("FROM Employee where concat_ws(' ', first_name, last_name) = :fullName", Employee.class)
                .setParameter("fullName", fullName)
                .getResultList()
                .isEmpty() ? "No" : "Yes";

        entityManager.getTransaction().commit();

        System.out.println(isEmployeePresented);
    }
}

