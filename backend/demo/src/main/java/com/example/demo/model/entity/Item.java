package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Setter
@Getter
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cost")
    private int cost;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_type_id", insertable = false, updatable = false)
    @JsonIgnore
    private ItemType itemType;

    @Column(name="item_type_id")
    private Long itemTypeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pet_type_id", insertable = false, updatable = false)
    @JsonIgnore
    private Pet pet;

    @Column(name="pet_type_id")
    private Long petTypeId;

    @Column(name = "image_item")
    private String imagePath;
}
