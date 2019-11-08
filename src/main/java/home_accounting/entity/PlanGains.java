package home_accounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class PlanGains {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private long value;

    @OneToOne
    @JoinColumn(name = "custom_user_id")
    private CustomUser custom_user;

    public PlanGains(CustomUser custom_user, String description, Long value){
        this.custom_user = custom_user;
        this.description = description;
        this.value = value;
    }
}
