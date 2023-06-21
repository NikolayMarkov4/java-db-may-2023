import entities.Employee;

public class EmployeesFromDepartment {
    public static void main(String[] args) {
        Utils.createEntityManager()
                .createQuery("FROM Employee WHERE department.name = :dName ORDER BY salary, id",
                        Employee.class)
                .setParameter("dName", "Research and Development")
                .getResultList()
                .forEach(Employee::printFullNameDepNameAndSalary);
    }
}


