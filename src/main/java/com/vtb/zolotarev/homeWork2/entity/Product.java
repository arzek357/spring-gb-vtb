package com.vtb.zolotarev.homeWork2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String name;

    @Column(name = "price")
    private Long price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "consumer_id")
    )
    private List<Consumer> consumerList;

    public Product(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, name = %s, price=%d]", id, name, price);
    }

}
