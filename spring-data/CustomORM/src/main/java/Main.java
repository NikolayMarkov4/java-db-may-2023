import entities.User;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException,
            IllegalAccessException,
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException {

        final EntityManager<User> userEntityManager = new EntityManager<>();

        User user = new User();
        user.setId(1L);

//        userEntityManager.doAlter(User.class);
//        userEntityManager.doDelete(user);
//
//        User user = new User("Pesho", 25, LocalDate.now());
//
//        userEntityManager.persist(user);
//
//        user.setId(1);
//        user.setAge(30);
//        user.setUsername("Gosho");
//
//        userEntityManager.persist(user);
//
//        User firstFirstWithArgument = userEntityManager.findFirst(User.class, "age > 10");
//        User firstFirstWithoutArgument = userEntityManager.findFirst(User.class);
//        Iterable<User> getAllWithArgument = userEntityManager.find(User.class, "age > 10");
//        Iterable<User> getAll = userEntityManager.find(User.class);

//        EntityManager<Account> accountEntityManager = new EntityManager();
//
//        accountEntityManager.doCreate(Account.class);
//        accountEntityManager.doAlter(Account.class);
//
//        System.out.println();
    }
}