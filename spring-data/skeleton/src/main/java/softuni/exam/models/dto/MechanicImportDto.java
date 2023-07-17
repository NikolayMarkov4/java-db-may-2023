package softuni.exam.models.dto;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MechanicImportDto {
    //first name – length value higher than or equal to 2. The values are unique in the database.
//last name – length value higher than or equal to 2.
//email – an email – (must contains ‘@’ and ‘.’ – dot). The email of a seller is unique.
//phone – length value higher than or equal to 2. Can be nullable. The values are unique in the database.

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2)
    private String firstName;

    @NotNull
    @Size(min = 2)
    private String lastName;

    @Size(min = 2)
    private String phone;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
