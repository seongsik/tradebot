package kr.sik.tradebot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name = "COIN")
public class Coin extends BaseEntity {

    @Column(name = "NAME")
    String name;

    @Column(name = "TICKER")
    String ticker;

}
