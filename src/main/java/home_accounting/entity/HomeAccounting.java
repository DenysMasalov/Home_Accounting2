package home_accounting.entity;

import home_accounting.enums.AccountingEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class HomeAccounting {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "accounting_period_id")
    private AccountingPeriod accounting_period;

    @OneToOne
    @JoinColumn(name = "custom_user_id")
    private CustomUser custom_user;


    @Enumerated(EnumType.STRING)
    private AccountingEnum type;

    private String description;
    private long value;


    public HomeAccounting(AccountingPeriod accounting_period,
                          CustomUser custom_user,
                          AccountingEnum type,
                          String description,
                          Long value){
        this.accounting_period = accounting_period;
        this.custom_user = custom_user;
        this.type= type;
        this.description = description;
        this.value = value;


    }
}
