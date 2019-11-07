package home_accounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import home_accounting.UserRole;

import javax.persistence.*;

@Entity
@Data  @NoArgsConstructor
public class CustomUser {
    @Id
    @GeneratedValue
    private Long id;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private String email;
    private String phone;

    public CustomUser(String login, String password, UserRole role, String email, String phone) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
    }



 /*public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }*/
}
