import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Utils {

    static EntityManager createEntityManager() {
        return Persistence.createEntityManagerFactory("soft_uni")
                .createEntityManager();
    }
}
