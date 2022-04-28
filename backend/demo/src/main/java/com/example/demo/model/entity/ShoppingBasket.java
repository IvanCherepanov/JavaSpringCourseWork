package com.example.demo.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shopping_basket")
@Setter
@Getter
@NoArgsConstructor
public class ShoppingBasket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name="user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id", insertable = false, updatable = false)
    @JsonIgnore
    private Item item;

    @Column(name="item_id")
    private Long itemId;

    @Column(name = "amount")
    private int amount;
}
