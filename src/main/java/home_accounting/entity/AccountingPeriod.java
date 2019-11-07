package home_accounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class AccountingPeriod {

    @Id
    @GeneratedValue
    private Long id;

    private String period;

    @Enumerated(EnumType.STRING)
    private PeriodStateEnum state;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "custom_user_id")
    private CustomUser custom_user;

    public AccountingPeriod(CustomUser custom_user, String period){
        this.custom_user = custom_user;
        this.period = period;
        this.state = PeriodStateEnum.OPEN;
    }


}
