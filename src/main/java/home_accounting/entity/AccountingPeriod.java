package home_accounting.entity;

import home_accounting.enums.PeriodStateEnum;
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

    @OneToOne
    @JoinColumn(name = "custom_user_id")
    private CustomUser custom_user;

    public AccountingPeriod(CustomUser custom_user, String period){
        this.custom_user = custom_user;
        this.period = period;
        this.state = PeriodStateEnum.OPEN;
    }


}
