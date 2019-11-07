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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "custom_user_id")
    private CustomUser custom_user;

}
