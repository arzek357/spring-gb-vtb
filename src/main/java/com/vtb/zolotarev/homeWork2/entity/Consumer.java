package com.vtb.zolotarev.homeWork2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "consumers")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "orders",
            joinColumns = @JoinColumn(name = "consumer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

    @Override
    public String toString() {
        return String.format("Client [id = %d, name = %s]", id, name);
    }

    public Consumer(String name) {
        this.name = name;
    }
}
