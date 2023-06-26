import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        final String townName = new Scanner(System.in).nextLine();

        final EntityManager entityManager = Utils.createEntityManager();

        entityManager.getTransaction().begin();

        final Town townDelete = entityManager.createQuery("FROM Town "
                        + "WHERE name = :town", Town.class)
                .setParameter("town", townName)
                .getSingleResult();

        final List<Address> addressesToDelete = entityManager
                .createQuery("FROM Address WHERE town.id= :id", Address.class)
                .setParameter("id", townDelete.getId())
                .getResultList();

        addressesToDelete
                .forEach(t -> t.getEmployees()
                        .forEach(em -> em.setAddress(null)));

        addressesToDelete.forEach(entityManager::remove);
        entityManager.remove(townDelete);

        final int countDeletedAddresses = addressesToDelete.size();

        System.out.printf("%d address%s in %s deleted",
                countDeletedAddresses,
                countDeletedAddresses == 1 ? "" : "es",
                townName);

        entityManager.getTransaction().commit();
    }
}
