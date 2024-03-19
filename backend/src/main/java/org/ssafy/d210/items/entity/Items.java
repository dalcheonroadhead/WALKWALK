package org.ssafy.d210.items.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1)
    @Column(name = "items_id")
    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String icon;

    @Column(name = "egg_price")
    @ColumnDefault("0")
    private Integer eggPrice;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String effect;

    @Column(nullable = false, length = 15)
    private String type;
}