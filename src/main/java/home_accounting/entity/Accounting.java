package home_accounting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Accounting {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accounting_period_id")
    private AccountingPeriod accounting_period;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "custom_user_id")
    private CustomUser custom_user;






}